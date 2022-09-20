import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    String MOBILE_SERVICE_URL = "https://next.privat24.ua/mobile";

    @Test
    void mobileService() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        By phoneForm = By.xpath("//input[@data-qa-node=\"phone-number\"]");
        By amountField = By.xpath("//input[@data-qa-node=\"amount\"]");
        By cardNumberField = By.xpath("//input[@data-qa-node=\"numberdebitSource\"]");
        By expDateField = By.xpath("//input[@data-qa-node=\"expiredebitSource\"]");
        By cvvField = By.xpath("//input[@data-qa-node=\"cvvdebitSource\"]");
        By termsAndConditionsLink = By.xpath("//a[@href=\"https://privatbank.ua/terms\"]");
        By firstNameField = By.xpath("//input[@data-qa-node=\"firstNamedebitSource\"]");
        By lastNameField = By.xpath("//input[@data-qa-node=\"lastNamedebitSource\"]");
        By submitButton = By.xpath("//button[@data-qa-node=\"submit\"]");
        By expectedPhoneNumber = By.xpath("//div[@data-qa-node=\"details\"]");
        By expectedAmount = By.xpath("//div[@data-qa-node=\"amount\"]");


        driver.get(MOBILE_SERVICE_URL);
        driver.findElement(phoneForm).sendKeys("664336081");
        driver.findElement(amountField).sendKeys("\b\b\b");
        driver.findElement(amountField).sendKeys("322");
        driver.findElement(cardNumberField).sendKeys("4006895689048337");
        driver.findElement(expDateField).sendKeys("0323");
        driver.findElement(cvvField).sendKeys("480");
        driver.findElement(termsAndConditionsLink).click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        Assertions.assertEquals("Умови та правила",driver.getTitle());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(firstNameField).sendKeys("Artem");
        driver.findElement(lastNameField).sendKeys("Checheniev");
        driver.findElement(submitButton).click();
        Assertions.assertEquals("Поповнення телефону. На номер +380664336081",driver.findElement(expectedPhoneNumber).getText());
        Assertions.assertEquals("322 UAH",driver.findElement(expectedAmount).getText());
    }
}
