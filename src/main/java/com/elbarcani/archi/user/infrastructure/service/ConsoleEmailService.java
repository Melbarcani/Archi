package com.elbarcani.archi.user.infrastructure.service;

import com.elbarcani.archi.user.domain.Email;
import com.elbarcani.archi.user.domain.User;

import java.io.*;

public class ConsoleEmailService {
   /* private static final String EMAIL_NOT_SENT_TEXT = "a mail has not been sent to : ";
    private final User user;
    private PrintStream console;
    private ByteArrayOutputStream consoleCopy;
    private String emailText;
    private BufferedWriter bufferedWriter;
    private String errorFileName = "errorEmailFile.txt";

    public ConsoleEmailService(User user) {
        this.user = user;

        prepareConsoleToBeCached();
        createEmailText();

        prepareBufferWriter();
    }

    private void prepareConsoleToBeCached() {
        console = System.out;
        consoleCopy = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleCopy));
    }

    private void createEmailText() {
        emailText = "this is an email to : " +
                user.getFirstName() + " " +
                user.getLastName();
    }

    private void prepareBufferWriter() {
        try{
            FileWriter outputStream = new FileWriter(errorFileName);
            bufferedWriter = new BufferedWriter(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send() {
        console.print(emailText);
    }

    @Override
    public boolean isSent() {
        return consoleCopy.toString().equals(emailText);
    }

    @Override
    public void emailNotSentAction() {
        String errorText = EMAIL_NOT_SENT_TEXT + user.getEmail();
        try{
            bufferedWriter.write(errorText);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
