package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.UserController;
import com.elbarcani.archi.user.use_case.ChooseInMenu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class UserWindow extends DisplayWindow {

    private static final String ENTER_YOUR_USER_ID = "Enter your user id";
    private Text userIdTxt;
    private int userId;

    @Override
    public void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        Label lbl = new Label(mainComposite, SWT.NONE);
        lbl.setText(ENTER_YOUR_USER_ID);
        userIdTxt = new Text(mainComposite, SWT.BORDER);
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        userIdTxt.addModifyListener(modifyEvent -> {
            if(!userIdTxt.getText().isBlank()){
                okBttn.setEnabled(true);
            }
        });
    }

    @Override
    protected void okPressed() {
        if(!userIdTxt.getText().isBlank()){
            try{
                userId = Integer.parseInt(userIdTxt.getText());
                UserController userController = new UserController(userId);
                if(userController.isUserExist()){
                    User user = userController.getUserById();
                    dispose();
                    ChooseInMenu u = new MenuWindow();
                    u.setUser(user);
                    u.displayMenu();
                } else {
                    openSuccessMessageDialog();
                }
            } catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
    }

    private void openSuccessMessageDialog() {
        MessageBox dialog =
                new MessageBox(getShell(), SWT.OK);
        dialog.setText("Something wrong");
        dialog.setMessage("This user doesn't exit or connection failed");
        dialog.open();
    }
}
