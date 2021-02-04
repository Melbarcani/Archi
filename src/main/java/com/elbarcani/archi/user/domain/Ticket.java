package com.elbarcani.archi.user.domain;

public class Ticket {
    private int id;
    private int price;
    private int userId;

    public Ticket(){}

    public Ticket(int id, int price, int userId) {
        this.id = id;
        this.price = price;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getUserId() {
        return userId;
    }
}
