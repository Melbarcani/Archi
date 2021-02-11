package com.elbarcani.archi.user.domain;

public interface Email {
    boolean send();
    void emailNotSentAction();
}
