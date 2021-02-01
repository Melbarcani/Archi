package com.elbarcani.archi.display;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class DisplayOrdersCase {
    private User user;
    /*private FormController formController;

    @Given("The user {int}")
    public void setUser(Integer userId) {
        user = new User(userId);
    }

    @And("I have those tickets :")
    public void injectTicketData(DataTable table) {
        formController = new FormController(user);
        List<List<String>> rowList = table.asLists();
        for (int i = 1; i < rowList.size(); i++) {
            formController.addTicket(new Ticket(Integer.parseInt(rowList.get(i).get(0)), Integer.parseInt(rowList.get(i).get(1)), user.getId()));
        }
    }

    @And("I already filled the form once")
    public void iAlreadyFilledTheFormOnce(DataTable table) {
        List<List<String>> rowList = table.asLists();
        for (int i = 1; i < rowList.size(); i++) {
            formController.setTicketAnswer(Integer.parseInt(rowList.get(i).get(0)),rowList.get(i).get(3));
        }
    }

    @And("I never filled the form")
    public void formNeverFilled() {
        formController.restoreNeverFilled();
    }

    *//*@When("I display the form")
    public void displayTheForm() {
        DisplayOrders display = new DisplayOrdersOnConsole();
        UserController controller = new UserController(display);
        *//**//*final UserDto userDto = new UserDto(1, "ok");
        controller.display(userDto);*//**//*
    }*//*

    @Then("I should see :")
    public void iShouldSee(DataTable table) {
        List<List<String>> rowList = table.asLists();
        for (int i = 1; i < rowList.size(); i++) {
            Ticket ticket = formController.getTicketById(i).get();
            List<String> currentRow = rowList.get(i);

            Assert.assertEquals(currentRow.get(0), String.valueOf(ticket.getTicketId()));
            Assert.assertEquals(currentRow.get(1), String.valueOf(ticket.getPrice()));
            Assert.assertEquals(currentRow.get(2), String.valueOf(user.getId()));
            Assert.assertEquals(currentRow.get(3), formController.getTicketsState().get(ticket.getTicketId()));
        }
    }
*/
}
