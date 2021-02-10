package com.elbarcani.archi.user_service.infrastructure.controller;

import com.elbarcani.archi.AbstractMock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChoicesControllerMock extends AbstractMock {

    private final String TICKET_ID = "1";
    private final String USER_ID = "123";
    private final String TICKET_PRICE = "100";
    private final String USER_CHOICE = "Keep";
    private final String CHOICE_DATE = "04-02-2021";
    private final String SEPARATOR = ",";
    public final String REGULAR_URL = "UserServiceFileTest.txt";
    private String ticketData;

    @Override
    protected void createObjects() {
        ticketData = createTicketData();
    }

    private String createTicketData() {
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(TICKET_ID)
                .append(SEPARATOR + TICKET_PRICE)
                .append(SEPARATOR + USER_ID)
                .append(SEPARATOR + USER_CHOICE)
                .append(SEPARATOR + CHOICE_DATE);
        return dataBuilder.toString();
    }

    public void createFile(String url) {
        try {
            FileWriter outputStream = new FileWriter(url);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
            bufferedWriter.write(ticketData);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTicketId(){
        return Integer.parseInt(TICKET_ID);
    }
}
