package com.example.case_study.model.utils.login;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;

import java.util.List;

public class LoginValidator implements Validator{
    private final String username;
    private final String password;
    private final List<User> userList ;

    public LoginValidator(String username, String password) {
        this.username = username;
        this.password = password;
        userList = (new UserService()).selectAll();
    }

    @Override
    public boolean isCheck() {
        for (User user : userList) {
            if ((username.equals(user.getUsername()) || username.equals(user.getPhone()) || username.equals(user.getEmail())) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
