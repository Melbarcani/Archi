package com.elbarcani.archi.user.domain;

public class StateDate {
    private final String state;
    private final String date;

    public StateDate(String state, String date) {
        this.state = state;
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public String getDate() {
        return date;
    }
}
