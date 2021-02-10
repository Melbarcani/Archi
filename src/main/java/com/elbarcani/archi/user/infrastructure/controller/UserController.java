package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.domain.UserDao;
import com.elbarcani.archi.user.infrastructure.dao.HttpUserDao;

public class UserController {

    int userId;
    UserDao userDao;

    public UserController(int userId) {
        this.userId = userId;
        userDao = new HttpUserDao(userId);
    }

    public User getUserById() {
        return userDao.findUser();
    }

    public boolean isUserExist() {
        return userDao.isUserExist();
    }
}
