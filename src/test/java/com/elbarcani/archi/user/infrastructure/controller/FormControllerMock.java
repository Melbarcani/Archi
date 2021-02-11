package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.use_case.AbstractUserMock;
import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.Ticket;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormControllerMock extends AbstractUserMock {

    public static final String EXISTENT_FILE_NAME = "fileNameTest.txt";
    public static final int REGULAR_USER_ID = 123;
    public static final int REGULAR_TICKET_ID = 1;
    public static final int REGULAR_TICKET_PRICE = 100;
    public static final String KEEP_STATE = "Keep";
    public static final String REIMBURSE_STATE = "Reimburse";

    private Form form, secondForm;
    private Ticket ticket;
    private List<Ticket> ticketsList;
    private Map<Integer, String> keepTicketsState;
    private Map<Integer, String> reimburseTicketsState;

    @Override
    protected void createObjects() {
        ticket = createTicket();
        ticketsList = createTicketsList();
        keepTicketsState = createTicketsState(KEEP_STATE);
        reimburseTicketsState = createTicketsState(REIMBURSE_STATE);
        form = createForm(keepTicketsState);
        secondForm = createForm(reimburseTicketsState);
    }

    private Form createForm(Map<Integer, String> ticketsState){
        Form f = mock(Form.class);
        when(f.getTicketsList()).thenReturn(ticketsList);
        when(f.getTicketsState()).thenReturn(ticketsState);
        return f;
    }

    private Ticket createTicket() {
        Ticket t = mock(Ticket.class);
        when(t.getId()).thenReturn(REGULAR_TICKET_ID);
        when(t.getPrice()).thenReturn(REGULAR_TICKET_PRICE);
        when(t.getUserId()).thenReturn(REGULAR_USER_ID);
        return t;
    }

    private List<Ticket> createTicketsList() {
        List<Ticket> list = new ArrayList<>();
        list.add(ticket);
        return list;
    }

    private Map<Integer, String> createTicketsState(String state) {
        Map<Integer, String> states = new HashMap<>();
        states.put(REGULAR_TICKET_ID, state);
        return states;
    }

    public boolean clearData(){
        File testedFile = new File(FormControllerMock.EXISTENT_FILE_NAME);
        return testedFile.delete();
    }

    public Form getRegularForm(){
        return form;
    }

    public Form getSecondForm(){
        return secondForm;
    }
}
