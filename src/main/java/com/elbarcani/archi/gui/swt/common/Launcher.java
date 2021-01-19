package com.elbarcani.archi.gui.swt.common;

import com.elbarcani.archi.gui.swt.user.UserWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class Launcher extends DisplayWindow{

    Button userButton;
    Button adminButton;

    public Launcher() {
        super();
        super.open();
    }

    @Override
    protected void createTitleComposite(Composite parent) {
        super.createTitleComposite(parent);
    }

    @Override
    protected void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        userButton = new Button(mainComposite, SWT.BUTTON1 );
        userButton.setText("User");
        adminButton = new Button(mainComposite, SWT.BUTTON1 );
        adminButton.setText("Admin");
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
