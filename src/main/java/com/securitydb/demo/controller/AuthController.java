package com.securitydb.demo.controller;


import com.securitydb.demo.dao.UserService;
import com.securitydb.demo.model.UserEntity;
import com.securitydb.demo.repository.UserRepository;
import com.securitydb.demo.security.AuthProviderImpl;
import com.securitydb.demo.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    UserService userService;
    UserValidator userValidator;
    UserRepository userRepository;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign_in")
    public String getSignIn(Model model){
        model.addAttribute("userEntity",new UserEntity());
        return "sign_in";
    }

    @PostMapping("/sign_in")
    public String signIn(@ModelAttribute UserEntity userEntity, Model model){
        model.addAttribute("userEntity", userEntity);
        UserEntity userEntityDB = userRepository.findByNameOfUser(userEntity.getNameOfUser());
        if (userEntityDB == null){
            throw new UsernameNotFoundException("User not exist");
        }
        if (!userEntityDB.getPassword().equals(userEntity.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }
        return "greeting";
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@RequestParam String nameOfUser,
                         @ModelAttribute UserEntity userEntity, BindingResult bindingResult, Model model) {
        model.addAttribute("userEntity", userEntity);
        userService.save(userEntity);
        userValidator.validate(userEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        userService.save(userEntity);
        return "grac";
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
