package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.dto.TicketDto;
import com.elbarcani.archi.user.infrastructure.dto.UserDto;
import com.elbarcani.archi.user.infrastructure.dto.http.HttpTicketDto;
import com.elbarcani.archi.user.infrastructure.dto.http.HttpUserDto;

import java.util.List;

public class UserController {

    int userId;
    UserDto userDto;

    public UserController(int userId) {
        this.userId = userId;
        userDto = new HttpUserDto(userId);
    }

    public User getUserById() {
        return userDto.getUserDto();
    }

    public boolean isUserExist() {
        return userDto.isUserExist();
    }

    public List<Ticket> getOrderByUser() {
        TicketDto ticketDto = new HttpTicketDto(userId);
        return ticketDto.getOrderDtoByUser();
    }
}
