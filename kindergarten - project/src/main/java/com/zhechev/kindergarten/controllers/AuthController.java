package com.zhechev.kindergarten.controllers;

import com.zhechev.kindergarten.dtos.LoginUserServiceModel;
import com.zhechev.kindergarten.dtos.UserRegisterDto;
import com.zhechev.kindergarten.models.User;
import com.zhechev.kindergarten.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class AuthController {
    private ModelMapper modelMapper = new ModelMapper();
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login.html");
        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView getRegisterForm(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/register.html");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterDto userRegisterDto, ModelAndView modelAndView) {
        User user = authService.register(userRegisterDto);
        modelMapper.map(user, UserRegisterDto.class);
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserRegisterDto userRegisterDto, ModelAndView modelAndView, HttpSession session) {
        modelMapper.map(userRegisterDto, User.class);
        try {
            LoginUserServiceModel loginServiceModel = authService.login(userRegisterDto);
            session.setAttribute("user", loginServiceModel);
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("users/invalid-login.html");
            return modelAndView;
        }
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session, ModelAndView modelAndView) {
        session.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}