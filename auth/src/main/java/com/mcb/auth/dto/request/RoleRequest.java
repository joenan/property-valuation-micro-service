package com.mcb.auth.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RoleRequest {

    @NotBlank
    private String name;
}
