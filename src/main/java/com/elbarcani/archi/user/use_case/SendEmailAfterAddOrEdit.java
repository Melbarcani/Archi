package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.Email;

public class SendEmailAfterAddOrEdit {

    private Email email;

    public SendEmailAfterAddOrEdit(Email email) {
        this.email = email;
    }

    public boolean execute(){
        ;
        if(email.send()){
            email.emailNotSentAction();
            return false;
        }
        return true;
    }
}
