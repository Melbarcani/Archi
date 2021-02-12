package com.elbarcani.archi.user_service.use_case;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractUserServiceTest<T extends AbstractUserServiceMock, S> {
    protected T mock;
    protected S objectToTest;

    @BeforeEach
    public void beforeEach() {
        mock = createMock();
        objectToTest = createObjectToTest();
    }

    protected abstract T createMock();

    protected abstract S createObjectToTest();
}
