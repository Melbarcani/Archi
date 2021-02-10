package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.TicketDao;
import com.elbarcani.archi.user.domain.User;

import java.util.HashMap;

public class LoadForm {
    private final User user;
    private final FormDao formDao;
    private final TicketDao ticketDao;

    public LoadForm(User user, FormDao formDao, TicketDao ticketDao) {
        this.user = user;
        this.formDao = formDao;
        this.ticketDao = ticketDao;
    }

    public Form execute() {
        if(formDao.isFormExist()){
            return formDao.loadForm(user.getId());
        }
        return new Form(new HashMap<>(), ticketDao.getOrderDtoByUser());
    }
}
