package com.elbarcani.archi.infrastructure.swt;

import com.elbarcani.Launcher;
import com.elbarcani.archi.client_service.infrastructure.swt.UserServiceWindow;
import com.elbarcani.archi.client_service.use_case.UserServiceMenu;
import com.elbarcani.archi.user.infrastructure.swt.UserWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class LauncherWindow extends DisplayWindow implements Launcher {

    public static final String USER = "User";
    public static final String ADMIN = "User Service";
    Button userButton;
    Button userServiceButton;

    public LauncherWindow() {
        super();
        super.open();
    }

    @Override
    public void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
        userButton = new Button(mainComposite, SWT.BUTTON1 );
        userButton.setText(USER);
        userServiceButton = new Button(mainComposite, SWT.BUTTON1 );
        userServiceButton.setText(ADMIN);
    }

    @Override
    protected void addListeners() {
        super.addListeners();
        userButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                openUserMenu();
            }
        });
        userServiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                openUserServiceMenu();
            }
        });
    }

    public void openUserServiceMenu() {
        dispose();
        UserServiceMenu adminMenuWindow = new UserServiceWindow();
        adminMenuWindow.display();
    }

    public void openUserMenu() {
        dispose();
        UserWindow userWindow = new UserWindow();
        userWindow.open();
    }
}
