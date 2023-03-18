package co.com.tevolvers.SauceAutomation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Login.TXT_FAILED_LOGIN;

public class ValidateFailedMessageLogin implements Question {

    private String messageFailed;

    public ValidateFailedMessageLogin(String messageFailed) {
        this.messageFailed = messageFailed;
    }

    @Override
    public Object answeredBy(Actor actor) {
        String messageFinal = TXT_FAILED_LOGIN.resolveFor(actor).getText();

        if (messageFinal.equals(messageFailed)) {
            return true;
        }

        return false;
    }

    public static ValidateFailedMessageLogin ValidateFailedMessageLogin(String messageFailed) {
        return new ValidateFailedMessageLogin(messageFailed);
    }

}
