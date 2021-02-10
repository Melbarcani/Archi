package com.elbarcani.archi.client_service.infrastructure.swt;

import com.elbarcani.archi.client_service.domaine.ChoicesCommandDao;
import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesCommandDao;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesQueryDao;
import com.elbarcani.archi.client_service.use_case.DeleteAllChoices;
import com.elbarcani.archi.infrastructure.swt.DisplayWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DeleteAllChoicesWindow{
    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";
    public static final String SUCCESS_MESSAGE = "Choices deleted successfully";
    public static final String FAIL_MESSAGE = "Choices deletion failed";

    private final DisplayWindow window;
    private ChoicesQueryDao choicesQueryDao;
    private ChoicesCommandDao choicesCommandDao;

    private Label messageLabel;

    public DeleteAllChoicesWindow() {
        window = new DisplayWindow();
        choicesCommandDao = new InMemoryChoicesCommandDao();
        choicesQueryDao = new InMemoryChoicesQueryDao();
        createComposites();
        addListeners();
    }

    public void open() {
        DeleteAllChoices deleteAllChoices = new DeleteAllChoices(choicesQueryDao, choicesCommandDao);
        messageLabel.setText(deleteAllChoices.execute()
                ? SUCCESS_MESSAGE
                : FAIL_MESSAGE);
        window.getResetBtn().setVisible(true);
        window.open();
    }

    private void createComposites() {
        Composite mainComposite = window.getMainComposite();
        messageLabel = new Label(mainComposite, SWT.NONE);
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
}
