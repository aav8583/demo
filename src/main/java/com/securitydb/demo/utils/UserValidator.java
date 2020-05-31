package com.securitydb.demo.utils;

import com.securitydb.demo.dao.UserService;
import com.securitydb.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity userEntity = (UserEntity) o;
        if (!userService.findById(userEntity.getId()).isPresent()){
            errors.rejectValue("Name", "", "This name already is used");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
