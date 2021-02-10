package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.domain.User;

public interface SendEmailAfterAddOrEdit {
    boolean sendEmail(User user);
}
