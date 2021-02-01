package com.elbarcani.archi.user.use_case;

public interface SeeOldChoices {
    void setUser(int userId);
    boolean isFormExist();
    void loadFormHistory();
    void createNonExistentFormComposite();
    void display();
}
