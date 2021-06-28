package com.zhechev.kindergarten.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginServiceModel {
    private String username;
    private String childName;

    public LoginServiceModel(String username, String childName) {
        this.username = username;
        this.childName = childName;
    }
}
