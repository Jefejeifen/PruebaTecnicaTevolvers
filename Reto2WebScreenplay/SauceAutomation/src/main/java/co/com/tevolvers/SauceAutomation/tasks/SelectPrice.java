package co.com.tevolvers.SauceAutomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

import static co.com.tevolvers.SauceAutomation.userinterfaces.ProductPage.*;

public class SelectPrice implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        List<String> productos = Text.of(BTN_ADD_TO_CART).viewedBy(actor).asList();
        actor.attemptsTo(

                Click.on(BTN_SELECT_PRODUCT.of("1")),
                Click.on(BTN_SELECT_PRODUCT.of(String.valueOf(productos.size() - 1))),
                Click.on(BTN_SELECT_PRODUCT.of(String.valueOf(productos.size() - 2))),
                Scroll.to(LOGO),
                Click.on(BTN_SHOPPING_CART)

        );

    }

    public static SelectPrice selectPrice() {
        return Tasks.instrumented(SelectPrice.class);
    }
}
