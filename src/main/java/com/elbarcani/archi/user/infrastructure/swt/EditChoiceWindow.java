package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.*;
import com.elbarcani.archi.user.infrastructure.dao.HttpTicketDao;
import com.elbarcani.archi.user.infrastructure.dao.InMemoryFormDao;
import com.elbarcani.archi.user.infrastructure.service.ConsoleEmail;
import com.elbarcani.archi.user.use_case.EditForm;
import com.elbarcani.archi.user.use_case.LoadForm;
import com.elbarcani.archi.user.use_case.SendEmailAfterAddOrEdit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class EditChoiceWindow {
    public static final String YOUR_CHOICE_LABEL = "Your choice";
    public static final String KEEP_TICKET_TEXT = "Keep";
    public static final String REIMBURSE_TICKET_TEXT = "Reimburse";

    private EditForm addOrEditForm;
    private LoadForm loadForm;
    private Form form;
    private final User user;
    private TicketDao ticketDao;
    private FormDao formDao;

    private Label incompleteFormLabel;
    private final DisplayWindow window;
    private Composite mainComposite;

    public EditChoiceWindow(User user) {
        window = new DisplayWindow();
        this.user = user;
        initDaos(user);
        initUseCases(user);
        createComposites();
        addListeners();
    }

    private void initUseCases(User user) {
        addOrEditForm = new EditForm(user, formDao);
        loadForm = new LoadForm(user, formDao, ticketDao);
    }

    private void initDaos(User user) {
        formDao = new InMemoryFormDao();
        ticketDao = new HttpTicketDao(user.getId());
    }

    public void open() {
        window.setTitle(String.valueOf(user.getId()));
        form = loadForm.execute();

        populateComposite();

        mainComposite.layout();
        window.getResetBtn().setVisible(true);
        window.open();
    }

    private void populateComposite() {
        for (Ticket ticket : form.getTicketsList()) {
            Composite ticketComposite = new Composite(mainComposite, SWT.NONE);
            populateTicketLabels(ticket, ticketComposite);
            createRadioButtons(ticket, ticketComposite);
        }
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
                    form.getTicketsState().put(ticket.getId(), source.getText());
                }
            }
        });
    }

    private void okPressed() {
        if (mainComposite.getChildren().length != form.getTicketsState().size()) {
            displayFormIncompleteError();
        } else {
            if (incompleteFormLabel != null) {
                incompleteFormLabel.setVisible(false);
            }
            addFormAction();
        }
    }

    private void addFormAction() {
        addOrEditForm.execute(form);
        Email email = new ConsoleEmail(user);
        SendEmailAfterAddOrEdit sendEmail = new SendEmailAfterAddOrEdit(email);
        if(sendEmail.execute()){
            openSuccessMessageDialog();
        }
        returnToUserChoices();
    }

    private void populateTicketLabels(Ticket ticket, Composite ticketComposite) {
        GridLayout layout = new GridLayout(4, false);
        ticketComposite.setLayout(layout);
        Label ticketIdLbl = new Label(ticketComposite, SWT.NONE);
        ticketIdLbl.setText(String.valueOf(ticket.getId()));
        Label ticketPriceLbl = new Label(ticketComposite, SWT.NONE);
        ticketPriceLbl.setText(String.valueOf(ticket.getPrice()));
        Label userIdLbl = new Label(ticketComposite, SWT.NONE);
        userIdLbl.setText(String.valueOf(ticket.getUserId()));
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
        MenuWindow u = new MenuWindow(user);
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

    private void createRadioButtons(Ticket ticket, Composite ticketComposite) {
        Group genderGroup = new Group(ticketComposite, SWT.NONE);
        genderGroup.setLayout(new RowLayout(SWT.HORIZONTAL));
        genderGroup.setData(ticket);

        Label label = new Label(genderGroup, SWT.NONE);
        label.setText(YOUR_CHOICE_LABEL);

        Button keepButton = new Button(genderGroup, SWT.RADIO);
        keepButton.setText(KEEP_TICKET_TEXT);
        keepButton.setSelection(KEEP_TICKET_TEXT.equals(form.getTicketsState().get(ticket.getId())));

        Button reimburseButton = new Button(genderGroup, SWT.RADIO);
        reimburseButton.setText(REIMBURSE_TICKET_TEXT);
        reimburseButton.setSelection(REIMBURSE_TICKET_TEXT.equals(form.getTicketsState().get(ticket.getId())));

        addRadioListeners(keepButton);
        addRadioListeners(reimburseButton);
    }

}
