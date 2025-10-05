import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import pages.HomePage;
import pages.CredentialsPage;
import pages.RentDetailsPage;

public class OrderWithLowerButtonTest {
    private WebDriver driver;
    private HomePage homePage;
    private CredentialsPage credentialsPage;
    private RentDetailsPage rentDetailsPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);
        driver = new FirefoxDriver(options);
        homePage = new HomePage(driver);
        credentialsPage = new CredentialsPage(driver);
        rentDetailsPage = new RentDetailsPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.clickCookieButton();
        driver.manage().window().maximize();
    }

    private void scrollToElementAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    @ParameterizedTest
    @CsvSource({
            "Александр, Петров, Питер, 89994445566, 10.10.2024, Я люблю самокаты!"
    })

    public void testOrderWithLowerButton(String firstName, String lastName, String address, String phoneNumber, String deliveryDate, String comment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderButton = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp' or @class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']"));
        scrollToElementAndClick(orderButton);

        credentialsPage.fillForm(firstName, lastName, address, phoneNumber);
        credentialsPage.nextButtonClick();

        rentDetailsPage.fillForm(deliveryDate, comment);
        rentDetailsPage.clickPlaceOrderButton();
        rentDetailsPage.clickYesButton();
        rentDetailsPage.assertSuccessTextVisible();
    }

    @AfterEach
    public void stopTest() {
        driver.quit();
    }
}
