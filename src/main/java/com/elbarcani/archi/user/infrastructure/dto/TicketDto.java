package com.elbarcani.archi.user.infrastructure.dto;

import com.elbarcani.archi.user.domain.Ticket;

import java.util.List;

public interface TicketDto {
    List<Ticket> getOrderDtoByUser();
}
