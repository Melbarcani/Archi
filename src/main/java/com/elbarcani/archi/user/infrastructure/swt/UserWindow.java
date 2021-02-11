package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.domain.UserDao;
import com.elbarcani.archi.user.infrastructure.dao.HttpUserDao;
import com.elbarcani.archi.user.use_case.LoadUser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import java.util.Optional;

public class UserWindow extends DisplayWindow {

    private static final String ENTER_YOUR_USER_ID = "Enter your user id";
    private Text userIdTxt;
    private UserDao userDao;
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
            if (!userIdTxt.getText().isBlank()) {
                okBttn.setEnabled(true);
            }
        });
    }

    @Override
    protected void okPressed() {
        if (!userIdTxt.getText().isBlank()) {
            try {
                userId = Integer.parseInt(userIdTxt.getText());
                UserDao userDao = new HttpUserDao(userId);
                LoadUser loadUser = new LoadUser(userDao);
                Optional<User> user = loadUser.execute();
                user.ifPresentOrElse(
                        this::openUserWindow, this::openFailMessageDialog
                );
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void openUserWindow(User user) {
        dispose();
        MenuWindow menuWindow = new MenuWindow(user);
        menuWindow.open();
    }

    private void openFailMessageDialog() {
        MessageBox dialog =
                new MessageBox(getShell(), SWT.OK);
        dialog.setText("Something wrong");
        dialog.setMessage("This user doesn't exit or connection failed");
        dialog.open();
    }
}
