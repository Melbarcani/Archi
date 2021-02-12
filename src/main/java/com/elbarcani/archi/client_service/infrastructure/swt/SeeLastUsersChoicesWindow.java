package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesQueryDao;
import com.elbarcani.archi.client_service.use_case.LoadAllSavedChoices;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.client_service.domaine.TicketHistory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

public class SeeLastUsersChoicesWindow {
    private static final String NO_DATA_MSG = "There is no registred data";

    private static final String USERS_LAST_CHOICES = "Users last choices";
    private static final String USER_ID = "userId";
    private static final String ANSWER = "Answer";
    private static final String DATE = "Date";
    private static final String ID = "Id";

    private final ChoicesQueryDao choicesQueryDao;

    private final DisplayWindow window;
    private Composite mainComposite;

    public SeeLastUsersChoicesWindow() {
        window = new DisplayWindow();
        choicesQueryDao = new InMemoryChoicesQueryDao();
        createComposites();
        addListeners();
        window.setTitle(USERS_LAST_CHOICES);
    }

    private void createComposites() {
        mainComposite = window.getMainComposite();
        createMainCompositeLayout();
        createTitles();
    }

    private void addListeners() {
        window.getResetBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                window.dispose();
                UserServiceWindow userServiceWindow = new UserServiceWindow();
                userServiceWindow.display();
            }
        });
    }

    public void display() {
        createHistoryComposite();
        mainComposite.layout();
        window.getResetBtn().setVisible(true);
        window.getOkBttn().setVisible(false);
        window.open();
    }

    private void createHistoryComposite() {
        LoadAllSavedChoices loadAllSavedChoices = new LoadAllSavedChoices(choicesQueryDao);
        List<TicketHistory> choicesHistory = loadAllSavedChoices.execute();

        for (TicketHistory history : choicesHistory) {
            populateLabels(history);
        }
        noDataAction(choicesHistory);
    }


    private void noDataAction(List<TicketHistory> choicesHistory) {
        if(choicesHistory.isEmpty()){
            Label noDataLbl = new Label(mainComposite, SWT.NONE);
            noDataLbl.setText(NO_DATA_MSG);
        }

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
}
