package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.infrastructure.controller.TicketDto;
import com.elbarcani.archi.user.infrastructure.controller.UserDto;

import java.util.List;

public interface BringDto {
    UserDto getUserDto(int userId);
    List<TicketDto> getOrderDtoByUser(int userId);
}
