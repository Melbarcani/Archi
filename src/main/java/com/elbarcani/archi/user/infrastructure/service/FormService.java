package com.elbarcani.archi.user.infrastructure.service;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import com.elbarcani.archi.user.infrastructure.dao.InMemoryFormDao;

import java.util.List;

public class FormService {
    private final String fileName;
    private final FormDao formDao;

    public FormService(String fileName) {
        this.fileName = fileName;
        formDao = new InMemoryFormDao(fileName);
    }

    public void saveForm(Form form, int userId){
        formDao.saveForm(form, userId);
    }

    public Form loadForm(int userId) {
        return formDao.loadForm(userId);
    }

    public boolean isFormFileExist(){
        return formDao.isFormExist();
    }

    public List<TicketStateHistory> loadFormHistory(int userId){
        return formDao.loadTicketsHistory(userId);
    }
}
