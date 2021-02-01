package com.elbarcani.archi.gui.swt.user;

import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.gui.swt.user.UserChoices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
                User user = userController.getUserById(userId);
                dispose();
                UserChoices u = new UserChoices();
                u.setUser(user);
                u.open();
            } catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
    }
}
