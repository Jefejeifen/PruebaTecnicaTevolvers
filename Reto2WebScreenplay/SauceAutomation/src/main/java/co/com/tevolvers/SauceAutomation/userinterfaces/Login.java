package co.com.tevolvers.SauceAutomation.userinterfaces;


import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Login {

    public static final Target INPUT_USER =
            Target.the("Input User")
                    .located(By.xpath("//input[@id='user-name']"));

    public static final Target INPUT_PASSWORD =
            Target.the("Input Password")
                    .located(By.xpath("//input[@id='password']"));

    public static final Target BTN_LOGIN =
            Target.the("Btn Login")
                    .located(By.xpath("//input[@id='login-button']"));

    public static final Target TXT_FAILED_LOGIN =
            Target.the("Btn Login")
                    .located(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));


}
