package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.infrastructure.controller.ChoicesController;
import com.elbarcani.archi.client_service.use_case.DeleteAllChoices;
import com.elbarcani.archi.client_service.use_case.UserServiceMenu;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DeleteAllChoicesWindow implements DeleteAllChoices {
    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";
    public static final String SUCCESS_MESSAGE = "Choices deleted successfully";
    public static final String FAIL_MESSAGE = "Choices deletion failed";

    private final DisplayWindow window;
    private final ChoicesController choicesController;

    private Composite mainComposite;
    private Label messageLabel;

    public DeleteAllChoicesWindow() {
        window= new DisplayWindow();

        choicesController = new ChoicesController(FORM_FILE_TEXT + TEXT_EXTENSION);
        createComposites();
        addListeners();
    }
    @Override
    public void display() {
        messageLabel.setText(choicesController.deleteChoices() ?
                SUCCESS_MESSAGE : FAIL_MESSAGE);
        window.getResetBtn().setVisible(true);
        window.open();
    }

    private void createComposites() {
        mainComposite = window.getMainComposite();
        messageLabel = new Label(mainComposite, SWT.NONE);
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
}
