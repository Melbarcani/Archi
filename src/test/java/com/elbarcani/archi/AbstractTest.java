package com.elbarcani.archi;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest<T extends AbstractMock, S> {

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
