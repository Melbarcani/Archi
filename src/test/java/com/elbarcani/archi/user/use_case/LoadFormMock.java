package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.Ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoadFormMock extends AbstractUserMock {

    public static final String TEST_FILE_PATH = "fileNameTest.txt";
    public static final String SEPARATOR = ",";
    public static final int REGULAR_TICKET_ID = 1;
    public static final int REGULAR_TICKET_PRICE = 100;
    public static final String REGULAR_DATE = "10-2-2021";
    public static final String KEEP_STATE = "Keep";
    public static final String REIMBURSE_STATE = "Reimburse";

    private Form form;
    private List<Ticket> ticketsList;
    private Map<Integer, String> keepTicketsState;
    private Map<Integer, String> reimburseTicketsState;

    @Override
    protected void createObjects() {
        form = createForm(keepTicketsState);
        ticketsList = createTicketsList();
        keepTicketsState = createTicketsState(KEEP_STATE);
        reimburseTicketsState = createTicketsState(REIMBURSE_STATE);
    }

    private Form createForm(Map<Integer, String> ticketsState){
        Form f = mock(Form.class);
        when(f.getTicketsList()).thenReturn(ticketsList);
        when(f.getTicketsState()).thenReturn(ticketsState);
        return f;
    }

    private List<Ticket> createTicketsList() {
        List<Ticket> list = new ArrayList<>();
        list.add(getRegularTicket());
        return list;
    }

    private Map<Integer, String> createTicketsState(String state) {
        Map<Integer, String> states = new HashMap<>();
        states.put(REGULAR_TICKET_ID, state);
        return states;
    }

    public boolean clearData(){
        File testedFile = new File(TEST_FILE_PATH);
        return testedFile.delete();
    }

    public boolean createTestFile(){
        try {
            FileWriter outputStream = new FileWriter(TEST_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
            bufferedWriter.write(REGULAR_TICKET_ID
                    + SEPARATOR + REGULAR_TICKET_PRICE
                    + SEPARATOR + REGULAR_USER_ID
                    + SEPARATOR + KEEP_STATE
                    + SEPARATOR + REGULAR_DATE);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isTestFileExist();
    }

    public boolean isTestFileExist(){
        Path path = Paths.get(TEST_FILE_PATH);
        return Files.exists(path);
    }

    public Form getRegularForm(){
        return form;
    }


}
