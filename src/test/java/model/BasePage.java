package model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public SelenideElement loginInput = $(By.xpath("//*[@id=\"login\"]"));
    public SelenideElement passwordInput =  $(By.xpath("//*[@id=\"password\"]"));
    public SelenideElement submitButton = $(By.xpath("//*[@id=\"submitBtn\"]"));
    public SelenideElement lDesktop = $(byText("Рабочий стол"));
    public SelenideElement loading = $(withText("Загрузка"));
    public SelenideElement formError = $(withText("Ошибка"));
    public SelenideElement formAttention = $(withText("Внимание"));

    public void Auth() {
        Selenide.open(ConfigProperties.getProperty("url"));
        loginInput.sendKeys(ConfigProperties.getProperty("login"));
        passwordInput.sendKeys(ConfigProperties.getProperty("password"));
        submitButton.click();
        wait(lDesktop);
    }

    public boolean isAuth() {
        return lDesktop.isDisplayed();
    }

    public void wait(SelenideElement element) {
        element.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void click(SelenideElement element) {
        wait(element);
        element.click();
    }

    public void doubleClick(SelenideElement element) {
        wait(element);
        element.doubleClick();
    }

    public void sendKeys(SelenideElement element) {
        wait(element);
        element.sendKeys();
    }

    public void scrollIntoView(SelenideElement element) {
        wait(element);
        element.scrollIntoView(true);
    }

    public boolean isDisplayed(SelenideElement element) {
        wait(element);
        return element.isDisplayed();
    }

    protected SelenideElement getElementIconByLabelText(String text, String nameIcon) {
        var element = $(byText(text));
        var parent = element.parent().parent().parent();
        var id = parent.getAttribute("id");
        var locator = String.format("#%s .x-form-trigger.x-form-trigger-default.x-form-%s-trigger", id, nameIcon);
        return $(By.cssSelector(locator));
    }
    public SelenideElement getIconSearchByLabelText(String text) {
        return getElementIconByLabelText(text, "search");
    }

    public SelenideElement getIconArrowByLabel(String text) {
        return getElementIconByLabelText(text, "arrow");
    }

    public void waitClose(SelenideElement element) throws InterruptedException {
        while (element.isDisplayed()) {
            Thread.sleep(1000);
        }
    }

    public void waitLoading() throws InterruptedException {
        while (loading.isDisplayed()) {
            Thread.sleep(1000);
        }
    }

    public String getTextError(SelenideElement element, String attribute, String template) {
        var formId = element.getAttribute(attribute);
        formId = formId.replaceAll("\\D+", "");
        var locator = String.format(template, formId);
        return $(By.cssSelector(locator)).getText();
    }
}
