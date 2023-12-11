package uiTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import model.BasePage;
import model.ConfigProperties;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.Console;
import java.time.Duration;
import java.util.NoSuchElementException;


public class BaseTest {
    public BasePage page = new BasePage();

    @BeforeSuite
    public void beforeAll() throws NoSuchElementException {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromedriver"));
        page.Auth();
    }

    @AfterSuite
    public void afterAll() {
        Selenide.closeWebDriver();
        System.exit(0);
    }

}
