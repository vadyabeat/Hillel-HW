import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.attribute;
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
    @Test(priority=2)
    public void SignUpTest_with_valid_data() {
        open("http://prestashop.qatestlab.com.ua/");
        $(".login").shouldHave(text("Войти")).click();
        $(".page-heading").shouldHave(text("Authentication"));
        $("#email_create").sendKeys("vadyabeat"+ randomInt +"@gmail.com");
        $("#SubmitCreate").click();
        $(By.xpath("//*[@id='noSlide']/h1")).shouldHave(text("Create an account")).click();
        $("#id_gender1").click();
        $("#uniform-id_gender1 > span").shouldHave(attribute("class","checked"));
        $("#customer_firstname").sendKeys("John");
        $("#customer_lastname").sendKeys("Doe");
        $("#email").shouldHave(attribute("value","vadyabeat"+ randomInt +"@gmail.com"));
        $("#passwd").sendKeys("Aa123123123");
        $(By.xpath("//*[@id='days']")).selectOption(1);
        $(By.xpath("//*[@id='uniform-days']/span")).shouldHave(text("1"));
        $(By.xpath("//*[@id='months']")).selectOption(1);
        $(By.xpath("//*[@id='uniform-months']/span")).shouldHave(text("January"));
        $("#cuselFrame-years").click();
        $(By.xpath("//*[@id='cusel-scroll-years']/span[14]")).click();
        $(By.xpath("//*[@id='cusel-scroll-years']/span[14]")).shouldHave(attribute("val","2011"));
        $("#submitAccount").click();
        $(By.xpath("//*[@id='center_column']/p[1]")).shouldHave(text("Your account has been created."));
    }
    @Test(priority=3)
    public void LogoutTest() {
        $(".logout").click();
        $(By.xpath("//*[@id='center_column']/h1")).shouldHave(text("Authentication"));
    }
}
