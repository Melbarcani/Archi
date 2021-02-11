package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.use_case.AbstractUserTest;
import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FormControllerTest extends AbstractUserTest<FormControllerMock, FormController> {

    @Test
    @DisplayName("saveForm(int userId) and loadForm()methods")
    void testSaveFormAndLoadFormMethods(){
        objectToTest.saveForm(mock.getRegularForm(), FormControllerMock.REGULAR_USER_ID);
        assertTrue(objectToTest.isFormDataExist());

        Form form = objectToTest.loadForm(FormControllerMock.REGULAR_USER_ID);
        Assertions.assertThat(form.getTicketsList().size()).isEqualTo(mock.getRegularForm().getTicketsList().size());
        Assertions.assertThat(form.getTicketsList().get(0).getId()).isEqualTo(mock.getRegularForm().getTicketsList().get(0).getId());

        assertTrue(mock.clearData());
    }

    @Test
    @DisplayName("saveForm(int userId) in existing file")
    void testSaveFormWithExistingFile(){
        // Given existing data form user
        objectToTest.saveForm(mock.getRegularForm(), FormControllerMock.REGULAR_USER_ID);
        assertTrue(objectToTest.isFormDataExist());

        // When I save a nth form for the same user
        objectToTest.saveForm(mock.getSecondForm(), FormControllerMock.REGULAR_USER_ID);

        // Then the last choice should be the nth form
        Form form = objectToTest.loadForm(FormControllerMock.REGULAR_USER_ID);
        Assertions.assertThat(form.getTicketsState().get(FormControllerMock.REGULAR_TICKET_ID)).isEqualTo(FormControllerMock.REIMBURSE_STATE);

        // And the history of the file should contains n choices by ticket
        List<TicketStateHistory> historyList = objectToTest.loadFormHistory(FormControllerMock.REGULAR_USER_ID);
        Assertions.assertThat(historyList.get(0).getStateDateList().size()).isEqualTo(2);
        Assertions.assertThat(historyList.get(0).getStateDateList().get(0).getState()).isEqualTo(FormControllerMock.KEEP_STATE);
        Assertions.assertThat(historyList.get(0).getStateDateList().get(1).getState()).isEqualTo(FormControllerMock.REIMBURSE_STATE);

        // Delete data to insure next tests
        assertTrue(mock.clearData());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected FormControllerMock createMock() {
        return new FormControllerMock();
    }

    @Override
    protected FormController createObjectToTest() {
        return new FormController(FormControllerMock.EXISTENT_FILE_NAME);
    }
}
