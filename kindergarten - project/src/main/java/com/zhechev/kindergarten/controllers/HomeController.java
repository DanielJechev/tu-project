package com.zhechev.kindergarten.controllers;

import com.zhechev.kindergarten.dtos.ChildHomeModel;
import com.zhechev.kindergarten.services.ChildrenService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final ChildrenService childrenService;
    private final ModelMapper modelMapper;

    public HomeController(ChildrenService childrenService, ModelMapper modelMapper) {
        this.childrenService = childrenService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getIndex(HttpSession session) {
        return "home/index.html";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

    @GetMapping("/home/all")
    public ModelAndView getHomeAll(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("home/home-all.html");
        boolean condition = childrenService.areThereChildren();

        List<ChildHomeModel> kids = childrenService
                .getChild(getChildrenName(session))
                .stream()
                .map(child -> modelMapper.map(child, ChildHomeModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("condition", condition);
        modelAndView.addObject("kids", kids);

        return modelAndView;
    }
}
