package com.elbarcani.archi.user_service.use_case;

public abstract class AbstractUserServiceMock {
    public AbstractUserServiceMock() {
        mockEnvironment();
        createObjects();
    }

    public void mockEnvironment() {

    }

    protected abstract void createObjects();
}
