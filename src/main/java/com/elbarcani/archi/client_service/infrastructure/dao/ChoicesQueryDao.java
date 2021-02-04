package com.elbarcani.archi.client_service.infrastructure.dao;

import com.elbarcani.archi.client_service.domaine.TicketHistory;

import java.util.List;

public interface ChoicesQueryDao {

    boolean isDataExist();

    List<TicketHistory> loadTicketsHistory();
}
