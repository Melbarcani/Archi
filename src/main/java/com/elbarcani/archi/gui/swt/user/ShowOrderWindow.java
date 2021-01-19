package com.elbarcani.archi.gui.swt.user;

import com.elbarcani.archi.gui.swt.common.DisplayWindow;
import com.elbarcani.archi.user.domain.Order;
import com.elbarcani.archi.user.domain.Ticket;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;

public class ShowOrderWindow extends DisplayWindow {
    private Ticket ticket;
    private int userId;

    private void updateData() {
    }

    @Override
    protected void createMainComposite(Composite parent) {
        super.createMainComposite(parent);
    }

    protected void setUser(int userId){
        this.userId = userId;

        List<Ticket> tickets = userUIService.getOrderByUser(userId);
        for(Ticket ticket : tickets){
            Composite ticketComposite = new Composite(mainComposite, SWT.NONE);
            GridLayout layout = new GridLayout(3, false);
            ticketComposite.setLayout(layout);
            Label ticketIdLbl = new Label(ticketComposite, SWT.NONE);
            ticketIdLbl.setText(String.valueOf(ticket.getTicketId()));
            Label ticketPriceLbl = new Label(ticketComposite, SWT.NONE);
            ticketPriceLbl.setText(String.valueOf(ticket.getPrice()));
            Label userIdLbl = new Label(ticketComposite, SWT.NONE);
            userIdLbl.setText(String.valueOf(ticket.getUserId()));

        }
        mainComposite.layout();
    }
}
