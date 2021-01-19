package com.elbarcani.archi.gui.service;

import com.elbarcani.archi.user.domain.Order;
import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.TicketDto;
import com.elbarcani.archi.user.infrastructure.controller.UserDto;
import com.elbarcani.archi.user.infrastructure.http.CallHttp;

import java.util.ArrayList;
import java.util.List;

public class UserSWTUIService implements UserUIService{
    private User user;

    public UserSWTUIService() {

    }

    @Override
    public User getUserById(int userId){
        CallHttp callHttp = new CallHttp();
        UserDto userDto = callHttp.getUserDto(userId);
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
    }

    @Override
    public List<Ticket> getOrderByUser(int userId) {
        CallHttp callHttp = new CallHttp();
        List<TicketDto> order = callHttp.getOrderDtoByUser(userId);
        List<Ticket> ticketList = new ArrayList<>();
        for(TicketDto dto : order){
            ticketList.add(new Ticket(dto.getId(), dto.getPrice(), dto.getUserId()));
        }
        return ticketList;
    }
}
