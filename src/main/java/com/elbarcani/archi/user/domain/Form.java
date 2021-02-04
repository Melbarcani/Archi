package com.elbarcani.archi.user.domain;

import java.util.List;
import java.util.Map;

public class Form {
    private final Map<Integer, String> ticketState;
    private final List<Ticket> ticketsList;

    public Form(Map<Integer, String> ticketState, List<Ticket> ticketsList){
        this.ticketsList = ticketsList;
        this.ticketState = ticketState;
    }

    public List<Ticket> getTicketsList() {
        return ticketsList;
    }

    public Map<Integer, String> getTicketsState() {
        return ticketState;
    }

    public void mergeWithNewForm(Form newForm) {
        ticketState.putAll(newForm.getTicketsState());
        if(ticketState.size() != ticketsList.size()){
            ticketsList.addAll(newForm.getTicketsList());
        }
    }
}
