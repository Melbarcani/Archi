package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;

import java.util.Map;
import java.util.Optional;

public class FormController {

    private final static String ERROR_MESSAGE = "There is at missing answers in your form";
    private final static String OK_MESSAGE = "the answers are recorded";
    private final static String UNDEFINED = "undefined";
    private String message;
    private Form form;
    private Ticket ticket;

    //All those methods will be improved to

    public FormController(FormDao formDao) {
        form = new Form(formDao.getTicketsState(), formDao.getAllTickets());
        initForm();
    }

    public FormController(User user) {
        form = new Form();
        initForm();
    }

    public void restoreNeverFilled() {
        initForm();
    }

    private void initForm() {
        for (Ticket ticket : form.getTicketsList()) {
            form.getTicketsState().put(ticket.getTicketId(), UNDEFINED);
        }
    }

    public void addTicket(Ticket ticket) {
        form.getTicketsList().add(ticket);
    }

    public Optional<Ticket> getTicketById(int id) {
        return form.getTicketsList().stream().filter(t -> t.getTicketId() == id).findFirst();
    }

    public boolean formIsNotOkay() {
        return form.getTicketsState().containsValue(UNDEFINED);
    }

    public String getMessageStatus() {
        return formIsNotOkay() ? ERROR_MESSAGE : OK_MESSAGE;
    }

    public void setTicketAnswer(int ticketId, String choice) {
        form.getTicketsState().put(ticketId, choice);
    }

    public Map<Integer, String> getTicketsState() {
        return form.getTicketsState();
    }
}
