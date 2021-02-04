package com.elbarcani.archi.user.use_case;

public interface SeeOldChoices {
    boolean isFormExist();
    void loadFormHistory();
    void createNonExistentFormComposite();
    void display();
}
