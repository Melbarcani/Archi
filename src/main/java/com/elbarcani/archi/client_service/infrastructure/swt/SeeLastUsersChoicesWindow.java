package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.infrastructure.controller.ChoicesController;
import com.elbarcani.archi.client_service.use_case.SeeLastUserChoices;
import com.elbarcani.archi.client_service.use_case.UserServiceMenu;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.client_service.domaine.TicketHistory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.List;

public class SeeLastUsersChoicesWindow implements SeeLastUserChoices {
    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";
    private static final String USERS_LAST_CHOICES = "Users last choices";
    private static final String USER_ID = "userId";
    private static final String ANSWER = "Answer";
    private static final String DATE = "Date";
    private static final String ID = "Id";
    private static final String NON_EXISTENT_DATA = "Non existent data";
    private static final String THERE_IS_NO_SAVED_DATA_YET = "There is no saved data yet!";

    private final ChoicesController choicesService;

    private final DisplayWindow window;
    private Composite mainComposite;

    public SeeLastUsersChoicesWindow() {
        window = new DisplayWindow();
        choicesService = new ChoicesController(FORM_FILE_TEXT + TEXT_EXTENSION);
        createComposites();
        addListeners();
        window.setTitle(USERS_LAST_CHOICES);
    }

    private void createComposites() {
        mainComposite = window.getMainComposite();
    }

    private void addListeners() {
        window.getResetBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                window.dispose();
                UserServiceMenu userServiceWindow = new UserServiceWindow();
                userServiceWindow.display();
            }
        });
    }

    @Override
    public void display() {
        createMainCompositeLayout();
        createTitles();

        List<TicketHistory> choicesHistory = choicesService.loadChoicesHistory();
        for (TicketHistory history : choicesHistory) {
            populateLabels(history);
        }
        mainComposite.layout();
        window.getResetBtn().setVisible(true);
        window.open();
    }

    private void createMainCompositeLayout() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        gridLayout.horizontalSpacing = 20;
        mainComposite.setLayout(gridLayout);
    }

    private void createTitles() {
        Label ticketIdTitleLabel = new Label(mainComposite, SWT.NONE);
        ticketIdTitleLabel.setText(ID);
        Label userIdTitleLabel = new Label(mainComposite, SWT.NONE);
        userIdTitleLabel.setText(USER_ID);
        Label lastChoiceTitleLabel = new Label(mainComposite, SWT.NONE);
        lastChoiceTitleLabel.setText(ANSWER);
        Label lastDateChoiceTitleLabel = new Label(mainComposite, SWT.NONE);
        lastDateChoiceTitleLabel.setText(DATE);
    }

    private void populateLabels(TicketHistory history) {
        Label ticketIdLabel = new Label(mainComposite, SWT.NONE);
        ticketIdLabel.setText(String.valueOf(history.getTicketId()));
        Label userIdLabel = new Label(mainComposite, SWT.NONE);
        userIdLabel.setText(String.valueOf(history.getUserId()));
        Label lastChoiceLabel = new Label(mainComposite, SWT.NONE);
        lastChoiceLabel.setText(history.getStateDateList().get(history.getStateDateList().size() - 1).getState());
        Label lastDateChoiceLabel = new Label(mainComposite, SWT.NONE);
        lastDateChoiceLabel.setText(history.getStateDateList().get(history.getStateDateList().size() - 1).getDate());
    }

    public boolean isDataExist() {
        return choicesService.isDataExist();
    }

    @Override
    public void displayNonExistentDataError() {
        MessageBox dialog =
                new MessageBox(window.getShell(), SWT.OK);
        dialog.setText(NON_EXISTENT_DATA);
        dialog.setMessage(THERE_IS_NO_SAVED_DATA_YET);
        dialog.open();
    }
}
