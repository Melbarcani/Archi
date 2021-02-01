package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.http.UserDTOHttp;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public User getUserById(int userId){
        BringDto callHttp = new UserDTOHttp();
        UserDto userDto = callHttp.getUserDto(userId);
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
    }

    public List<Ticket> getOrderByUser(int userId) {
        BringDto callHttp = new UserDTOHttp();
        List<TicketDto> order = callHttp.getOrderDtoByUser(userId);
        List<Ticket> ticketList = new ArrayList<>();
        for(TicketDto dto : order){
            ticketList.add(new Ticket(dto.getId(), dto.getPrice(), dto.getUserId()));
        }
        return ticketList;
    }
}
