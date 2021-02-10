package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

public class MenuWindow extends DisplayWindow {

    private static final String SEE_OLD_CHOICE = "See old choice";
    private static final String ADD_OR_EDIT_YOUR_CHOICES = "Add or edit your choices";
    private static final String SHOW_MY_ORDERS = "Show my orders";
    private Button showOrderBtn;
    private Button addEditChoiceBtn;
    private Button seeOldChoicesBtn;

    private User user;


    public MenuWindow(User user) {
        this.user = user;
    }

    @Override
    protected void createControls() {
        super.createControls();
        initTexts();
    }

    public void open() {
        super.open();
    }

    @Override
    public void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        showOrderBtn = new Button(mainComposite, SWT.NONE);
        addEditChoiceBtn = new Button(mainComposite, SWT.NONE);
        seeOldChoicesBtn = new Button(mainComposite, SWT.NONE);
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        showOrderBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                showOrderButtonAction();
            }
        });

        addEditChoiceBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                editChoiceButtonAction();
            }
        });

        seeOldChoicesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                seeOldChoicesButtonAction();
            }
        });
    }

    private void seeOldChoicesButtonAction() {
        dispose();
        SeeOldChoicesWindow oldChoicesWindow = new SeeOldChoicesWindow(user);
        oldChoicesWindow.open();

    }

    private void createNonExistentFormComposite() {
        MessageBox dialog =
                new MessageBox(getShell(), SWT.OK);
        dialog.setText("Non existent form");
        dialog.setMessage("You don't have filled your form yet!");
        dialog.open();
    }

    private void showOrderButtonAction() {
        dispose();
        ShowOrderWindow displayOrderWindow = new ShowOrderWindow(user);
        displayOrderWindow.open();
    }

    private void editChoiceButtonAction() {
        dispose();
        EditChoiceWindow editWindow = new EditChoiceWindow(user);
        editWindow.display();
    }

    protected void initTexts() {
        seeOldChoicesBtn.setText(SEE_OLD_CHOICE);
        addEditChoiceBtn.setText(ADD_OR_EDIT_YOUR_CHOICES);
        showOrderBtn.setText(SHOW_MY_ORDERS);
    }
}
