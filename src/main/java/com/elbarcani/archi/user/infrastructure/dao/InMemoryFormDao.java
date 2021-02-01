package com.elbarcani.archi.user.infrastructure.dao;

import com.elbarcani.archi.user.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class InMemoryFormDao implements FormDao, Serializable {
    public static final String SEPARATOR = ",";
    private Form form;
    private BufferedWriter bufferedWriter;
    private String fileName;

    public InMemoryFormDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public Map<Integer, String> getTicketsState() {
        return null;
    }

    @Override
    public boolean isFormExist() {
        Path path = Paths.get(fileName);
        File file = new File(fileName);

        return Files.exists(path) && file.length() != 0;
    }

    @Override
    public void saveForm(Form newForm, int userId) {
        try {
            if (isFormExist()) {
                Form currentUserForm = loadForm(userId);
                StringBuilder holeFormFile = new StringBuilder();
                loadForm(-1);
                form.mergeWithNewForm(newForm);
                if (!currentUserForm.getTicketsList().isEmpty()) {
                    for (Ticket ticket : form.getTicketsList()) {
                        holeFormFile.append(appendNewTicketState(ticket, form, userId));
                    }
                    FileWriter outputStream = new FileWriter(fileName);
                    bufferedWriter = new BufferedWriter(outputStream);
                } else {
                    FileWriter outputStream = new FileWriter(fileName);
                    bufferedWriter = new BufferedWriter(outputStream);
                    for (Ticket ticket : form.getTicketsList()) {
                        saveTicketState(ticket, form);
                    }
                }
                bufferedWriter.write(holeFormFile.toString());
            } else {
                FileWriter outputStream = new FileWriter(fileName);
                bufferedWriter = new BufferedWriter(outputStream);
                for (Ticket ticket : newForm.getTicketsList()) {
                    saveTicketState(ticket, newForm);
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Form loadForm(int userId) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTicket;
            Map<Integer, String> ticketState = new HashMap<>();
            List<Ticket> ticketsList = new ArrayList<>();
            if (userId == -1) {
                while ((lineTicket = bufferedReader.readLine()) != null) {
                    loadTicketFromLine(lineTicket, ticketState, ticketsList);
                }
            } else {
                while ((lineTicket = bufferedReader.readLine()) != null) {
                    loadTicketFromLine(lineTicket, ticketState, ticketsList, userId);
                }
            }
            form = new Form(ticketState, ticketsList);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return form;
    }

    private StringBuilder appendNewTicketState(Ticket ticket, Form newForm, int userId) {
        StringBuilder holeFormFile = new StringBuilder();
        try {
            var reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTicket;
            while ((lineTicket = bufferedReader.readLine()) != null) {
                if (ticket.getTicketId() == Integer.parseInt(lineTicket.split(SEPARATOR)[0])) {
                    if(ticket.getUserId() != userId){
                        holeFormFile.append(lineTicket).append(System.lineSeparator());
                    } else {
                        holeFormFile.append(lineTicket)
                                .append(SEPARATOR).append(newForm.getTicketsState().get(ticket.getTicketId()))
                                .append(SEPARATOR).append(getCurrentDate())
                                .append(System.lineSeparator());
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holeFormFile;
    }

    @Override
    public void saveTicketState(Ticket ticket, Form newForm) {
        try {
            bufferedWriter.write(ticket.getTicketId()
                    + SEPARATOR + ticket.getPrice()
                    + SEPARATOR + ticket.getUserId()
                    + SEPARATOR + newForm.getTicketsState().get(ticket.getTicketId())
                    + SEPARATOR + getCurrentDate());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDate() {
        LocalDate date = LocalDate.now();
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
    }

    private void loadTicketFromLine(String lineTicket, Map<Integer, String> ticketState, List<Ticket> ticketsList) {
        String[] data = lineTicket.split(SEPARATOR);
        Ticket ticket = new Ticket(
                Integer.parseInt(data[0]),
                Integer.parseInt(data[1]),
                Integer.parseInt(data[2]));
        ticketsList.add(ticket);
        ticketState.put(ticket.getTicketId(), data[data.length - 2]);
    }

    private void loadTicketFromLine(String lineTicket, Map<Integer, String> ticketState, List<Ticket> ticketsList, int userId) {
        String[] data = lineTicket.split(SEPARATOR);
        if (Integer.parseInt(data[2]) == userId) {
            Ticket ticket = new Ticket(
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]));
            ticketsList.add(ticket);
            ticketState.put(ticket.getTicketId(), data[data.length - 2]);
        }
    }

    @Override
    public List<TicketStateHistory> loadTicketsHistory(int userId) {
        List<TicketStateHistory> ticketsHistories = new ArrayList<>();
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTicket;
            while ((lineTicket = bufferedReader.readLine()) != null) {
                Optional<TicketStateHistory> ticketHistory = loadTicketHistory(lineTicket, userId);
                if (ticketHistory.isPresent()) {
                    ticketsHistories.add(ticketHistory.get());
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketsHistories;
    }

    private Optional<TicketStateHistory> loadTicketHistory(String line, int userId) {
        String data[] = line.split(SEPARATOR);
        Optional<TicketStateHistory> ticketHistory = Optional.empty();
        if (Integer.parseInt(data[2]) == userId) {
            ticketHistory = Optional.of(new TicketStateHistory(Integer.parseInt(data[0])));
            for (int i = 3; i < data.length; i += 2) {
                ticketHistory.get().addStateDateToTicket(new StateDate(data[i], data[i + 1]));
            }
        }
        return ticketHistory;
    }
}
