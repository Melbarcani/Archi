package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.dao.HttpTicketDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadUserTicketsTest extends AbstractUserTest<LoadUserTicketsMock, LoadUserTickets> {

    @Test
    @DisplayName("Load User Tickets Should be Ok")
    void testLoadUserTickets(){
        // Given an existent user
        User user = mock.getRegularUser();

        // When
        List<Ticket> tickets = objectToTest.execute();

        // Then
        assertFalse(tickets.isEmpty());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected LoadUserTicketsMock createMock() {
        return new LoadUserTicketsMock();
    }

    @Override
    protected LoadUserTickets createObjectToTest() {
        return new LoadUserTickets(new HttpTicketDao(mock.getRegularUser().getId()));
    }
}
