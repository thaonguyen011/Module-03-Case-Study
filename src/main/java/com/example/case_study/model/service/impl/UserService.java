package com.example.case_study.model.service.impl;

import com.example.case_study.model.DAO.IUserDAO;
import com.example.case_study.model.DAO.impl.UserDAO;
import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private IUserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    @Override
    public List<User> selectAll() {
        return userDAO.selectAll();
    }

    @Override
    public User selectById(int id) {
        return userDAO.selectById(id);
    }

    @Override
    public boolean insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean remove(int id) {
        return userDAO.remove(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean isExistUser(String username) {
        for (User user : selectAll()) {
            if (username.equals(user.getUsername()) || username.equals(user.getEmail()) || username.equals(user.getPhone())) {
                return true;
            }
        }
        return false;
    }

}
