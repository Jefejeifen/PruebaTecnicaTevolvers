package co.com.tevolvers.SauceAutomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.tevolvers.SauceAutomation.userinterfaces.ShoppingCart.*;

public class SelectCartRemove implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(BTN_REMOVE_PRODUCTS, WebElementStateMatchers.isVisible()).
                        forNoMoreThan(10).seconds()
        );

        List<String> productos = Text.of(BTN_REMOVE_PRODUCTS).viewedBy(actor).asList();

        int randomProduct = (int) (Math.random() * ((productos.size()) - 1)) + 1;


        actor.attemptsTo(

                WaitUntil.the(BTN_REMOVE.of(String.valueOf(randomProduct)),
                        WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),
                Click.on(BTN_REMOVE.of(String.valueOf(randomProduct)))

        );

        actor.attemptsTo(
                Click.on(BTN_CHECKOUT)
        );

    }


    public static SelectCartRemove selectCartRemove() {
        return Tasks.instrumented(SelectCartRemove.class);
    }
}
