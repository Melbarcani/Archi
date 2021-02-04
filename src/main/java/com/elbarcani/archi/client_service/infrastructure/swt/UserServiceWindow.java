package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.use_case.DeleteAllChoices;
import com.elbarcani.archi.client_service.use_case.UserServiceMenu;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class UserServiceWindow extends DisplayWindow implements UserServiceMenu {


    private static final String SEE_ALL_CHOICES = "See all choices";
    private static final String SEE_ALL_USERS_LAST_CHOICES = "See all users last choices";
    private static final String DELETE_ALL_USERS_CHOICES = "Delete all users choices";
    private Button seeAllChoicesBtn;
    private Button seeLastChoicesBtn;
    private Button deleteAllChoicesBtn;

    @Override
    protected void createControls() {
        super.createControls();
        initTexts();
    }

    @Override
    public void display() {
        open();
    }

    @Override
    public void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        seeAllChoicesBtn = new Button(mainComposite, SWT.NONE);
        seeLastChoicesBtn = new Button(mainComposite, SWT.NONE);
        deleteAllChoicesBtn = new Button(mainComposite, SWT.NONE);
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        seeAllChoicesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                seeAllSavedChoices();
            }
        });

        seeLastChoicesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                seeLastUsersChoices();
            }
        });

        deleteAllChoicesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                deleteAllChoices();
            }
        });
    }

    @Override
    public void deleteAllChoices() {
        dispose();
        DeleteAllChoices deleteWindow = new DeleteAllChoicesWindow();
        deleteWindow.display();
    }

    @Override
    public void seeAllSavedChoices() {
        dispose();
        SeeAllSavedChoicesWindow seeAllSavedChoicesWindow = new SeeAllSavedChoicesWindow();

        if (!seeAllSavedChoicesWindow.isDataExist()) {
            seeAllSavedChoicesWindow.displayNonExistentDataError();
        }
        seeAllSavedChoicesWindow.display();
    }

    @Override
    public void seeLastUsersChoices() {
        dispose();
        SeeLastUsersChoicesWindow displayWindow = new SeeLastUsersChoicesWindow();

        if (!displayWindow.isDataExist()) {
            displayWindow.displayNonExistentDataError();
        }
        displayWindow.display();
    }

    protected void initTexts() {
        seeLastChoicesBtn.setText(SEE_ALL_USERS_LAST_CHOICES);
        seeAllChoicesBtn.setText(SEE_ALL_CHOICES);
        deleteAllChoicesBtn.setText(DELETE_ALL_USERS_CHOICES);
    }
}
