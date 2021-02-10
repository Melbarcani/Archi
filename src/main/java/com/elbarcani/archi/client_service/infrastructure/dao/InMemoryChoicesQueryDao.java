package com.elbarcani.archi.client_service.infrastructure.dao;

import com.elbarcani.archi.client_service.domaine.ChoiceDate;
import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.domaine.TicketHistory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryChoicesQueryDao implements ChoicesQueryDao {
    public static final String SEPARATOR = ",";
    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";

    private String fileName;

    public InMemoryChoicesQueryDao() {
        fileName = FORM_FILE_TEXT+TEXT_EXTENSION;
    }

    @Override
    public boolean isDataExist() {
        Path path = Paths.get(fileName);
        File file = new File(fileName);

        return Files.exists(path) && file.length() != 0;
    }


    @Override
    public List<TicketHistory> loadTicketsHistory() {
        List<TicketHistory> ticketsHistories = new ArrayList<>();
        if (isDataExist()) {
            try {
                FileReader reader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTicket;
                while ((lineTicket = bufferedReader.readLine()) != null) {
                    Optional<TicketHistory> ticketHistory = loadLastChoiceData(lineTicket);
                    ticketHistory.ifPresent(ticketsHistories::add);
                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ticketsHistories;
    }

    private Optional<TicketHistory> loadLastChoiceData(String line) {
        String[] data = line.split(SEPARATOR);
        Optional<TicketHistory> ticketHistory = Optional.of(new TicketHistory(Integer.parseInt(data[0])));
        for (int i = 3; i < data.length; i += 2) {
            ticketHistory.get().addStateDateToTicket(new ChoiceDate(data[i], data[i + 1]));
            ticketHistory.get().setUserId(Integer.parseInt(data[2]));
        }
        return ticketHistory;
    }
}
