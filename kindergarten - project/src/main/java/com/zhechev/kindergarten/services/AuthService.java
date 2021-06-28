package com.zhechev.kindergarten.services;

import com.zhechev.kindergarten.models.User;
import com.zhechev.kindergarten.dtos.UserRegisterDto;
import com.zhechev.kindergarten.dtos.LoginUserServiceModel;

public interface AuthService {
    User register(UserRegisterDto userRegisterDto);

    LoginUserServiceModel login(UserRegisterDto userRegisterDto);

    String hashPassword(String str);
}
