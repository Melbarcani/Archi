package com.elbarcani.archi.client_service.use_case;

import com.elbarcani.archi.client_service.domaine.TicketHistory;
import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;

import java.util.List;

public class LoadAllSavedChoices {
    private final ChoicesQueryDao choicesQueryDao;

    public LoadAllSavedChoices(ChoicesQueryDao choicesQueryDao) {
        this.choicesQueryDao = choicesQueryDao;
    }

    public List<TicketHistory> execute(){
        return choicesQueryDao.loadTicketsHistory();
    }
}
