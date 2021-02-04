package com.elbarcani.archi.client_service.use_case;

public interface SeeAllSavedChoices {
    void display();
    boolean isDataExist();
    void loadAllSavedChoices();
    void displayNonExistentDataError();
}
