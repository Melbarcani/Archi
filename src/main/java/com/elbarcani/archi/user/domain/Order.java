package com.elbarcani.archi.user.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Ticket> ticketList;

    public Order(){
        this.ticketList = new ArrayList<>();
    }

    public Order(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
