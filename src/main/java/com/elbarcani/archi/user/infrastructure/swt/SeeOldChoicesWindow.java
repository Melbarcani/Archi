package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.StateDate;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.dao.InMemoryFormDao;
import com.elbarcani.archi.user.use_case.LoadTicketHistory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

public class SeeOldChoicesWindow {
    private static final String NO_DATA_MSG = "There is no registred data for you";
    private final DisplayWindow window;
    private Composite mainComposite;
    private List<TicketStateHistory> formHistoryList;
    private final User user;

    public SeeOldChoicesWindow(User user) {
        window = new DisplayWindow();
        this.user = user;
        createComposites();
        addListeners();
        loadFormHistory();
    }

    private void addListeners() {
        window.getResetBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                window.dispose();
                UserWindow userWindow = new UserWindow();
                userWindow.open();
            }
        });
    }

    private void createComposites() {
        mainComposite = window.getMainComposite();
    }

    public void loadFormHistory() {
        FormDao formDao = new InMemoryFormDao();
        LoadTicketHistory loadTicketHistory = new LoadTicketHistory(user, formDao);
        formHistoryList = loadTicketHistory.execute();
        createHistoryComposite();
    }

    private void createHistoryComposite() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 20;
        mainComposite.setLayout(gridLayout);

        for (TicketStateHistory stateHistory : formHistoryList) {
            createTicketHistoryComposite(mainComposite, stateHistory);
        }
        noDataAction();
        mainComposite.layout();
    }

    private void noDataAction() {
        if (formHistoryList.isEmpty()) {
            Label noDataLbl = new Label(mainComposite, SWT.NONE);
            noDataLbl.setText(NO_DATA_MSG);
        }
    }

    private void createTicketHistoryComposite(Composite parent, TicketStateHistory stateHistory) {
        Composite ticketComposite = new Composite(parent, SWT.NONE);
        createCompositeLayout(stateHistory, ticketComposite);
        for (StateDate state : stateHistory.getStateDateList()) {
            Label stateLabel = new Label(ticketComposite, SWT.None);
            Label dateLabel = new Label(ticketComposite, SWT.None);
            stateLabel.setText(state.getState());
            dateLabel.setText(state.getDate());
        }
    }

    private void createCompositeLayout(TicketStateHistory stateHistory, Composite ticketComposite) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        ticketComposite.setLayout(gridLayout);
        Label ticketIdLabel = new Label(ticketComposite, SWT.None);
        ticketIdLabel.setText(String.valueOf(stateHistory.getTicketId()));
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        ticketIdLabel.setLayoutData(gridData);
    }

    public void open() {
        window.setTitle(String.valueOf(user.getId()));
        window.getResetBtn().setVisible(true);
        window.open();
    }


}
