package com.elbarcani.archi.display;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.controller.FormController;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class FormAnswers {
    private User user;
    private FormController formController;

    @Given("User {int}")
    public void setUser(int userId) {
        user = new User(userId);
    }

    @And("I have tickets :")
    public void injectTicketData(DataTable table) {
        formController = new FormController(user);
        List<List<String>> rowList = table.asLists();
        for (int i = 1; i < rowList.size(); i++) {
            formController.addTicket(new Ticket(Integer.parseInt(rowList.get(i).get(0)), Integer.parseInt(rowList.get(i).get(1)), user.getId()));
        }
    }
    @When("I answer to the form as :")
    public void setFormAnswers(DataTable table) {
        List<List<String>> rowList = table.asLists();
        formController = new FormController(user);
        for (int i = 1; i < rowList.size(); i++) {
            formController.setTicketAnswer(Integer.parseInt(rowList.get(i).get(0)), rowList.get(i).get(1));
        }
    }

    @Then("I see this message : {string}")
    public void iSeeThisMessage(String recordedMessage) {
        Assert.assertEquals(formController.getMessageStatus(),recordedMessage);
    }

    @And("an email is sent to the user {int}")
    public void anEmailIsSentToTheUser(int userID) {
        // TODO
        Assert.assertEquals(user.getId(),userID);
    }
}
