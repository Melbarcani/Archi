package com.elbarcani.archi.user.domain;

import java.util.List;

public interface FormDao {

    void saveForm(Form form, int userId);

    void saveTicketState(Ticket ticket, Form newForm);

    boolean isFormExist();

    Form loadForm(int userId);

    List<TicketStateHistory> loadTicketsHistory(int userId);
}
