package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.infrastructure.dao.HttpTicketDao;
import com.elbarcani.archi.user.infrastructure.dao.InMemoryFormDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditFormTest extends AbstractUserTest<EditFormMock, EditForm> {

    @Test
    @DisplayName("edit an existing form")
    void testEditFormShouldSuccess() {
        // Given existing data form user
        assertTrue(mock.createTestFile());

        // When
        objectToTest.execute(mock.getRegularForm());

        // Then
        LoadForm loadForm = new LoadForm(
                mock.getRegularUser(),
                new InMemoryFormDao(EditFormMock.TEST_FILE_PATH),
                new HttpTicketDao(mock.getRegularUser().getId())
        );
        Form loadedForm = loadForm.execute();
        assertSame(mock.getRegularForm().getTicketsList().size(), loadedForm.getTicketsList().size());
        assertEquals(loadedForm.getTicketsList().get(0).getId(), mock.getRegularForm().getTicketsList().get(0).getId());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected EditFormMock createMock() {
        return new EditFormMock();
    }

    @Override
    protected EditForm createObjectToTest() {
        return new EditForm(mock.getRegularUser(), new InMemoryFormDao(EditFormMock.TEST_FILE_PATH));
    }
}
