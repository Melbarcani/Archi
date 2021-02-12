package com.elbarcani.archi.user_service.use_case;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadAllSavedChoicesMock extends AbstractUserServiceMock{
    public static final String TEST_FILE_PATH = "fileNameTest.txt";
    public static final String SEPARATOR = ",";
    public static final int REGULAR_TICKET_ID = 1;
    public static final int REGULAR_TICKET_PRICE = 100;
    public static final String REGULAR_DATE = "10-2-2021";
    public static final String KEEP_STATE = "Keep";
    public static final int REGULAR_USER_ID = 123;

    @Override
    protected void createObjects() {

    }

    public boolean createTestFile(){
        try {
            FileWriter outputStream = new FileWriter(TEST_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
            bufferedWriter.write(REGULAR_TICKET_ID
                    + SEPARATOR + REGULAR_TICKET_PRICE
                    + SEPARATOR + REGULAR_USER_ID
                    + SEPARATOR + KEEP_STATE
                    + SEPARATOR + REGULAR_DATE);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isTestFileExist();
    }

    public boolean isTestFileExist(){
        Path path = Paths.get(TEST_FILE_PATH);
        return Files.exists(path);
    }

    public boolean clearData(){
        File testedFile = new File(TEST_FILE_PATH);
        return testedFile.delete();
    }
}
