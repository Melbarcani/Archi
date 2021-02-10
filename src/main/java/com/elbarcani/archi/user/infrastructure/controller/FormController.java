package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import com.elbarcani.archi.user.infrastructure.dao.InMemoryFormDao;

import java.util.List;

public class FormController {
    private final FormDao formDao;

    public FormController(String fileName) {
        formDao = new InMemoryFormDao(fileName);
    }

    public void saveForm(Form form, int userId){
        formDao.saveForm(form, userId);
    }

    public Form loadForm(int userId) {
        return formDao.loadForm(userId);
    }

    public boolean isFormDataExist(){
        return formDao.isFormExist();
    }

    public List<TicketStateHistory> loadFormHistory(int userId){
        return formDao.loadTicketsHistory(userId);
    }
}
