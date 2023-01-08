import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
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
    @Test(priority=4)
    public void LoginTest_with_valid_data() {
        $("#email").sendKeys("vadyabeat"+ randomInt +"@gmail.com");
        $("#passwd").sendKeys("Aa123123123");
        $("#SubmitLogin").click();
        $(".info-account").shouldHave(text("Welcome to your account"));
    }
    @Test(priority=5)
    public void AddItemToCart() {
        $(".logo").click();
        $(By.xpath("//*[@id='homefeatured']/li[1]/div/div[2]")).hover();
        $(By.xpath("//*[@id='homefeatured']/li[1]/div/div[2]/div[2]/a[1]/span")).click();
        $(By.xpath("//*[@id='layer_cart']/div[1]/div[1]/h2")).shouldHave(text("Товар был успешно добавлен в вашу корзину"));
        $(By.xpath("//*[@id='layer_cart_product_title']")).shouldHave(text("Faded Short Sleeve T-shirts"));
        $(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")).click();
        $(By.xpath("//*[@id='header']/div[3]/div/div/div[3]/div/a/b")).click();
        $(By.xpath("//*[@id='product_1_1_0_0']/td[2]/p/a")).shouldHave(text("Faded Short Sleeve T-shirts"));
        $(By.xpath("//*[@id='product_1_1_0_0']/td[5]/input[2]")).shouldHave(value("1"));
    }
    @Test(priority=6)
    public void ProceedToCheckout() {
        $(By.xpath("//*[@id='center_column']/p[2]/a/span")).shouldHave(text("Proceed to checkout")).click();
        $("#firstname").sendKeys("John");
        $("#lastname").sendKeys("Doe");
        $("#address1").sendKeys("Baker street 221B");
        $("#postcode").sendKeys("01000");
        $("#city").sendKeys("London");
        $("#id_country").selectOption(0);
        $("#phone").sendKeys("1234567890");
        $("#id_state").selectOption(1);
        $(By.xpath("//*[@id='uniform-id_state']/span")).shouldHave(text("Alabama"));
        $("#alias").sendKeys("home");
        $("#submitAddress").click();
        $(By.xpath("//*[@id='address_invoice']/li[2]")).shouldHave(text("John Doe"));
        $(By.xpath("//*[@id='address_invoice']/li[3]")).shouldHave(text("Baker street 221B"));
        $(By.xpath("//*[@id='address_invoice']/li[4]")).shouldHave(text("London, Alabama 01000"));
        $(By.xpath("//*[@id='address_invoice']/li[6]")).shouldHave(text("1234567890"));
        $(By.xpath("//*[@id='center_column']/form/p/button/span")).shouldHave(text("Proceed to checkout")).click();
        $("#cgv").click();
        $("#uniform-cgv > span").shouldHave(attribute("class","checked"));
        $(By.xpath("//*[@id='form']/p/button")).click();
        $(By.xpath("//*[@id='center_column']/div/p")).shouldHave(text("No payment modules have been installed."));
    }
}
