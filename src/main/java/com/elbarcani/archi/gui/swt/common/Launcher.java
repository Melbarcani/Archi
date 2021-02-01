package com.elbarcani.archi.gui.swt.common;

import com.elbarcani.archi.gui.swt.user.UserWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class Launcher extends DisplayWindow{

    public static final String USER = "User";
    public static final String ADMIN = "Admin";
    Button userButton;
    Button adminButton;

    public Launcher() {
        super();
        super.open();
    }

    @Override
    public void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        userButton = new Button(mainComposite, SWT.BUTTON1 );
        userButton.setText(USER);
        adminButton = new Button(mainComposite, SWT.BUTTON1 );
        adminButton.setText(ADMIN);
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        userButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                dispose();
                UserWindow userWindow = new UserWindow();
                userWindow.open();
            }
        });
    }
}
