package com.codegym.excercise_registration_form.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.codegym.excercise_registration_form.dto.UserDto;
import com.codegym.excercise_registration_form.model.User;
import com.codegym.excercise_registration_form.service.IUserService;

import java.util.List;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<User> users = userService.findAll();
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("userDto", new UserDto());
        return modelAndView;
    }

    @PostMapping("/validateUser")
    public ModelAndView checkValidation(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        new UserDto().validate(userDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/index");
        } else {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            userService.save(user);
            return new ModelAndView("/result");
        }
    }
}