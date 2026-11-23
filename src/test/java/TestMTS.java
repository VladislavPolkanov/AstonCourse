import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMTS {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.mts.by/";

    private static final By PAY_WRAPPER = By.cssSelector("div.pay__wrapper");
    private static final By PAY_PARTNERS = By.cssSelector("div.pay__partners");
    private static final By PAY_FORM = By.cssSelector("div.pay__form");

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 3);
        driver.get(BASE_URL);

        closeCookieBanner();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void closeCookieBanner() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, 3);
            WebElement acceptBtn = shortWait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))
            );
            acceptBtn.click();

            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cookie")));
        } catch (TimeoutException e) {
            System.out.println("Баннер cookie не появился или уже закрыт.");
        }
    }

    // T1: Проверка названия блока
    @Test
    public void testBlockTitle() {
        WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(PAY_WRAPPER));
        WebElement title = wrapper.findElement(By.tagName("h2"));
        String text = title.getAttribute("textContent");

        text = text.replaceAll("\\s+", " ").trim();
        System.out.println("Полученный текст заголовка: [" + text + "]"); // Для отладки

        assertTrue(text.contains("Онлайн пополнение"),
                "Заголовок не содержит 'Онлайн пополнение'. Фактический текст: [" + text + "]");
        assertTrue(text.contains("без комиссии"),
                "Заголовок не содержит 'без комиссии'. Фактический текст: [" + text + "]");
    }

    // T2: Проверка наличия логотипов платёжных систем
    @Test
    public void testPaymentLogos() {

        WebElement partnersContainer = wait.until(ExpectedConditions.presenceOfElementLocated(PAY_PARTNERS));

        List<WebElement> logos = partnersContainer.findElements(By.tagName("img"));

        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены в блоке pay__partners");


    }

    // T3: Проверка работы ссылки «Подробнее о сервисе»
    @Test
    public void testMoreDetailsLink() {
        WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(PAY_WRAPPER));

        WebElement link = wrapper.findElement(By.linkText("Подробнее о сервисе"));

        String href = link.getAttribute("href");
        assertNotNull(href, "Атрибут href у ссылки отсутствует");
        assertTrue(href.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Ссылка ведет не на тот URL: " + href);

        String originalUrl = driver.getCurrentUrl();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        link.click();

        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost"));
        assertNotEquals(originalUrl, driver.getCurrentUrl(), "URL не изменился после клика по ссылке");
    }

    // T4: Заполнение формы и проверка кнопки оплаты
    @Test
    public void testFillFormAndContinue() {
        WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(PAY_FORM));

        WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".select__header")
        ));
        selectHeader.click();

        WebElement serviceOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[@class='select__option' and contains(text(), 'Услуги связи')]")
        ));
        serviceOption.click();

        WebElement phoneInput = form.findElement(By.id("connection-phone"));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement payByCardBtn = form.findElement(By.cssSelector("button[data-method='card']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", payByCardBtn);
        payByCardBtn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
    }
}
