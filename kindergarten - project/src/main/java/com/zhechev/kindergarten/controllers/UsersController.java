package com.zhechev.kindergarten.controllers;

import com.zhechev.kindergarten.dtos.CommentCreateModel;
import com.zhechev.kindergarten.dtos.CommentCreateServiceModel;
import com.zhechev.kindergarten.dtos.LoginUserServiceModel;
import com.zhechev.kindergarten.exceptions.GroupNotFoundException;
import com.zhechev.kindergarten.services.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController extends BaseController {
    private ModelMapper modelMapper = new ModelMapper();
    private final UsersService usersService;

    @GetMapping("/profile")
    public String getProfile(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/users/login";
        }
        return "users/profile.html";
    }

    @GetMapping("/priem")
    public ModelAndView getPriem(ModelAndView modelAndView) {
        modelAndView.setViewName("users/priem.html");
        return modelAndView;
    }

    @GetMapping("/groups")
    public ModelAndView getGroup(ModelAndView modelAndView) {
        modelAndView.setViewName("users/group.html");
        return modelAndView;
    }

    @GetMapping("/sun")
    public ModelAndView getGroupSun(ModelAndView modelAndView) {
        modelAndView.setViewName("users/sun.html");
        return modelAndView;
    }

    @GetMapping("/rusalka")
    public ModelAndView getGroupRusalka(ModelAndView modelAndView) {
        modelAndView.setViewName("users/rusalka.html");
        return modelAndView;
    }

    @GetMapping("/star")
    public ModelAndView getGroupStar(ModelAndView modelAndView) {
        modelAndView.setViewName("users/star.html");
        return modelAndView;
    }

    @GetMapping("/svetulka")
    public ModelAndView getGroupSvetulka(ModelAndView modelAndView) {
        modelAndView.setViewName("users/svetulka.html");
        return modelAndView;
    }

    @GetMapping("/kalinka")
    public ModelAndView getGroupKalinka(ModelAndView modelAndView) {
        modelAndView.setViewName("users/kalinka.html");
        return modelAndView;
    }

    @GetMapping("/end")
    public ModelAndView getEndOfAll(ModelAndView modelAndView) {
        modelAndView.setViewName("child/end.html");
        return modelAndView;

    }

    @GetMapping("/comments")
    public ModelAndView getComment(ModelAndView modelAndView, HttpSession session) {
        if (!isAuthenticated(session)) {
            modelAndView.setViewName("redirect:/users/login");
            return modelAndView;
        }
        modelAndView.setViewName("comments/komentar.html");
        return modelAndView;
    }

    @PostMapping("/comments")
    public String createCommment(@ModelAttribute CommentCreateModel commentCreateModel, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);
        String email = getEmail(session);
        String phone = getPhone(session);
        String address = getAddress(session);
        String subject = getSubject(session);
        String group = getGroup(session);

        CommentCreateServiceModel serviceModel = modelMapper.map(commentCreateModel, CommentCreateServiceModel.class);
        try {
            usersService.createUserComment(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username, commentCreateModel.getName(), email, phone, address, subject, group);
            session.setAttribute("user", loginUserServiceModel);

            return "redirect:/users/success/";
        } catch (Exception ex) {
            throw new GroupNotFoundException("Полетата означени със * са задължителни.Моля попълнете ги!");
        }
    }

    @GetMapping("/contacts")
    public ModelAndView getContacts(ModelAndView modelAndView) {
        modelAndView.setViewName("comments/contacts.html");
        return modelAndView;
    }

    @GetMapping("/success")
    public ModelAndView getCommentResponse(ModelAndView modelAndView) {
        modelAndView.setViewName("comments/response-successfully.html");
        return modelAndView;
    }
}