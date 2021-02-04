package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.UserController;
import com.elbarcani.archi.user.use_case.ChooseInMenu;
import com.elbarcani.archi.user.use_case.SeeOldChoices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MenuWindow extends DisplayWindow implements ChooseInMenu {

    private static final String SEE_OLD_CHOICE = "See old choice";
    private static final String ADD_OR_EDIT_YOUR_CHOICES = "Add or edit your choices";
    private static final String SHOW_MY_ORDERS = "Show my orders";
    private Button showOrderBtn;
    private Button addEditChoiceBtn;
    private Button seeOldChoicesBtn;

    private User user;
    private UserController userController;

    @Override
    protected void createControls() {
        super.createControls();
        initTexts();
    }

    @Override
    public void displayMenu(){
        open();
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        userController = new UserController(user.getId());
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

    @Override
    public void seeOldChoicesButtonAction() {
        dispose();
        SeeOldChoices oldChoicesWindow = new SeeOldChoicesWindow(user);
        if (oldChoicesWindow.isFormExist()) {
            oldChoicesWindow.display();
        } else {
            oldChoicesWindow.createNonExistentFormComposite();
        }
    }

    @Override
    public void showOrderButtonAction() {
        dispose();
        ShowOrderWindow displayWindow = new ShowOrderWindow(user);
        displayWindow.display(userController);
    }

    @Override
    public void editChoiceButtonAction() {
        dispose();
        EditChoiceWindow editWindow = new EditChoiceWindow(user);
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
