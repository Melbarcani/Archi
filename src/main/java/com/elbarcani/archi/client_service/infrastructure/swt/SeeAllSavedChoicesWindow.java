package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.domaine.ChoiceDate;
import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.domaine.TicketHistory;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesQueryDao;
import com.elbarcani.archi.client_service.use_case.LoadAllSavedChoices;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

public class SeeAllSavedChoicesWindow {

    private static final String USER_LABEL = "User : ";
    private static final String TICKET_LABEL = "Ticket : ";
    private static final String ALL_USERS_CHOICES = "All Users Choices";

    private final DisplayWindow window;
    private Composite mainComposite;

    private final ChoicesQueryDao choicesQueryDao;
    private List<TicketHistory> choicesHistoryList;

    public SeeAllSavedChoicesWindow() {
        window= new DisplayWindow();
        choicesQueryDao = new InMemoryChoicesQueryDao();
        loadAllSavedChoices();
        createComposites();
        addListeners();
        window.setTitle(ALL_USERS_CHOICES);
    }

    public void loadAllSavedChoices() {
        LoadAllSavedChoices loadAllChoices = new LoadAllSavedChoices(choicesQueryDao);
        choicesHistoryList = loadAllChoices.execute();
    }

    public void open() {
        window.getResetBtn().setVisible(true);
        window.getOkBttn().setVisible(false);
        window.open();
    }

    private void createComposites() {
        mainComposite = window.getMainComposite();
        createHistoryComposite();
    }

    private void addListeners() {
        window.getResetBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                returnToMenu();
            }
        });
    }

    private void returnToMenu() {
        window.dispose();
        UserServiceWindow userServiceWindow = new UserServiceWindow();
        userServiceWindow.display();
    }

    private void createHistoryComposite() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.horizontalSpacing = 20;
        mainComposite.setLayout(gridLayout);

        createTicketsHistoriesComposites();
        mainComposite.layout();
    }

    private void createTicketsHistoriesComposites() {
        for (TicketHistory stateHistory : choicesHistoryList) {
            Composite ticketComposite = new Composite(mainComposite, SWT.NONE);
            ticketComposite.setBackground(window.getTitleComposite().getBackground());
            createCompositeLayout(stateHistory, ticketComposite);
            populateStateDateLabels(stateHistory, ticketComposite);
        }
    }

    private void populateStateDateLabels(TicketHistory stateHistory, Composite ticketComposite) {
        for (ChoiceDate state : stateHistory.getStateDateList()) {
            Label stateLabel = new Label(ticketComposite, SWT.None);
            Label dateLabel = new Label(ticketComposite, SWT.None);
            stateLabel.setText(state.getState());
            dateLabel.setText(state.getDate());
        }
    }

    private void createCompositeLayout(TicketHistory stateHistory, Composite parent) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        parent.setLayout(gridLayout);

        Label userLabel = new Label(parent, SWT.None);
        userLabel.setText(USER_LABEL);
        Label ticketIdLabel = new Label(parent, SWT.None);
        ticketIdLabel.setText(String.valueOf(stateHistory.getUserId()));

        Label ticketLabel = new Label(parent, SWT.None);
        ticketLabel.setText(TICKET_LABEL);
        Label userIdLabel = new Label(parent, SWT.None);
        userIdLabel.setText(String.valueOf(stateHistory.getTicketId()));
    }
}
