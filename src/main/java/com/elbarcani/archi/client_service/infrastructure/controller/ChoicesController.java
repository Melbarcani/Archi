package com.elbarcani.archi.client_service.infrastructure.controller;

import com.elbarcani.archi.client_service.domaine.TicketHistory;
import com.elbarcani.archi.client_service.domaine.ChoicesCommandDao;
import com.elbarcani.archi.client_service.domaine.ChoicesQueryDao;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesCommandDao;
import com.elbarcani.archi.client_service.infrastructure.dao.InMemoryChoicesQueryDao;

import java.util.List;

public class ChoicesController {
    private final ChoicesQueryDao choicesQueryDao;
    private final ChoicesCommandDao choicesCommandDao;

    public ChoicesController(String fileName) {
        choicesQueryDao = new InMemoryChoicesQueryDao();
        choicesCommandDao = new InMemoryChoicesCommandDao();
    }

    public boolean isDataExist(){
        return choicesQueryDao.isDataExist();
    }

    public List<TicketHistory> loadChoicesHistory(){
        return choicesQueryDao.loadTicketsHistory();
    }

    public boolean deleteChoices(){
        return !choicesQueryDao.isDataExist() || choicesCommandDao.deleteAllChoices();
    }
}
