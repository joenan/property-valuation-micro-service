package com.mcb.commons.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto implements Serializable {

    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "businessUnit is required")
    private String businessUnit;
    @NotBlank(message = "contactNumber is required")
    private String contactNumber;
}
