package co.com.tevolvers.SauceAutomation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Login.BTN_LOGIN;

public class ValidateLogOut implements Question {

    @Override
    public Object answeredBy(Actor actor) {

        return BTN_LOGIN.resolveFor(actor).isEnabled();

    }

    public static ValidateLogOut validateLogOut( ) {
        return new ValidateLogOut();
    }

}
