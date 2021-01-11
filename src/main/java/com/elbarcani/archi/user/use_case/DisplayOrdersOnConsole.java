package com.elbarcani.archi.user.use_case;

import com.elbarcani.archi.user.infrastructure.controller.FormController;

public class DisplayOrdersOnConsole implements DisplayOrders{
    @Override
    public void display(FormController formController) {
        StringBuilder result = new StringBuilder();
        formController.getTicketsState().forEach((key, value) -> {
            if(formController.getTicketById(key).isPresent()){
                result.append(key)
                        .append("  |  ").append(formController.getTicketById(key).get().getPrice())
                        .append("  |  ").append(formController.getTicketById(key).get().getUserId())
                        .append("  |  ").append(value)
                        .append("\n");
            }
        });
        System.out.println(result);
    }
}
