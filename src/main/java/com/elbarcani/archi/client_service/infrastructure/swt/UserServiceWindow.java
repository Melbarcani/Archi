package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesQueryDao;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

public class UserServiceWindow extends DisplayWindow {

    private static final String SEE_ALL_CHOICES = "See all choices";
    private static final String SEE_ALL_USERS_LAST_CHOICES = "See all users last choices";
    private static final String DELETE_ALL_USERS_CHOICES = "Delete all users choices";
    private static final String NON_EXISTENT_DATA = "Non existent data";
    private static final String THERE_IS_NO_SAVED_DATA_YET = "There is no saved data yet!";
    private Button seeAllChoicesBtn;
    private Button seeLastChoicesBtn;
    private Button deleteAllChoicesBtn;

    private ChoicesQueryDao choicesQueryDao;

    public UserServiceWindow() {
        choicesQueryDao = new InMemoryChoicesQueryDao();
    }

    @Override
    protected void createControls() {
        super.createControls();
        initTexts();
    }

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

    public void deleteAllChoices() {
        dispose();
        DeleteAllChoicesWindow deleteWindow = new DeleteAllChoicesWindow();
        deleteWindow.open();
    }

    public void seeAllSavedChoices() {
            dispose();
            SeeAllSavedChoicesWindow seeAllSavedChoicesWindow = new SeeAllSavedChoicesWindow();
            seeAllSavedChoicesWindow.open();
    }

    public void seeLastUsersChoices() {
            dispose();
            SeeLastUsersChoicesWindow displayWindow = new SeeLastUsersChoicesWindow();
            displayWindow.display();
    }

    private void displayNonExistentDataError() {
        MessageBox dialog =
                new MessageBox(getShell(), SWT.OK);
        dialog.setText(NON_EXISTENT_DATA);
        dialog.setMessage(THERE_IS_NO_SAVED_DATA_YET);
        dialog.open();
    }

    protected void initTexts() {
        seeLastChoicesBtn.setText(SEE_ALL_USERS_LAST_CHOICES);
        seeAllChoicesBtn.setText(SEE_ALL_CHOICES);
        deleteAllChoicesBtn.setText(DELETE_ALL_USERS_CHOICES);
    }
}
