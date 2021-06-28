package com.zhechev.kindergarten.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class RegisterUserServiceModel {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}

