package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractUserMock {
    public static final int REGULAR_USER_ID = 123;
    public static final int REGULAR_TICKET_ID = 1;
    public static final int REGULAR_TICKET_PRICE = 100;

    private User user;
    private Ticket ticket;

    public AbstractUserMock() {
        mockEnvironment();
        createObjects();
    }

    protected abstract void createObjects();

    public void mockEnvironment() {
        user = mockUser();
        ticket = mockTicket();
    }

    private User mockUser() {
        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(REGULAR_USER_ID);
        return user;
    }

    private Ticket mockTicket() {
        Ticket t = mock(Ticket.class);
        when(t.getId()).thenReturn(REGULAR_TICKET_ID);
        when(t.getPrice()).thenReturn(REGULAR_TICKET_PRICE);
        when(t.getUserId()).thenReturn(REGULAR_USER_ID);
        return t;
    }

    protected User getRegularUser() {
        return user;
    }

    protected Ticket getRegularTicket() {
        return ticket;
    }
}
