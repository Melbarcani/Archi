package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.StateDate;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.FormController;
import com.elbarcani.archi.user.use_case.SeeOldChoices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.List;

public class SeeOldChoicesWindow implements SeeOldChoices {
    private final DisplayWindow window;
    private Composite mainComposite;
    private final FormController formService;
    private List<TicketStateHistory> formHistoryList;
    private final User user;

    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";

    public SeeOldChoicesWindow(User user) {
        window = new DisplayWindow();
        this.user = user;
        formService = new FormController(FORM_FILE_TEXT + TEXT_EXTENSION);
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

    @Override
    public boolean isFormExist() {
        return formService.isFormDataExist();
    }

    @Override
    public void loadFormHistory() {
        formHistoryList = formService.loadFormHistory(user.getId());
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
        mainComposite.layout();
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

    @Override
    public void createNonExistentFormComposite() {
        MessageBox dialog =
                new MessageBox(window.getShell(), SWT.OK);
        dialog.setText("Non existent form");
        dialog.setMessage("You don't have filled your form yet!");
        dialog.open();
    }

    @Override
    public void display() {

        window.setTitle(String.valueOf(user.getId()));
        window.getResetBtn().setVisible(true);
        window.open();
    }


}
