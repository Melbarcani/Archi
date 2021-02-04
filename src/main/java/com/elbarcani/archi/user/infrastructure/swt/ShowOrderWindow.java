package com.elbarcani.archi.user.infrastructure.swt;

import com.elbarcani.archi.infrastructure.swt.DisplayWindow;
import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.UserController;
import com.elbarcani.archi.user.use_case.DisplayOrders;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

public class ShowOrderWindow implements DisplayOrders {
    private final DisplayWindow window;
    private Composite mainComposite;
    private User user;

    public ShowOrderWindow(User user) {
        window= new DisplayWindow();
        this.user = user;
        createComposites();
        addListeners();
    }

    private void addListeners() {
        window.getResetBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                window.dispose();
                UserWindow userWindow = new UserWindow();
                userWindow.open();
            }
        });
    }

    private void createComposites() {
        mainComposite = window.getMainComposite();
    }

    @Override
    public void display(UserController userController) {
        window.setTitle(String.valueOf(user.getId()));
        List<Ticket> tickets = userController.getOrderByUser();
        for(Ticket ticket : tickets){
            Composite ticketComposite = new Composite(mainComposite, SWT.NONE);
            GridLayout layout = new GridLayout(3, false);
            ticketComposite.setLayout(layout);
            Label ticketIdLbl = new Label(ticketComposite, SWT.NONE);
            ticketIdLbl.setText(String.valueOf(ticket.getId()));
            Label ticketPriceLbl = new Label(ticketComposite, SWT.NONE);
            ticketPriceLbl.setText(String.valueOf(ticket.getPrice()));
            Label userIdLbl = new Label(ticketComposite, SWT.NONE);
            userIdLbl.setText(String.valueOf(ticket.getUserId()));
        }
        mainComposite.layout();
        window.getResetBtn().setVisible(true);
        window.open();
    }
}
