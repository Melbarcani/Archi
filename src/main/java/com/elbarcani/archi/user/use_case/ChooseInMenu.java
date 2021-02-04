package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.User;

public interface ChooseInMenu {
    void setUser(User user);
    void displayMenu();
    void seeOldChoicesButtonAction();
    void showOrderButtonAction();
    void editChoiceButtonAction();
}
