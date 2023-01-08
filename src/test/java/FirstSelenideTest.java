import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FirstSelenideTest {
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    @Test(priority=1)
    public void LoginTest_with_invalid_data() {
        open("http://prestashop.qatestlab.com.ua/");
        $(".login").shouldHave(text("Войти")).click();
        $(".page-heading").shouldHave(text("Authentication"));
        $("#email").sendKeys("vadyabeatttt@gmail.com");
        $("#passwd").sendKeys("Aa123123123123");
        $("#SubmitLogin").click();
        $(".alert-danger").shouldHave(text("Authentication failed."));
    }

}
