package com.mcb.auth.dto.response;

import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String name;
    private String businessUnit;
    private String contactNumber;
    private String email;
    private List<String> roles;
}
