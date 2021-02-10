package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.TicketStateHistory;
import com.elbarcani.archi.user.domain.User;

import java.util.List;

public class LoadTicketHistory {
    FormDao formDao;
    User user;

    public LoadTicketHistory(User user, FormDao formDao) {
        this.formDao = formDao;
        this.user = user;
    }

    public List<TicketStateHistory> execute(){
        return formDao.loadTicketsHistory(user.getId());
    }
}
