package com.elbarcani.archi.client_service.use_case;

import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;

public class CheckDataExistence {

    private final ChoicesQueryDao choicesQueryDao;

    public CheckDataExistence(ChoicesQueryDao choicesQueryDao) {
        this.choicesQueryDao = choicesQueryDao;
    }

    public boolean execute() {
        return choicesQueryDao.isDataExist();
    }
}
