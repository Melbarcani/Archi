package com.elbarcani.archi.gui.swt.user;

import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.user.domain.User;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class UserChoices extends DisplayWindow {

    private Button showOrderBtn;
    private Button addEditChoiceBtn;
    private Button seeOldChoicesBtn;

    private User user;

    @Override
    protected void createControls() {
        super.createControls();
        initTexts();
    }

    protected void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void createMainComposite(Composite parent) {
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
                dispose();
                ShowOrderWindow s = new ShowOrderWindow();
                s.setUser(user.getId());
                s.open();
            }
        });
    }

    protected void initTexts() {
        seeOldChoicesBtn.setText("See old choice");
        addEditChoiceBtn.setText("Add or edit your choices");
        showOrderBtn.setText("Show my orders");
    }
}
