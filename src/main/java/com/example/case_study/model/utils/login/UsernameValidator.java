package com.example.case_study.model.utils.login;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.IUserService;
import com.example.case_study.model.service.impl.UserService;

import java.util.ArrayList;
import java.util.List;

public class UsernameValidator implements Validator{
    private final String username;
    private IUserService userService;

    public UsernameValidator(String username) {
        this.username = username;
       userService = new UserService();
    }

    @Override
    public boolean isCheck() {
        for (String username1 : getUsernameList()) {
            if (username1.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getUsernameList() {
        List<String> usernameList = new ArrayList<>();
        for (User user : userService.selectAll()) {
            usernameList.add(user.getUsername());
        }
        return usernameList;
    }
}
