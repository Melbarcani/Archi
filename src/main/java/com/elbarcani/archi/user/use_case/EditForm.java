package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Form;
import com.elbarcani.archi.user.domain.FormDao;
import com.elbarcani.archi.user.domain.User;

public class EditForm {
    private final User user;
    private final FormDao formDao;

    public EditForm(User user, FormDao formDao) {
        this.user = user;
        this.formDao = formDao;
    }

    public void execute(Form form) {
        formDao.saveForm(form, user.getId());
    }
}
