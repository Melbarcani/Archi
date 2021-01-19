package com.elbarcani.archi.gui.swt.user;

import com.elbarcani.archi.gui.service.UserSWTUIService;
import com.elbarcani.archi.gui.service.UserUIService;
import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UserWindow extends DisplayWindow {

    private Text userIdTxt;
    private int userId;

    @Override
    protected void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        Label lbl = new Label(mainComposite, SWT.NONE);
        lbl.setText("Enter your user id");
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
                User user = userUIService.getUserById(userId);
                dispose();
                UserChoices u = new UserChoices();
                u.setUser(user);
                u.open();
            } catch(NumberFormatException e){
                Label label = new Label(mainComposite, SWT.NONE);
                label.setText("Try again");
            }
        }
    }
}
