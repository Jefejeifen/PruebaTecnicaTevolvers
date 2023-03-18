package co.com.tevolvers.SauceAutomation.tasks;

import co.com.tevolvers.SauceAutomation.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static co.com.tevolvers.SauceAutomation.userinterfaces.ProductPage.BTN_BURGER;
import static co.com.tevolvers.SauceAutomation.userinterfaces.ProductPage.BTN_LOGOUT;

public class LogOutPage implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(BTN_BURGER),
                Click.on(BTN_LOGOUT)
        );

    }

    public static LogOutPage logOutPage() {
        return Tasks.instrumented(LogOutPage.class);
    }


}
