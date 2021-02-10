package com.elbarcani.archi.user.domain;

import java.util.List;

public interface TicketDao {
    List<Ticket> getOrderDtoByUser();
}
