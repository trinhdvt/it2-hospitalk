package com.team1.it2hospitalk.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @NotEmpty
    @Size(min = 5, max = 32, message = "Username must be from 5 to 20 characters")
    private String username;

    @NotEmpty
    @Size(min = 6, max = 32, message = "Password must be from 6 to 32 characters")
    private String password;

}
