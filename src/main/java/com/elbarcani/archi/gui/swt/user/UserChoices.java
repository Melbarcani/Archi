package com.elbarcani.archi.gui.swt.user;

import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.swt.EditChoiceWindow;
import com.elbarcani.archi.user.infrastructure.swt.SeeOldChoicesWindow;
import com.elbarcani.archi.user.infrastructure.swt.ShowOrderWindow;
import com.elbarcani.archi.user.use_case.SeeOldChoices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class UserChoices extends DisplayWindow {

    private static final String SEE_OLD_CHOICE = "See old choice";
    private static final String ADD_OR_EDIT_YOUR_CHOICES = "Add or edit your choices";
    private static final String SHOW_MY_ORDERS = "Show my orders";
    private Button showOrderBtn;
    private Button addEditChoiceBtn;
    private Button seeOldChoicesBtn;

    private User user;

    @Override
    protected void createControls() {
        super.createControls();
        initTexts();
    }

    public void setUser(User user) {
        this.user = user;
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
        SeeOldChoices oldChoicesWindow = new SeeOldChoicesWindow();
        oldChoicesWindow.setUser(user.getId());
        if (oldChoicesWindow.isFormExist()) {
            oldChoicesWindow.loadFormHistory();
            oldChoicesWindow.display();
        } else {
            oldChoicesWindow.createNonExistentFormComposite();
        }
    }

    private void showOrderButtonAction() {
        dispose();
        ShowOrderWindow displayWindow = new ShowOrderWindow();
        displayWindow.setUser(user.getId());
        displayWindow.display(userController);
    }

    private void editChoiceButtonAction() {
        dispose();
        EditChoiceWindow editWindow = new EditChoiceWindow();
        editWindow.setUser(user.getId());
        if (!editWindow.isFormExist()) {
            editWindow.createNewForm(userController);
        }
        editWindow.display(userController);
    }

    protected void initTexts() {
        seeOldChoicesBtn.setText(SEE_OLD_CHOICE);
        addEditChoiceBtn.setText(ADD_OR_EDIT_YOUR_CHOICES);
        showOrderBtn.setText(SHOW_MY_ORDERS);
    }
}
