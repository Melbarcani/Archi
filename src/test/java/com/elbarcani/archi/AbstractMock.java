package com.elbarcani.archi;

public abstract class AbstractMock {


    public AbstractMock() {
        createObjects();
        mockEnvironment();
    }

    protected abstract void createObjects();

    public void mockEnvironment(){

    }
}
