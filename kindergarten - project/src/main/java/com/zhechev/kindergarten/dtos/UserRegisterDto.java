package com.zhechev.kindergarten.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    @NotEmpty(message = "This field cannot be empty. Please, provide your full name!")
    private String username;

    @NotEmpty(message = "This field cannot be empty. Please, provide your email!")
    private String email;

    @NotEmpty(message = "This field cannot be empty. Please, provide your password!")
    private String password;

    @NotEmpty(message = "This field cannot be empty. Please, provide your password!")
    private String confirmPassword;

    @NotEmpty(message = "The field cannot be empty. Please, provide EGN!")
    @Pattern(regexp = "[0-9]{2}[0-5][0-9][0-3][0-9]{5}", message = "Invalid EGN!")
    @Size(min = 10, max = 10, message = "Invalid length!")
    private String egn;

    @Pattern(regexp = "^.+,.+,.+$", message = "Please enter (country,city,street) separated by comma!")
    @NotEmpty(message = "The field cannot be empty. Please, provide Address!")
    @Size(max = 120, message = "Invalid length!")
    private String address;

    @NotEmpty(message = "The field cannot be empty. Please, provide phone number!")
    @Pattern(regexp = "08[789]\\d{7}", message = "Please,enter real phone number!")
    private String phone;

}
