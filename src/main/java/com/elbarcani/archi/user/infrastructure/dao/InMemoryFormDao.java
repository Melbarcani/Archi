package com.elbarcani.archi.user.infrastructure.dao;

import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.Ticket;

import java.util.List;
import java.util.Map;

public class InMemoryFormDao implements FormDao {
    @Override
    public List<Ticket> getTickets(int userId) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public Map<Integer, String> getTicketsState() {
        return null;
    }

    @Override
    public void insertTicket(int ticketId, int userId) {

    }

    @Override
    public void saveTicketState(int ticketId, String state) {

    }
}
