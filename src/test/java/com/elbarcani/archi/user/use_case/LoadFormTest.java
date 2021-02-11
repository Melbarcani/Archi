package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.infrastructure.dao.HttpTicketDao;
import com.elbarcani.archi.user.infrastructure.dao.InMemoryFormDao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadFormTest extends AbstractUserTest<LoadFormMock, LoadForm> {

    @Test
    @DisplayName("LoadForm from an existent file should be ok")
    void testLoadFormFileOk(){
        // Given I have a non empty form
        assertTrue(mock.createTestFile());

        // When
        Form form = objectToTest.execute();

        // Then
        Assertions.assertThat(form.getTicketsList().size()).isEqualTo(mock.getRegularForm().getTicketsList().size());
        Assertions.assertThat(form.getTicketsList().get(0).getId()).isEqualTo(mock.getRegularForm().getTicketsList().get(0).getId());

        assertTrue(mock.clearData());
    }

    @Test
    @DisplayName("LoadForm from an non existent file should be ok")
    void testLoadFormHttpOk(){
        //Given I have an empty form
        assertFalse(mock.isTestFileExist());

        //when
        Form form = objectToTest.execute();

        // Then
        Assertions.assertThat(form.getTicketsList().size()).isEqualTo(6);
        Assertions.assertThat(form.getTicketsList().get(0).getId()).isEqualTo(mock.getRegularForm().getTicketsList().get(0).getId());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected LoadFormMock createMock() {
        return new LoadFormMock();
    }

    @Override
    protected LoadForm createObjectToTest() {
        return new LoadForm(mock.getRegularUser(),  new InMemoryFormDao(LoadFormMock.TEST_FILE_PATH), new HttpTicketDao(mock.getRegularUser().getId()));
    }
}
