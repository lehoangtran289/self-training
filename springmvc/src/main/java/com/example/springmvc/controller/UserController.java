package com.example.springmvc.controller;

import com.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String userForm(Locale locale, Model model) {
        model.addAttribute("users", userService.list());
        return "users";
    }
}
