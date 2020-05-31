package com.securitydb.demo.controller;

import com.securitydb.demo.dao.RequestService;
import com.securitydb.demo.dao.UserService;
import com.securitydb.demo.model.UserEntity;
import com.securitydb.demo.model.UserRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    UserService userService;
    RequestService requestService;

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        model.addAttribute("userRequest", new UserRequests());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@RequestParam String nameOfUser,
                                 @ModelAttribute UserRequests userRequests,
                                 @ModelAttribute UserEntity userEntity, Model model) {
        model.addAttribute("userEntity", userEntity);
        UserEntity user1 = new UserEntity(nameOfUser, userEntity.getRoleOfUser(), userEntity.getRequestNumber());
        UserRequests userRequest = new UserRequests(userRequests.getComment(), userEntity.getRequestNumber());
        userService.save(user1);
        requestService.save(userRequest);
        return "result";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }
}
