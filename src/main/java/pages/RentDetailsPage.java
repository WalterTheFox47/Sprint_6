package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RentDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By blackPearlColor = By.xpath("//input[@id='black' and @type='checkbox']");
    private By courierNoteInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By rentalPeriodDropdown = By.className("Dropdown-arrow");
    private By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    private By confirmButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    private By rentalPeriodOption = By.xpath(".//*[text() = 'сутки']"); // Выбор срока аренды

    private By orderCompletedText = By.xpath(".//*[text() = 'Заказ оформлен']");

    private void clickButton(By buttonLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
    }

    private void enterText(By fieldLocator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator)).sendKeys(text);
    }

    public void setDeliveryDate(String deliveryDate) {
        enterText(deliveryDateInput, deliveryDate);
        clickButton(rentalPeriodDropdown);
    }

    public void setRentalDuration() {
        clickButton(rentalPeriodOption);
    }

    public void setScooterColor() {
        clickButton(blackPearlColor);
    }

    public void setCourierComment(String comment) {
        enterText(courierNoteInput, comment);
    }

    public void fillForm(String deliveryDate, String comment) {
        setDeliveryDate(deliveryDate);
        setRentalDuration();
        setScooterColor();
        setCourierComment(comment);
    }

    public void clickPlaceOrderButton() {
        clickButton(orderButton);
    }

    public void clickYesButton() {
        clickButton(confirmButton);
    }

    public void assertSuccessTextVisible() {
        WebElement checkStatusTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompletedText));
        Assertions.assertTrue(checkStatusTextElement.isDisplayed(), "Текст 'Заказ оформлен' отсутствует");
    }
}
