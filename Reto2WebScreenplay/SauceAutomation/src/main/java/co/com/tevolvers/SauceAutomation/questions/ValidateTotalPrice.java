package co.com.tevolvers.SauceAutomation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.tevolvers.SauceAutomation.userinterfaces.Overview.*;

public class ValidateTotalPrice implements Question {

    @Override
    public Object answeredBy(Actor actor) {

        float totalItem = Float.parseFloat(TXT_ITEM_TOTAL.resolveFor(actor)
                .getText().replace("Item total: $", ""));

        totalItem = Float.parseFloat(String.format("%.2f", totalItem).replace(",", "."));

        float tax = Float.parseFloat(TXT_TAX.resolveFor(actor)
                .getText().replace("Tax: $", ""));

        tax = Float.parseFloat(String.format("%.2f", tax).replace(",", "."));

        float total = Float.parseFloat(TXT_TOTAL.resolveFor(actor)
                .getText().replace("Total: $", ""));

        total = Float.parseFloat(String.format("%.2f", total).replace(",", "."));

        float totalPrice = totalItem + tax;

        totalPrice = Float.parseFloat(String.format("%.2f", totalPrice).replace(",", "."));

        if (totalPrice == total) {
            return true;
        }

        return false;

    }

    public static ValidateTotalPrice validateTotalPrice() {
        return new ValidateTotalPrice();
    }

}
