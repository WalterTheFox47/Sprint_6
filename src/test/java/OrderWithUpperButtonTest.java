import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.RentDetailsPage;
import pages.HomePage;
import pages.CredentialsPage;

public class OrderWithUpperButtonTest {

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

    @ParameterizedTest
    @CsvSource({
            "Иван, Иванов, Москва, 89998887766, 01.12.2023, Я НЕНАВИЖУ САМОКАТЫ!!!!!!!",
            "Александр, Петров, Питер, 89994445566, 10.10.2024, Я люблю самокаты!"
    })

    public void testOrderWithUpperButton(String firstName, String lastName, String address, String phoneNumber, String deliveryDate, String comment) {
        homePage.clickOrderUpButton();

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
