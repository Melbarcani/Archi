package com.elbarcani.archi.user.domain;

import java.util.List;
import java.util.Map;

public interface FormDao {
    List<Ticket> getAllTickets();
    Map<Integer, String> getTicketsState();

    void saveForm(Form form, int userId);
    void saveTicketState(Ticket ticket, Form newForm);

    boolean isFormExist();
    Form loadForm(int userId);
    List<TicketStateHistory> loadTicketsHistory(int userId);
}
