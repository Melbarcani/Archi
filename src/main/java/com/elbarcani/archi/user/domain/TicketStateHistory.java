package com.elbarcani.archi.user.domain;

import java.util.ArrayList;
import java.util.List;

public class TicketStateHistory {
    private int ticketId;
    private int userId;
    private List<StateDate> stateDateList;

    public TicketStateHistory(int ticketId) {
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

    public List<StateDate> getStateDateList() {
        return stateDateList;
    }

    public void addStateDateToTicket(StateDate stateDate){
        stateDateList.add(stateDate);
    }
}
