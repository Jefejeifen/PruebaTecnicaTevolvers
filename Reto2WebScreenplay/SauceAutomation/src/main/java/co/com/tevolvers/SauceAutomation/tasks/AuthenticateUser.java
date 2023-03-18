package co.com.tevolvers.SauceAutomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Login.*;

public class AuthenticateUser implements Task {

    private final String user;
    private final String password;

    public AuthenticateUser(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(INPUT_USER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(60).seconds(),
                Enter.theValue(user).into(INPUT_USER),
                Enter.theValue(password).into(INPUT_PASSWORD),
                Click.on(BTN_LOGIN));
    }

    public static AuthenticateUser authenticateUser(String user, String password) {
        return Tasks.instrumented(AuthenticateUser.class, user, password);

    }
}
