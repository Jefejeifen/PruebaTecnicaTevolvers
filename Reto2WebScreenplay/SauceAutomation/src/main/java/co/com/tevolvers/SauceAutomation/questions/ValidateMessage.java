package co.com.tevolvers.SauceAutomation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Complete.TXT_FINISH;
import static co.com.tevolvers.SauceAutomation.userinterfaces.Overview.BTN_FINISH;

public class ValidateMessage implements Question {

    private String message;

    public ValidateMessage(String message) {
        this.message = message;
    }

    @Override
    public Object answeredBy(Actor actor) {

        actor.attemptsTo(
                Click.on(BTN_FINISH)
        );

        String messageFinal = TXT_FINISH.resolveFor(actor).getText();

        if (messageFinal.equals(message)) {
            return true;
        }

        return false;
    }

    public static ValidateMessage validateMessage(String message) {
        return new ValidateMessage(message);
    }

}
