package com.elbarcani.archi.client_service.infrastructure.dao;

import com.elbarcani.archi.client_service.domaine.ChoicesCommandDao;

import java.io.File;

public class InMemoryChoicesCommandDao implements ChoicesCommandDao {

    public static final String FORM_FILE_TEXT = "form_file";
    public static final String TEXT_EXTENSION = ".txt";
    private final String fileName;

    public InMemoryChoicesCommandDao() {
        fileName = FORM_FILE_TEXT+TEXT_EXTENSION;
    }
    @Override
    public boolean deleteAllChoices() {
        File testedFile = new File(fileName);
        return testedFile.delete();
    }
}
