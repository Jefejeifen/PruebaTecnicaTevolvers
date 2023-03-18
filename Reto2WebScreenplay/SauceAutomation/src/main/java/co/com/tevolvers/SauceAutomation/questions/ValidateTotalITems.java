package co.com.tevolvers.SauceAutomation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Overview.*;


public class ValidateTotalITems implements Question {

    @Override
    public Object answeredBy(Actor actor) {


        List<String> products = Text.of(TXT_PRICE_RESULT).viewedBy(actor).asList();
        float countItems = 0;


        actor.attemptsTo(
                Scroll.to(BTN_FINISH)
        );

        for (int i = 1; i <= products.size(); i++) {
            countItems = countItems + Float.parseFloat(
                    TXT_PRICE_RESULT_ITEMS.of(String.valueOf(i)).
                            resolveFor(actor).getText().replace("$", ""));
        }

        countItems = Float.parseFloat(String.format("%.2f", countItems).replace(",", "."));

        float totalItem = Float.parseFloat(TXT_ITEM_TOTAL.resolveFor(actor)
                .getText().replace("Item total: $", ""));

        totalItem = Float.parseFloat(String.format("%.2f", totalItem).replace(",", "."));

        if (countItems == totalItem) {
            return true;
        }
        return false;
    }

    public static ValidateTotalITems validateTotalITems() {
        return new ValidateTotalITems();
    }

}
