package com.elbarcani.archi.gui.service;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;

import java.util.List;

public interface UserUIService {
    User getUserById(int userId);
    List<Ticket> getOrderByUser(int userId);
}
