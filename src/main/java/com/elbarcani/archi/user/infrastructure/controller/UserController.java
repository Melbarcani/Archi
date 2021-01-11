package com.elbarcani.archi.user.infrastructure.controller;

import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.use_case.DisplayOrders;

public class UserController {

    private final DisplayOrders displayOrders;

    public UserController(DisplayOrders displayOrders) {
        this.displayOrders = displayOrders;
    }
}
