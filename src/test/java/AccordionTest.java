import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccordionTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);
        driver = new FirefoxDriver(options);
        homePage = new HomePage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        homePage.clickCookieButton();
        homePage.scrollToAccordion();
    }

    @ParameterizedTest
    @CsvSource({
            "'Сутки — 400 рублей. Оплата курьеру — наличными или картой.', 0",
            "'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.', 1",
            "'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.', 2",
            "'Только начиная с завтрашнего дня. Но скоро станем расторопнее.', 3",
            "'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.', 4",
            "'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.', 5",
            "'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.', 6",
            "'Да, обязательно. Всем самокатов! И Москве, и Московской области.', 7"
    })
    public void testAccordionElement(String expectedText, int index) {
        homePage.clickAccordionButton(index);
        String actualText = homePage.getAccordionText(index);
        assertEquals(expectedText, actualText);
    }

    @AfterEach
    public void stopTest() {
        driver.quit();
    }
}
