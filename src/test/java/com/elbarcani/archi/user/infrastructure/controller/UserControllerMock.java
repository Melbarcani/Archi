package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.AbstractMock;
import com.elbarcani.archi.user.domain.User;
import org.mockito.Mockito;

public class UserControllerMock extends AbstractMock {
    public static final int REGULAR_USER_ID = 123;
    public static final int REGULAR_TICKETS_NUMBER = 6;
    public static final int FIRST_TICKET_ID = 1;
    public static final int WRONG_USER_ID = -1;

    private User user;
    @Override
    protected void createObjects() {
        user = createUser();
    }

    private User createUser(){
        return Mockito.mock(User.class);
    }

    public User getUser(){
        return user;
    }
}
