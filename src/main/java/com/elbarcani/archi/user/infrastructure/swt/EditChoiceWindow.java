package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.gui.swt.user.UserChoices;
import com.elbarcani.archi.gui.swt.user.UserWindow;
import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.UserController;
import com.elbarcani.archi.user.infrastructure.service.FormService;
import com.elbarcani.archi.user.use_case.AddOrEditForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EditChoiceWindow implements AddOrEditForm {
    public static final String YOUR_CHOICE_LABEL = "Your choice";
    public static final String KEEP_TICKET_TEXT = "Keep";
    public static final String REIMBURSE_TICKET_TEXT = "Reimburse";

    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";

    private int userId;
    private Form form;
    private Map<Integer, String> ticketState;
    private FormService formService;
    private User user;

    private Label incompleteFormLabel;
    private final DisplayWindow window;
    private Composite mainComposite;

    public EditChoiceWindow() {
        window = new DisplayWindow();
        createComposites();
        addListeners();
    }

    private void addListeners() {
        window.getOkBttn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                okPressed();
            }
        });
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
    public void setUser(int userId) {
        this.userId = userId;
    }

    @Override
    public void display(UserController userController) {
        window.setTitle(String.valueOf(userId));
        if(isFormExist()){
            loadPrecedentForm(userController);
        }
        if (form.getTicketsList().isEmpty()) {
            createNewForm(userController);
        }
        for (Ticket ticket : form.getTicketsList()) {
            Composite ticketComposite = new Composite(mainComposite, SWT.NONE);
            populateTicketsComposite(ticket, ticketComposite);
            createRadioButtons(ticket, ticketComposite);
        }
        mainComposite.layout();
        window.getResetBtn().setVisible(true);
        window.open();
    }

    @Override
    public boolean isFormExist(){
        formService = new FormService(FORM_FILE_TEXT + TEXT_EXTENSION);
        return formService.isFormFileExist();
    }

    private void populateTicketsComposite(Ticket ticket, Composite ticketComposite) {
        GridLayout layout = new GridLayout(4, false);
        ticketComposite.setLayout(layout);
        Label ticketIdLbl = new Label(ticketComposite, SWT.NONE);
        ticketIdLbl.setText(String.valueOf(ticket.getTicketId()));
        Label ticketPriceLbl = new Label(ticketComposite, SWT.NONE);
        ticketPriceLbl.setText(String.valueOf(ticket.getPrice()));
        Label userIdLbl = new Label(ticketComposite, SWT.NONE);
        userIdLbl.setText(String.valueOf(ticket.getUserId()));
    }

    private void createRadioButtons(Ticket ticket, Composite ticketComposite) {
        Group genderGroup = new Group(ticketComposite, SWT.NONE);
        genderGroup.setLayout(new RowLayout(SWT.HORIZONTAL));
        genderGroup.setData(ticket);

        Label label = new Label(genderGroup, SWT.NONE);
        label.setText(YOUR_CHOICE_LABEL);

        Button keepButton = new Button(genderGroup, SWT.RADIO);
        keepButton.setText(KEEP_TICKET_TEXT);
        keepButton.setSelection(KEEP_TICKET_TEXT.equals(form.getTicketsState().get(ticket.getTicketId())));

        Button reimburseButton = new Button(genderGroup, SWT.RADIO);
        reimburseButton.setText(REIMBURSE_TICKET_TEXT);
        reimburseButton.setSelection(REIMBURSE_TICKET_TEXT.equals(form.getTicketsState().get(ticket.getTicketId())));

        addRadioListeners(keepButton);
        addRadioListeners(reimburseButton);
    }

    @Override
    public void createNewForm(UserController userController) {
        List<Ticket> tickets = userController.getOrderByUser(userId);
        user = userController.getUserById(userId);
        ticketState = new TreeMap<>();
        form = new Form(ticketState, tickets);
        formService = new FormService(FORM_FILE_TEXT + TEXT_EXTENSION);
    }

    @Override
    public void loadPrecedentForm(UserController userController) {
        user = userController.getUserById(userId);
        formService = new FormService(FORM_FILE_TEXT + TEXT_EXTENSION);
        form = formService.loadForm(userId);
        ticketState = form.getTicketsState();
    }

    private void addRadioListeners(Button choiceButton) {
        choiceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source = (Button) e.getSource();
                if (source.getSelection()) {
                    window.getOkBttn().setEnabled(true);
                    if (incompleteFormLabel != null) {
                        incompleteFormLabel.setVisible(false);
                    }
                    Ticket ticket = (Ticket) source.getParent().getData();
                    ticketState.put(ticket.getTicketId(), source.getText());
                }
            }
        });
    }

    private void okPressed() {
        if (mainComposite.getChildren().length != ticketState.size()) {
            displayFormIncompleteError();
        } else {
            if (incompleteFormLabel != null) {
                incompleteFormLabel.setVisible(false);
            }
            formService.saveForm(form, userId);

            openSuccessMessageDialog();
            returnToUserChoices();
        }
    }

    private void openSuccessMessageDialog() {
        MessageBox dialog =
                new MessageBox(window.getShell(), SWT.OK);
        dialog.setText("Changes saved");
        dialog.setMessage("An email has been sent to your mailbox");
        dialog.open();
    }

    private void returnToUserChoices() {
        window.dispose();
        UserChoices u = new UserChoices();
        u.setUser(user);
        u.open();
    }

    private void displayFormIncompleteError() {
        if (incompleteFormLabel == null) {
            incompleteFormLabel = new Label(window.getButtonComposite(), SWT.NONE);
        }
        incompleteFormLabel.setVisible(true);
        incompleteFormLabel.setText("Form Incomplete");
        window.getButtonComposite().layout();
    }
}
