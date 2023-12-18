package com.example.case_study.model.DAO;

import com.example.case_study.model.entity.User;

public interface IUserDAO extends IEntityDAO<User>{
    User getUserByUsername(String username);

}
