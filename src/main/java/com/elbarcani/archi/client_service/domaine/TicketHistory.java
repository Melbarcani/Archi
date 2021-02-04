package com.elbarcani.archi.client_service.domaine;

import java.util.ArrayList;
import java.util.List;

public class TicketHistory {
    private final int ticketId;
    private int userId;
    private final List<ChoiceDate> stateDateList;

    public TicketHistory(int ticketId) {
        this.ticketId = ticketId;
        stateDateList = new ArrayList<>();
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public List<ChoiceDate> getStateDateList() {
        return stateDateList;
    }

    public void addStateDateToTicket(ChoiceDate stateDate){
        stateDateList.add(stateDate);
    }
}
