package co.com.tevolvers.SauceAutomation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Complete {

    public static final Target TXT_FINISH =
            Target.the("Text finish shopping")
                    .located(By.xpath("//h2[@class='complete-header']"));

}
