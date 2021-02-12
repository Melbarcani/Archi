package com.elbarcani.archi.user_service.use_case;

import com.elbarcani.archi.client_service.domaine.TicketHistory;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesQueryDao;
import com.elbarcani.archi.client_service.use_case.LoadAllSavedChoices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadAllSavedChoicesTest extends AbstractUserServiceTest<LoadAllSavedChoicesMock, LoadAllSavedChoices> {

    @Test
    @DisplayName("loadAllSavedChoices should success")
    void testLoadAllSavedChoicesOk(){
        // Given
        assertTrue(mock.createTestFile());

        // When I load tickets
        List<TicketHistory> tickets = objectToTest.execute();

        // Then
        assertEquals(tickets.size(), 1);
        assertEquals(tickets.get(0).getTicketId(), LoadAllSavedChoicesMock.REGULAR_TICKET_ID);

        // clean file

        assertTrue(mock.clearData());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================
    @Override
    protected LoadAllSavedChoicesMock createMock() {
        return new LoadAllSavedChoicesMock();
    }

    @Override
    protected LoadAllSavedChoices createObjectToTest() {
        return new LoadAllSavedChoices(new InMemoryChoicesQueryDao(LoadAllSavedChoicesMock.TEST_FILE_PATH));
    }
}
