package com.elbarcani.archi.user.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;


    public User(int id){
        this.id = id;
    }
    public User(int id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
