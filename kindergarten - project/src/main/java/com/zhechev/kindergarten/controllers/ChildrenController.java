package com.zhechev.kindergarten.controllers;

import com.zhechev.kindergarten.dtos.ChildrenCreateModel;
import com.zhechev.kindergarten.dtos.ChildrenCreateServiceModel;
import com.zhechev.kindergarten.dtos.GroupModel;
import com.zhechev.kindergarten.dtos.GroupServiceModel;
import com.zhechev.kindergarten.dtos.LoginUserServiceModel;
import com.zhechev.kindergarten.dtos.SubjectModel;
import com.zhechev.kindergarten.dtos.SubjectServiceModel;
import com.zhechev.kindergarten.exceptions.KindergartenNotFoundException;
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
@RequestMapping("/children")
@AllArgsConstructor
public class ChildrenController extends BaseController {

    private ModelMapper modelMapper = new ModelMapper();
    private final UsersService usersService;

    @GetMapping("/details")
    public ModelAndView getChildrenGroup(HttpSession session, ModelAndView modelAndView) {
        if (!isAuthenticated(session)) {
            modelAndView.setViewName("redirect:/users/login");
            return modelAndView;
        }
        modelAndView.setViewName("child/children-details.html");
        return modelAndView;
    }

    @PostMapping("/details")
    public String createChildrenGroup(@ModelAttribute GroupModel groupModel, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);
        String email = getEmail(session);
        String phone = getPhone(session);
        String address = getAddress(session);
        String subject = getSubject(session);
        String group = getGroup(session);

        GroupServiceModel serviceModel = modelMapper.map(groupModel, GroupServiceModel.class);
        try {
            usersService.chooseGroupForChildren(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username, groupModel.getVid(), email, phone, address, subject, group);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/children/education";
        } catch (KindergartenNotFoundException ex) {
            throw new KindergartenNotFoundException("ВЕЧЕ ИМАТЕ ИЗБРАНА ГРУПА !");
        } catch (Exception e) {
            throw new KindergartenNotFoundException("За съжаление всички места са вече заети !");
        }
    }

    @GetMapping("/create")
    public String getChildren(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/users/login";
        }
        return "child/create-children.html";
    }

    @PostMapping("/create")
    public String createChildren(@ModelAttribute ChildrenCreateModel childrenCreateModel, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);
        String email = getEmail(session);
        String phone = getPhone(session);
        String address = getAddress(session);
        String subject = getSubject(session);
        String group = getGroup(session);

        ChildrenCreateServiceModel serviceModel = modelMapper.map(childrenCreateModel, ChildrenCreateServiceModel.class);
        try {
            usersService.createChildrenForUser(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username, childrenCreateModel.getName(), email, phone, address, subject, group);
            session.setAttribute("user", loginUserServiceModel);

            return "redirect:/children/details/";
        } catch (Exception ex) {
            return "redirect:/children/create";
        }
    }

    @GetMapping("/education")
    public String getChildrenSubject(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/users/login";
        }
        return "child/children-education.html";
    }

    @PostMapping("/education")
    public String createChildrenZip(@ModelAttribute SubjectModel subjectModel, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);
        String email = getEmail(session);
        String phone = getPhone(session);
        String address = getAddress(session);
        String subject = getSubject(session);
        String group = getGroup(session);

        SubjectServiceModel serviceModel = modelMapper.map(subjectModel, SubjectServiceModel.class);
        try {
            usersService.chooseZipForChildren(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username, subjectModel.getEducation(), email, phone, address, subject, group);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/users/end";
        } catch (Exception ex) {
            throw new KindergartenNotFoundException("ВЕЧЕ ИМАТЕ ИЗБРАН ЗИП !");
        }
    }
}
