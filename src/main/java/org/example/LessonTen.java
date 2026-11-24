package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LessonTen {

    private WebDriver driver;
    private WebDriverWait wait;

    private By payWrapper = By.cssSelector("div.pay__wrapper");
    private By payPartners = By.cssSelector("div.pay__partners");
    private By cookieBanner = By.id("cookie-agree");

    private By tabServices = By.xpath("//p[@class='select__option' and contains(text(), 'Услуги связи')]");
    private By tabInternet = By.xpath("//p[@class='select__option' and contains(text(), 'Домашний интернет')]");
    private By tabInstalment = By.xpath("//p[@class='select__option' and contains(text(), 'Рассрочка')]");
    private By tabDebt = By.xpath("//p[@class='select__option' and contains(text(), 'Задолженность')]");

    private By moreDetailsLink = By.linkText("Подробнее о сервисе");
    private By bankCardButton = By.cssSelector("button[data-method='card']");

    public LessonTen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
        closeCookieBanner();
    }

    private void closeCookieBanner() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement acceptBtn = shortWait.until(ExpectedConditions.elementToBeClickable(cookieBanner));
            acceptBtn.click();
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cookie")));
        } catch (TimeoutException e) {
        }
    }
    public String getBlockTitle() {
        WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(payWrapper));
        return wrapper.findElement(By.tagName("h2")).getAttribute("textContent").replaceAll("\\s+", " ").trim();
    }

    public boolean areLogosPresent() {
        WebElement partners = wait.until(ExpectedConditions.presenceOfElementLocated(payPartners));
        return !partners.findElements(By.tagName("img")).isEmpty();
    }
    public void selectTab(String tabName) {
        By tabLocator;
        switch (tabName) {
            case "Услуги связи": tabLocator = tabServices; break;
            case "Домашний интернет": tabLocator = tabInternet; break;
            case "Рассрочка": tabLocator = tabInstalment; break;
            case "Задолженность": tabLocator = tabDebt; break;
            default: throw new IllegalArgumentException("Неизвестный вариант оплаты: " + tabName);
        }

        WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select__header")));
        selectHeader.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(tabLocator));
        option.click();
    }
    public String getInputIdForTab(String tabName) {
        switch (tabName) {
            case "Услуги связи": return "connection-phone";
            case "Домашний интернет": return "internet-phone";
            case "Рассрочка": return "score-instalment";
            case "Задолженность": return "score-arrears";
            default: throw new IllegalArgumentException("Неизвестная вкладка: " + tabName);
        }
    }
    public String getFieldPlaceholder(String inputId) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(inputId)));
        return input.getAttribute("placeholder");
    }
    public String getFieldPrefix(String inputId) {
        try {

            WebElement label = driver.findElement(By.cssSelector("label[for='" + inputId + "']"));
            return label.getText().trim();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
    public void fillPaymentForm(String tabName, String phoneOrAccount, String sum) {
        String inputId = getInputIdForTab(tabName);
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(inputId)));
        inputField.clear();
        inputField.sendKeys(phoneOrAccount);

        if (sum != null) {
            String sumInputId = inputId.replace("-phone", "-sum").replace("-instalment", "-sum").replace("-arrears", "-sum");
            WebElement sumField = driver.findElement(By.id(sumInputId));
            sumField.clear();
            sumField.sendKeys(sum);
        }
    }

    public String getMoreDetailsLinkHref() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(moreDetailsLink));
        return link.getAttribute("href");
    }

    public void clickMoreDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moreDetailsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        link.click();
    }
    public void waitForUrlContains(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickBankCardPayment() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(bankCardButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        btn.click();
    }

    //T6

    @FindBy(xpath = "//div[contains(@class, 'pay-description__cost')]//span")
    private WebElement orderCost;

    @FindBy(xpath = "//div[contains(@class, 'pay-description__text')]//span")
    private WebElement orderDescription;

    @FindBy(css = "iframe.payment-widget-iframe")
    private WebElement paymentIframe;
    @FindBy(css = "button[type='submit']")
    private WebElement payButton;
    @FindBy(css = "input[formcontrolname='creditCard']")
    private WebElement cardNumberInput;
    @FindBy(css = "input[formcontrolname='expirationDate']")
    private WebElement expirationDateInput;
    @FindBy(css = "input[formcontrolname='cvc']")
    private WebElement cvcInput;
    @FindBy(css = "input[formcontrolname='holder']")
    private WebElement holderInput;

    @FindBy(xpath = "//input[@formcontrolname='creditCard']/following-sibling::label")
    private WebElement cardNumberLabel;
    @FindBy(xpath = "//input[@formcontrolname='expirationDate']/following-sibling::label")
    private WebElement expirationDateLabel;
    @FindBy(xpath = "//input[@formcontrolname='cvc']/following-sibling::label")
    private WebElement cvcLabel;
    @FindBy(xpath = "//input[@formcontrolname='holder']/following-sibling::label")
    private WebElement holderLabel;

    @FindBy(css = "div.cards-brands div")
    private List<WebElement> paymentSystemIcons;

    public void switchToPaymentFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
    }

    public String getOrderCost() {
        wait.until(ExpectedConditions.visibilityOf(orderCost));
        if (orderCost == null) {
            throw new RuntimeException("Элемент стоимости заказа не найден. Проверьте локатор и переключение на iframe.");
        }
        return orderCost.getText().trim();
    }
    public String getOrderDescription() {
        wait.until(ExpectedConditions.visibilityOf(orderDescription));
        return orderDescription.getText().trim();
    }
    public String getPayButtonText() {
        wait.until(ExpectedConditions.visibilityOf(payButton));
        return payButton.getText().trim();
    }
    public String getCardNumberLabelText() {
        wait.until(ExpectedConditions.visibilityOf(cardNumberLabel));
        return cardNumberLabel.getText().trim();
    }

    public String getExpirationDateLabelText() {
        wait.until(ExpectedConditions.visibilityOf(expirationDateLabel));
        return expirationDateLabel.getText().trim();
    }

    public String getCvcLabelText() {
        wait.until(ExpectedConditions.visibilityOf(cvcLabel));
        return cvcLabel.getText().trim();
    }

    public String getHolderLabelText() {
        wait.until(ExpectedConditions.visibilityOf(holderLabel));
        return holderLabel.getText().trim();
    }

    public int getPaymentSystemsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemIcons));
        return (int) paymentSystemIcons.stream().filter(WebElement::isDisplayed).count();
    }
}
