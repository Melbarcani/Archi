package com.elbarcani.archi.unittest.user.infrastructure.controller;

import com.elbarcani.archi.unittest.AbstractMock;
import com.elbarcani.archi.unittest.AbstractTest;
import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class UserControllerTest extends AbstractTest<UserControllerMock, UserController> {

    @Test
    @DisplayName("getUserById")
    public void testGetUserByIdMethod() {
        User user = mock.getUser();
        if (objectToTest.isUserExist()) {
            user = objectToTest.getUserById();
        }
        Assertions.assertThat(user.getId()).isEqualTo(UserControllerMock.REGULAR_USER_ID);

        objectToTest = new UserController(UserControllerMock.WRONG_USER_ID);
        if (!objectToTest.isUserExist()) {
            assertThrows(Exception.class, () -> objectToTest.getUserById());
        }
    }

    @Test
    @DisplayName("getOrderByUser")
    public void testGetOrderByUserMethod(){
        List<Ticket> tickets = objectToTest.getOrderByUser();
        assertEquals(UserControllerMock.REGULAR_TICKETS_NUMBER, tickets.size());
        assertEquals(UserControllerMock.FIRST_TICKET_ID, tickets.get(0).getId());
        for(Ticket t : tickets){
            assertEquals(UserControllerMock.REGULAR_USER_ID, t.getUserId());
        }
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected UserControllerMock createMock() {
        return new UserControllerMock();
    }

    @Override
    protected UserController createObjectToTest() {
        return new UserController(UserControllerMock.REGULAR_USER_ID);
    }
}
