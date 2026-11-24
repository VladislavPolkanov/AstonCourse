import org.example.LessonTen;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMts {
    private WebDriver driver;
    private LessonTen paymentPage;
    private static final String BASE_URL = "https://www.mts.by/";
    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        paymentPage = new LessonTen(driver);
        paymentPage.open(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    //1
    @Test
    public void testBlockTitle() {
        String text = paymentPage.getBlockTitle();
        assertTrue(text.contains("Онлайн пополнение"), "Заголовок не содержит 'Онлайн пополнение'");
        assertTrue(text.contains("без комиссии"), "Заголовок не содержит 'без комиссии'");
    }

    //2
    @Test
    public void testPaymentLogos() {
        assertTrue(paymentPage.areLogosPresent(), "Логотипы платёжных систем не найдены");
    }
    // T3: Проверка работы ссылки «Подробнее о сервисе»
    @Test
    public void testMoreDetailsLink() {
        String href = paymentPage.getMoreDetailsLinkHref();
        assertNotNull(href, "Атрибут href у ссылки отсутствует");
        assertTrue(href.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Ссылка ведет не на тот URL: " + href);

        String originalUrl = paymentPage.getCurrentUrl();

        paymentPage.clickMoreDetailsLink();

        paymentPage.waitForUrlContains("poryadok-oplaty-i-bezopasnost");
        assertNotEquals(originalUrl, paymentPage.getCurrentUrl(), "URL не изменился после клика по ссылке");
    }
    // T4: Заполнение формы и проверка появления окна оплаты (iframe)
    @Test
    public void testFillFormAndContinue() {
        String tabName = "Услуги связи";
        paymentPage.selectTab(tabName);

        paymentPage.fillPaymentForm(tabName, "297777777", "111");

        paymentPage.clickBankCardPayment();

        paymentPage.switchToPaymentFrame();

    }
    // T5: Проверка надписей в незаполненных полях каждого варианта оплаты услуг
    @Test
    public void testPlaceholdersForAllTabs() {

        // --- 1. Услуги связи ---
        paymentPage.selectTab("Услуги связи");
        String idServices = paymentPage.getInputIdForTab("Услуги связи");
        String placeholderServices = paymentPage.getFieldPlaceholder(idServices);
        String prefixServices = paymentPage.getFieldPrefix(idServices);

        System.out.println("Вкладка 'Услуги связи': Placeholder = [" + placeholderServices + "], Префикс = [" + prefixServices + "]");
        assertEquals("Номер телефона", placeholderServices, "Неверный placeholder для 'Услуги связи'");
        assertEquals("+375", prefixServices, "Отсутствует или неверный префикс +375");

        // --- 2. Домашний интернет ---
        paymentPage.selectTab("Домашний интернет");
        String idInternet = paymentPage.getInputIdForTab("Домашний интернет");
        String placeholderInternet = paymentPage.getFieldPlaceholder(idInternet);
        String prefixInternet = paymentPage.getFieldPrefix(idInternet);

        System.out.println("Вкладка 'Домашний интернет': Placeholder = [" + placeholderInternet + "], Префикс = [" + prefixInternet + "]");
        assertEquals("Номер абонента", placeholderInternet, "Неверный placeholder для 'Домашний интернет'");
        assertEquals("+375", prefixInternet, "Отсутствует или неверный префикс +375");

        // --- 3. Рассрочка ---
        paymentPage.selectTab("Рассрочка");
        String idInstalment = paymentPage.getInputIdForTab("Рассрочка");
        String placeholderInstalment = paymentPage.getFieldPlaceholder(idInstalment);

        System.out.println("Вкладка 'Рассрочка': Placeholder = [" + placeholderInstalment + "]");
        assertEquals("Номер счета на 44", placeholderInstalment, "Неверный placeholder для 'Рассрочка'");

        // --- 4. Задолженность ---
        paymentPage.selectTab("Задолженность");
        String idDebt = paymentPage.getInputIdForTab("Задолженность");
        String placeholderDebt = paymentPage.getFieldPlaceholder(idDebt);

        System.out.println("Вкладка 'Задолженность': Placeholder = [" + placeholderDebt + "]");
        assertEquals("Номер счета на 2073", placeholderDebt, "Неверный placeholder для 'Задолженность'");
    }

    // T6: Заполнение формы, нажатие кнопки и проверка окна оплаты
    @Test
    public void testPaymentFormVerification() {

        // 1. Выбор вкладки "Услуги связи"
        String tabName = "Услуги связи";
        paymentPage.selectTab(tabName);

        String phone = "297777777";
        String sum = "111";
        paymentPage.fillPaymentForm(tabName, phone, sum);

        paymentPage.clickBankCardPayment();
        paymentPage.switchToPaymentFrame();

        String actualCost = paymentPage.getOrderCost();
        Assertions.assertEquals("111.00 BYN", actualCost, "Стоимость заказа не совпадает!");

        String actualDescription = paymentPage.getOrderDescription();
        Assertions.assertTrue(actualDescription.contains("Услуги связи"), "Услуга не найдена!");
        Assertions.assertTrue(actualDescription.contains("375297777777"), "Номер не найден!");

        // Проверка текста на кнопке оплаты
        String expectedButtonText = "Оплатить 111.00 BYN";
        Assertions.assertEquals(expectedButtonText, paymentPage.getPayButtonText(),
                "Текст на кнопке оплаты не совпадает!");
        // Проверка надписей (Labels) в полях ввода

        Assertions.assertEquals("Номер карты", paymentPage.getCardNumberLabelText(),
                "Подпись поля 'Номер карты' не совпадает!");

        Assertions.assertEquals("Срок действия", paymentPage.getExpirationDateLabelText(),
                "Подпись поля 'Срок действия' не совпадает!");

        Assertions.assertEquals("CVC", paymentPage.getCvcLabelText(),
                "Подпись поля 'CVC' не совпадает!");

        Assertions.assertEquals("Имя и фамилия на карте", paymentPage.getHolderLabelText(),
                "Подпись поля 'Имя и фамилия на карте' не совпадает!");

        // Проверка иконок платежных систем
        int iconsCount = paymentPage.getPaymentSystemsCount();
        Assertions.assertTrue(iconsCount > 0,
                "Иконки платежных систем не найдены или не отображаются!");
    }
}
