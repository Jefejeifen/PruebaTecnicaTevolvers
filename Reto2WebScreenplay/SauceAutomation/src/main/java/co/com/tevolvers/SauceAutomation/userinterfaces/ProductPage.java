package co.com.tevolvers.SauceAutomation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductPage {

    public static final Target BTN_FILTER =
            Target.the("Filter products ")
                    .located(By.xpath("//select[@class='product_sort_container']"));

    public static final Target LOW_TO_HIGH =
            Target.the("Filter low high")
                    .located(By.xpath("//option[@value='lohi']"));

    public static final Target BTN_ADD_TO_CART =
            Target.the("Add product to cart")
                    .located(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));

    public static final Target BTN_SELECT_PRODUCT =
            Target.the("Add product to cart")
                    .locatedBy("(//button[@class='btn btn_primary btn_small btn_inventory'])[{0}]");

    public static final Target TXT_PRICE_PRODUCT =
            Target.the("Text price product")
                    .located(By.xpath("//div[@class='inventory_item_price']"));

    public static final Target BTN_SHOPPING_CART =
            Target.the("Button Shopping Cart")
                    .located(By.xpath("//a[@class='shopping_cart_link']"));

    public static final Target LOGO =
            Target.the("Logo")
                    .located(By.xpath("//div[@class='app_logo']"));

    public static final Target BTN_BURGER =
            Target.the("Btn burger")
                    .located(By.xpath("//button[@id='react-burger-menu-btn']"));

    public static final Target BTN_LOGOUT =
            Target.the("Btn Logout")
                    .located(By.xpath("//a[@id='logout_sidebar_link']"));



}
