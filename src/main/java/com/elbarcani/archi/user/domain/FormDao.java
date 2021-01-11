package com.elbarcani.archi.user.domain;

import java.util.List;
import java.util.Map;

public interface FormDao {
    List<Ticket> getTickets(int userId);
    List<Ticket> getAllTickets();
    Map<Integer, String> getTicketsState();

    void insertTicket(int ticketId, int userId);
    void saveTicketState(int ticketId, String state);
}
