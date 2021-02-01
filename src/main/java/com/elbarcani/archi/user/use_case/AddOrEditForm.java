package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.infrastructure.controller.UserController;

public interface AddOrEditForm {
    void display(UserController userController);
    void setUser(int userId);

    boolean isFormExist();
    void createNewForm(UserController userController);
    void loadPrecedentForm(UserController userController);
}
