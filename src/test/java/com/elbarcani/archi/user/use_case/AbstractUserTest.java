package com.elbarcani.archi.user.use_case;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractUserTest<T extends AbstractUserMock, S> {

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
