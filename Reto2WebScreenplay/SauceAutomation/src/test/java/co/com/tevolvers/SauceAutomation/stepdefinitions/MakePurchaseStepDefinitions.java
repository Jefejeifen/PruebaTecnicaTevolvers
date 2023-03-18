package co.com.tevolvers.SauceAutomation.stepdefinitions;

import co.com.tevolvers.SauceAutomation.exceptions.ExcepcionErrors;
import co.com.tevolvers.SauceAutomation.models.User;
import co.com.tevolvers.SauceAutomation.questions.*;
import co.com.tevolvers.SauceAutomation.tasks.*;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MakePurchaseStepDefinitions {

    @Managed(driver = "chrome")
    static WebDriver driver;

    @Before
    public void configuration() {
        WebDriverManager.chromedriver().setup();
        OnStage.setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(driver)));
        OnStage.theActorCalled("Dylan");
    }

    @Given("the user enters the home page")
    public void the_user_enters_the_home_page() {
        OnStage.theActorInTheSpotlight().wasAbleTo(Open.url("https://www.saucedemo.com"));
    }

    @When("the user tries to authenticate with his credentials")
    public void the_user_tries_to_authenticate_with_his_credentials(List<String> authentication) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AuthenticateUser.authenticateUser(authentication.get(0), authentication.get(1)));
    }

    @When("the user to organize the prices from lowest to highest")
    public void the_user_to_organize_the_prices_from_lowest_to_highest() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SelectFilter.selectFilter()
        );
    }

    @When("the user chooses the products to the shopping cart")
    public void the_user_chooses_the_products_to_the_shopping_cart() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SelectPrice.selectPrice()
        );
    }

    @When("the user goes to the shopping cart and removes a product")
    public void the_user_goes_to_the_shopping_cart_and_removes_a_product() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SelectCartRemove.selectCartRemove()
        );
    }

    @When("the user enters all the purchase data")
    public void the_user_enters_all_the_purchase_data(List<User> users) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CompleteInformation.completeInfo(users)
        );
    }

    @Then("the user validates the total items")
    public void the_user_validates_the_total_items() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidateTotalITems.validateTotalITems(), Matchers.is(true))
        );
    }

    @Then("the user validates the total price")
    public void the_user_validates_the_total_price() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidateTotalPrice.validateTotalPrice(), Matchers.is(true))
        );
    }

    @Then("user validates purchase success total with a message (.*)")
    public void user_validates_purchase_success_total(String message) {

        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidateMessage.validateMessage(message), Matchers.is(true))
                        .orComplainWith(ExcepcionErrors.class, "Purchase was not completed successfully - false"));

    }

    @Then("^the user displays the error message (.*)$")
    public void theUserDisplaysTheErrorMessage(String failedMessage) {

        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        ValidateFailedMessageLogin.ValidateFailedMessageLogin(failedMessage), Matchers.is(true))
                        .orComplainWith(ExcepcionErrors.class, "Login error - false")
        );

    }

    @And("^the user logs out of the page$")
    public void theUserLogsOutOfThePage() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            LogOutPage.logOutPage()
        );
    }

    @Then("^the user validates the closed session$")
    public void theUserValidatesTheClosedSession() {

        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        ValidateLogOut.validateLogOut(), Matchers.is(true))
                        .orComplainWith(ExcepcionErrors.class, "LogOut error - false")
        );
    }
}
