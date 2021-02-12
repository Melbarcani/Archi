package com.elbarcani.archi.user.infrastructure.service;

import com.elbarcani.archi.user.domain.Email;
import com.elbarcani.archi.user.domain.User;

import java.io.*;

public class ConsoleEmail implements Email{
    private static final String EMAIL_NOT_SENT_TEXT = "a mail has not been sent to : ";
    private final User user;
    private String emailText;
    private BufferedWriter bufferedWriter;
    private String errorFileName = "errorEmailFile.txt";

    public ConsoleEmail(User user) {
        this.user = user;
        createEmailText();
    }

    private void createEmailText() {
        emailText = "this is an email to : " +
                user.getFirstName() + " " +
                user.getLastName();
    }

    public boolean send() {
        System.out.print(emailText);
        return true;
    }

    @Override
    public void emailNotSentAction() {

    }


}
