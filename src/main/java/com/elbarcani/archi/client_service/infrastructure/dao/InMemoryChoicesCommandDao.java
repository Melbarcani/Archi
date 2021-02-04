package com.elbarcani.archi.client_service.infrastructure.dao;

import java.io.File;

public class InMemoryChoicesCommandDao implements ChoicesCommandDao {


    private final String fileName;

    public InMemoryChoicesCommandDao(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public boolean deleteAllChoices() {
        File testedFile = new File(fileName);
        return testedFile.delete();
    }
}
