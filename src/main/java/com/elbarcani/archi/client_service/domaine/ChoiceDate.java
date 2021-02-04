package com.elbarcani.archi.client_service.domaine;

public class ChoiceDate {
    private final String state;
    private final String date;

    public ChoiceDate(String state, String date) {
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
