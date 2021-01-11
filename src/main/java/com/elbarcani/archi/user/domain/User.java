package com.elbarcani.archi.user.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private Form form;
    private final List<Ticket> ticketList;

    public User(int id){
        this.id = id;
        ticketList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }


    public Form getForm() {
        return form;
    }
}
