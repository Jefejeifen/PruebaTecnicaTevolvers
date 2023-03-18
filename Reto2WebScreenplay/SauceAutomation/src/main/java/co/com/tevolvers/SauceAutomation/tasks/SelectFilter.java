package co.com.tevolvers.SauceAutomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.tevolvers.SauceAutomation.userinterfaces.ProductPage.BTN_FILTER;

public class SelectFilter implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(BTN_FILTER, WebElementStateMatchers.isVisible()).
                        forNoMoreThan(5).seconds(),
                SelectFromOptions.byIndex(2).from(BTN_FILTER)
        );

    }

    public static SelectFilter selectFilter() {
        return Tasks.instrumented(SelectFilter.class);
    }

}
