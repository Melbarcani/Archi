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
    private static final int MERGE_ID = -1;

    private Form form;
    private BufferedWriter bufferedWriter;
    private final String fileName;

    public InMemoryFormDao(String fileName) {
        this.fileName = fileName;
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
            if (!isFormExist()) {
                saveNewFormInFile(newForm);
            } else {
                appendFormToExistingFile(newForm, userId);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendFormToExistingFile(Form newForm, int userId) throws IOException {
        Form currentUserForm = loadForm(userId);
        StringBuilder holeFormFile = mergeForms(newForm);
        if (!currentUserForm.getTicketsList().isEmpty()) {
            appendToFormFile(userId, holeFormFile);
        } else {
            saveNewFormInFile(form);
        }
        bufferedWriter.write(holeFormFile.toString());
    }

    private StringBuilder mergeForms(Form newForm) {
        StringBuilder holeFormFile = new StringBuilder();
        loadForm(MERGE_ID);
        form.mergeWithNewForm(newForm);
        return holeFormFile;
    }

    private void saveNewFormInFile(Form form) throws IOException {
        FileWriter outputStream = new FileWriter(fileName);
        bufferedWriter = new BufferedWriter(outputStream);
        for (Ticket ticket : form.getTicketsList()) {
            saveTicketState(ticket, form);
        }
    }

    private void appendToFormFile(int userId, StringBuilder holeFormFile) throws IOException {
        for (Ticket ticket : form.getTicketsList()) {
            holeFormFile.append(appendNewTicketState(ticket, form, userId));
        }
        FileWriter outputStream = new FileWriter(fileName);
        bufferedWriter = new BufferedWriter(outputStream);
    }

    private StringBuilder appendNewTicketState(Ticket ticket, Form newForm, int userId) {
        StringBuilder holeFormFile = new StringBuilder();
        try {
            var reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            convertFormToString(ticket, newForm, userId, holeFormFile, bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holeFormFile;
    }

    private void convertFormToString(Ticket ticket, Form newForm, int userId, StringBuilder holeFormFile, BufferedReader bufferedReader) throws IOException {
        String lineTicket;
        while ((lineTicket = bufferedReader.readLine()) != null) {
            if (ticket.getId() == Integer.parseInt(lineTicket.split(SEPARATOR)[0])) {
                if (userId != ticket.getUserId()) {
                    holeFormFile.append(lineTicket).append(System.lineSeparator());
                } else {
                    holeFormFile.append(lineTicket)
                            .append(SEPARATOR).append(newForm.getTicketsState().get(ticket.getId()))
                            .append(SEPARATOR).append(getCurrentDate())
                            .append(System.lineSeparator());
                }
            }
        }
    }

    @Override
    public Form loadForm(int userId) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            Map<Integer, String> ticketState = new HashMap<>();
            List<Ticket> ticketsList = new ArrayList<>();
            loadTicket(userId, bufferedReader, ticketState, ticketsList);
            form = new Form(ticketState, ticketsList);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return form;
    }

    private void loadTicket(int userId, BufferedReader bufferedReader, Map<Integer, String> ticketState, List<Ticket> ticketsList) throws IOException {
        String lineTicket;
        if (userId == MERGE_ID) {
            while ((lineTicket = bufferedReader.readLine()) != null) {
                loadTicketFromLine(lineTicket, ticketState, ticketsList);
            }
        } else {
            while ((lineTicket = bufferedReader.readLine()) != null) {
                loadTicketFromLine(lineTicket, ticketState, ticketsList, userId);
            }
        }
    }

    @Override
    public void saveTicketState(Ticket ticket, Form newForm) {
        try {
            bufferedWriter.write(ticket.getId()
                    + SEPARATOR + ticket.getPrice()
                    + SEPARATOR + ticket.getUserId()
                    + SEPARATOR + newForm.getTicketsState().get(ticket.getId())
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
        loadTicket(ticketState, ticketsList, data);
    }

    private void loadTicketFromLine(String lineTicket, Map<Integer, String> ticketState, List<Ticket> ticketsList, int userId) {
        String[] data = lineTicket.split(SEPARATOR);
        if (Integer.parseInt(data[2]) == userId) {
            loadTicket(ticketState, ticketsList, data);
        }
    }

    private void loadTicket(Map<Integer, String> ticketState, List<Ticket> ticketsList, String[] data) {
        Ticket ticket = new Ticket(
                Integer.parseInt(data[0]),
                Integer.parseInt(data[1]),
                Integer.parseInt(data[2]));
        ticketsList.add(ticket);
        ticketState.put(ticket.getId(), data[data.length - 2]);
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
                ticketHistory.ifPresent(ticketsHistories::add);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketsHistories;
    }

    private Optional<TicketStateHistory> loadTicketHistory(String line, int userId) {
        String[] data = line.split(SEPARATOR);
        Optional<TicketStateHistory> ticketHistory = Optional.empty();
        if (Integer.parseInt(data[2]) == userId) {
            ticketHistory = Optional.of(new TicketStateHistory(Integer.parseInt(data[0])));
            for (int i = 3; i < data.length; i += 2) {
                ticketHistory.get().addStateDateToTicket(new StateDate(data[i], data[i + 1]));
                ticketHistory.get().setUserId(Integer.parseInt(data[2]));
            }
        }
        return ticketHistory;
    }
}
