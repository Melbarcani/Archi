package com.elbarcani.archi.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Form {
    private final Map<Integer, String> ticketState;
    private final List<Ticket> ticketsList;

    public Form(){
        ticketsList = new ArrayList<>();
        ticketState = new TreeMap<>();
    }

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

}
