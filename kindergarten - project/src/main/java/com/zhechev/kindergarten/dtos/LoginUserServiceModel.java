package com.zhechev.kindergarten.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private String childrenName;
    private String email;
    private String phone;
    private String address;
    private String subject;
    private String group;

    public LoginUserServiceModel(String username, String childrenName, String email, String phone, String address, String subject, String group) {
        this.username = username;
        this.childrenName = childrenName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.subject = subject;
        this.group = group;
    }
}
