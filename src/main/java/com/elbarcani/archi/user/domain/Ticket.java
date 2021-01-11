package com.elbarcani.archi.user.domain;

public class Ticket {
    private int ticketId;
    private int price;
    private int userId;

    public Ticket(int id, int price, int userId) {
        this.ticketId = id;
        this.price = price;
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getPrice() {
        return price;
    }

    public int getUserId() {
        return userId;
    }
}
