package com.mcb.auth.controller;


import com.mcb.auth.dto.request.LoginRequest;
import com.mcb.auth.dto.request.SignupRequest;
import com.mcb.auth.dto.response.JwtResponse;
import com.mcb.auth.dto.response.MessageResponse;
import com.mcb.auth.repository.RoleRepository;
import com.mcb.auth.repository.UserRepository;
import com.mcb.auth.security.jwt.JwtUtils;
import com.mcb.auth.security.services.UserDetailsImpl;
import com.mcb.commons.entities.ERole;
import com.mcb.commons.entities.Role;
import com.mcb.commons.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(JwtResponse.builder()
                .id(userDetails.getId())
                .token(jwt)
                .businessUnit(userDetails.getBusinessUnit())
                .username(userDetails.getUsername())
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .contactNumber(userDetails.getContactNumber())
                .roles(roles)
                .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.username())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account


        User user = User.builder()
                .businessUnit(signUpRequest.businessUnit())
                .contactNumber(signUpRequest.contactNumber())
                .username(signUpRequest.username())
                .email(signUpRequest.email())
                .name(signUpRequest.name())
                .password(encoder.encode(signUpRequest.password()))

                .build();

        Set<String> strRoles = signUpRequest.role();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(new Role(null, ERole.ROLE_USER));
        } else {
            strRoles.forEach(role -> {
                Role newRole = roleRepository.findByName(ERole.valueOf(role.toUpperCase()))
                        .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                roles.add(newRole);
            });
        }

        roleRepository.saveAll(roles);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
