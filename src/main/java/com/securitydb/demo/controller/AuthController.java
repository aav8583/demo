package com.securitydb.demo.controller;


import com.securitydb.demo.dao.UserService;
import com.securitydb.demo.model.UserEntity;
import com.securitydb.demo.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    UserService userService;
    UserValidator userValidator;

    @RequestMapping("/login")
    public String login() {
        return "auth/sign_in";
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@RequestParam String nameOfUser,
                         @ModelAttribute UserEntity userEntity, BindingResult bindingResult, Model model) {
        model.addAttribute("userEntity", userEntity);
        userService.save(userEntity);
        userValidator.validate(userEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/sign_up";
        }
        userService.save(userEntity);
        return "redirect:/grac";
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
