package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.TicketDao;

import java.util.List;

public class LoadUserTickets {
    private final TicketDao ticketDao;

    public LoadUserTickets(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public List<Ticket> execute() {
        return ticketDao.getOrderDtoByUser();
    }
}
