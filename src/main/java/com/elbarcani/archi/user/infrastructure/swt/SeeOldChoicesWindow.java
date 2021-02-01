package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.gui.swt.user.UserWindow;
import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.StateDate;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import com.elbarcani.archi.user.infrastructure.service.FormService;
import com.elbarcani.archi.user.use_case.SeeOldChoices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

public class SeeOldChoicesWindow implements SeeOldChoices {
    private int userId;
    private final DisplayWindow window;
    private Composite mainComposite;
    private Form form;
    private FormService formService;
    List<TicketStateHistory> formHistoryList;

    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";

    public SeeOldChoicesWindow() {
        window= new DisplayWindow();
        createComposites();
        addListeners();
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
    public void setUser(int userId){
        this.userId = userId;
    }

    @Override
    public boolean isFormExist(){
        formService = new FormService(FORM_FILE_TEXT + TEXT_EXTENSION);
        return formService.isFormFileExist();
    }

    @Override
    public void loadFormHistory() {
        if(isFormExist()){
            formHistoryList = formService.loadFormHistory(userId);
            createHistoryComposite();
        }
    }

    private void createHistoryComposite() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 20;
        mainComposite.setLayout(gridLayout);

        for(TicketStateHistory stateHistory : formHistoryList){
            createTicketHistoryComposite(mainComposite, stateHistory);
        }
        mainComposite.layout();
    }

    private void createTicketHistoryComposite(Composite parent, TicketStateHistory stateHistory) {
        Composite ticketComposite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        ticketComposite.setLayout(gridLayout);
        Label ticketIdLabel = new Label(ticketComposite, SWT.None);
        ticketIdLabel.setText(String.valueOf(stateHistory.getTicketId()));
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        ticketIdLabel.setLayoutData(gridData);
        for(StateDate state : stateHistory.getStateDateList()){
            Label stateLabel = new Label(ticketComposite, SWT.None);
            Label dateLabel = new Label(ticketComposite, SWT.None);
            stateLabel.setText(state.getState());
            dateLabel.setText(state.getDate());
        }
    }

    @Override
    public void createNonExistentFormComposite() {

    }

    @Override
    public void display() {
        window.setTitle(String.valueOf(userId));
        window.getResetBtn().setVisible(true);
        window.open();
    }


}
