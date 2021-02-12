package com.elbarcani.archi.client_service.use_case;

import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.domaine.TicketHistory;

import java.util.Collections;
import java.util.List;

public class LoadLastUserChoices {
    private final ChoicesQueryDao choicesQueryDao;

    public LoadLastUserChoices(ChoicesQueryDao choicesQueryDao) {
        this.choicesQueryDao = choicesQueryDao;
    }

    public List<TicketHistory> execute(){
        if(choicesQueryDao.isDataExist())
            return choicesQueryDao.loadTicketsHistory();
        return Collections.emptyList();
    }
}
