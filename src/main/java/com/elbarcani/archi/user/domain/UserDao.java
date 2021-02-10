package com.elbarcani.archi.user.domain;

public interface UserDao {

    User findUser();
    boolean isUserExist();
}
