package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By cookieButton = By.id("rcc-confirm-button");
    private By orderUpButton = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");

    private String accordionButtonFormat = "accordion__heading-%d";
    private String accordionTextFormat = "accordion__panel-%d";

    public void clickButton(By buttonLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
    }

    public void clickOrderUpButton() {
        clickButton(orderUpButton);
    }

    public void clickCookieButton() {
        clickButton(cookieButton);
    }

    public void clickAccordionButton(int index) {
        By buttonLocator = By.id(String.format(accordionButtonFormat, index));
        clickButton(buttonLocator);
    }

    public String getAccordionText(int index) {
        By textLocator = By.id(String.format(accordionTextFormat, index));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator)).getText();
    }

    public void scrollToAccordion() {
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}
