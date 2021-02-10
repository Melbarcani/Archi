package com.elbarcani.archi.client_service.use_case;

import com.elbarcani.archi.client_service.domaine.ChoicesCommandDao;
import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;

public class DeleteAllChoices {


    private final ChoicesQueryDao choicesQueryDao;
    private final ChoicesCommandDao choicesCommandDao;

    public DeleteAllChoices(ChoicesQueryDao choicesQueryDao, ChoicesCommandDao choicesCommandDao) {

        this.choicesQueryDao = choicesQueryDao;
        this.choicesCommandDao = choicesCommandDao;
    }

    public boolean execute(){
        return !choicesQueryDao.isDataExist() || choicesCommandDao.deleteAllChoices();
    }
}
