package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.domain.UserDao;

import java.util.Optional;

public class LoadUser {
    private UserDao userDao;

    public LoadUser(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> execute(){
        if(userDao.isUserExist()){
            return Optional.of(userDao.findUser());
        }
        return Optional.empty();
    }
}
