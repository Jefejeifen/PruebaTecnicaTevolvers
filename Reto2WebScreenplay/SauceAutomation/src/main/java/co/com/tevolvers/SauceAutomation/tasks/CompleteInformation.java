package co.com.tevolvers.SauceAutomation.tasks;

import co.com.tevolvers.SauceAutomation.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Information.*;

public class CompleteInformation implements Task {

    List<User> users;

    public CompleteInformation(List<User> users) {
        this.users = users;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(TXT_FIRSTNAME, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(60).seconds(),
                Enter.theValue(users.get(0).getFirstName()).into(TXT_FIRSTNAME),
                Enter.theValue(users.get(0).getLastName()).into(TXT_LASTNAME),
                Enter.theValue(users.get(0).getZip()).into(TXT_ZIP),
                Click.on(BTN_CONTINUE)
        );

    }

    public static CompleteInformation completeInfo(List<User> userList) {
        return Tasks.instrumented(CompleteInformation.class, userList);
    }

}
