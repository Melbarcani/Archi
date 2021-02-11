package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.dao.HttpUserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoadUserTest extends AbstractUserTest<LoadUserMock, LoadUser>{

    @Test
    @DisplayName("test loadUser should success")
    void testLoadRegularUser() {
        // Given a user Id
        // When
        Optional<User> loadedUser = objectToTest.execute();

        // Then
        assertTrue(loadedUser.isPresent());
        assertSame(loadedUser.get().getId(), mock.getRegularUser().getId());
    }

    @Test
    @DisplayName("test load user with wring id")
    void testLoadFakeUser() {
        // Given a fake number Id
        LoadUser loadFakeUser = new LoadUser(new HttpUserDao(LoadUserMock.FAKE_USER_ID));

        // When
        Optional<User> loadedUser = loadFakeUser.execute();

        // Then
        assertTrue(loadedUser.isEmpty());
    }

    //====================================================================================================
    // Create method
    //====================================================================================================

    @Override
    protected LoadUserMock createMock() {
        return new LoadUserMock();
    }

    @Override
    protected LoadUser createObjectToTest() {
        return new LoadUser(new HttpUserDao(mock.getRegularUser().getId()));
    }
}
