package com.mcb.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;


import java.util.Set;

public record SignupRequest(
        @NotBlank @Size(min = 3, max = 50) String username,
        @NotBlank @Size(max = 50) @Email String email,
        @NotBlank String name,
        @NotBlank String businessUnit,
        @NotBlank String contactNumber,
        Set<String> role,
        @NotBlank @Size(min = 3, max = 100) String password
) {
    @Builder
    public SignupRequest {

    }
}
