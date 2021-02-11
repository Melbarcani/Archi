package com.elbarcani.archi.user_service.infrastructure.controller;

import com.elbarcani.archi.client_service.domaine.TicketHistory;
import com.elbarcani.archi.client_service.infrastructure.controller.ChoicesController;
import com.elbarcani.archi.user.use_case.AbstractUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChoicesControllerTest extends AbstractUserTest<ChoicesControllerMock, ChoicesController> {

    @Test
    @DisplayName("loadChoicesHistory and isDataExist methods")
    public void testLoadChoicesHistory(){
        List<TicketHistory> historyTicketsList = objectToTest.loadChoicesHistory();
        assertTrue(historyTicketsList.isEmpty());

        // Given I have data with tickets history
        mock.createFile(mock.REGULAR_URL);

        // When I load history
        historyTicketsList = objectToTest.loadChoicesHistory();

        // Then I should have all saved tickets
        assertFalse(historyTicketsList.isEmpty());
        assertEquals(mock.getTicketId(), historyTicketsList.get(0).getTicketId());
        assertTrue(objectToTest.isDataExist());

        // Delete the created file to insure next TUs
        assertTrue(objectToTest.deleteChoices());
    }

    @Test
    @DisplayName("deleteChoices")
    public void testDeleteChoices(){
        assertFalse(objectToTest.isDataExist());

        // Given I have data with tickets history
        mock.createFile(mock.REGULAR_URL);
        List<TicketHistory> historyTicketsList = objectToTest.loadChoicesHistory();
        assertFalse(historyTicketsList.isEmpty());

        // When I delete all choices
        assertTrue(objectToTest.deleteChoices());

        // Then I should not have saved data
        assertFalse(objectToTest.isDataExist());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected ChoicesControllerMock createMock() {
        return new ChoicesControllerMock();
    }

    @Override
    protected ChoicesController createObjectToTest() {
        return new ChoicesController(mock.REGULAR_URL);
    }
}
