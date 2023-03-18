package co.com.tevolvers.SauceAutomation.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/makePurchase.feature",
        glue = "co.com.tevolvers.SauceAutomation.stepdefinitions",
        snippets = SnippetType.CAMELCASE
)


public class MakePurchaseRunner {
}
