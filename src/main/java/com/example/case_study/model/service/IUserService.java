package com.example.case_study.model.service;

import com.example.case_study.model.entity.User;

public interface IUserService extends IEntityService<User>{
    User getUserByUsername(String username);
    boolean isExistUser(String username);


}
