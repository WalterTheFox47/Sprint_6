package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CredentialsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CredentialsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Далее')]");

    private By metroStation = By.xpath(".//*[text() = 'Тропарёво']");

    public void setField(By fieldLocator, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator)).sendKeys(value);
    }

    public void setMetro() {
        wait.until(ExpectedConditions.elementToBeClickable(metroStationField)).click();
        wait.until(ExpectedConditions.elementToBeClickable(metroStation)).click();
    }

    public void nextButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillForm(String firstName, String lastName, String address, String phoneNumber) {
        setField(firstNameField, firstName);
        setField(lastNameField, lastName);
        setField(addressField, address);
        setMetro();
        setField(phoneNumberField, phoneNumber);
    }
}
