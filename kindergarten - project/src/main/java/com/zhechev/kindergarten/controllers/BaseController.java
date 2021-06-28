package com.zhechev.kindergarten.controllers;

import com.zhechev.kindergarten.dtos.LoginUserServiceModel;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    protected String getUsername(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getUsername();
    }

    protected String getPhone(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getPhone();
    }

    protected String getAddress(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getAddress();
    }

    protected String getSubject(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getSubject();
    }

    protected String getEmail(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getEmail();
    }

    protected String getGroup(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getGroup();
    }

    protected String getChildrenName(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getChildrenName();
    }
}