package com.elbarcani.archi.display;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class ServiceUser {
    private User[] user;

    @Given("answers recorded from {int} user {int} and {int}")
    public void answersRecordedFromUser(int nbUser, int user1, int user2) {
        this.user = new User[nbUser];
        this.user[0] = new User(user1);
        this.user[1] = new User(user2);
    }

    @When("I list their last answer")
    public void iListTheirLastAnswer() {

    }

    /*@When("I delete all choice")
    public void iDeleteAllChoice() {
        for (User u : user) {
            u.getTicketList().removeAll(u.getTicketList());
        }
    }

    @Then("all choice should be deleted")
    public void allChoiceShouldBeDeleted() {
        for (User u : user) {
            Assert.assertTrue(String.valueOf(u.getTicketList().isEmpty()), true);
        }
    }*/

    @And("I have all this tickets :")
    public void iHaveAllThisTickets(DataTable table) {
        /*List<List<String>> rowList = table.asLists();
        for (int i = 1; i < rowList.size(); i++) {
            User u = user[Integer.parseInt(rowList.get(i).get(1))];
            formController = new FormController(u);

            formController.addTicket(new Ticket(Integer.parseInt(rowList.get(i).get(0)),
                    Integer.parseInt(rowList.get(i).get(1)), u.getId()));
        }*/
    }

}
