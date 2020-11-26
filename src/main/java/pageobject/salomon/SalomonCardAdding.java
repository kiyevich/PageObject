package pageobject.salomon;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.Page;

public class SalomonCardAdding extends Page {

    @FindBy(xpath = "//button[text() = 'Добавить в корзину']")
    WebElement addCardButton;

    @FindBy(xpath = "//a[@class='header__button js-vue-small-cart']")
    WebElement CardButton;

    @FindBy(xpath = "//li[@class='checkout-item']/a")
    WebElement CardItemTitle;

    @FindBy(xpath = "(//p[@class='checkout-item__props']/span)[2]")
    WebElement CardItemSize;

    @FindBy(xpath = "(//p[@class='price']/span)[3]")
    WebElement CardItemPrice;

    private static final String PAGE_URL = "https://salomon.ru/catalog/muzhchiny/vidy_sporta/turizm_i_alpinizm/13058/";
    public SalomonCardAdding(WebDriver driver){
        super(driver);
    }
    public SalomonCardAdding openPage(){
        driver.get(PAGE_URL);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        return this;
    }

    public SalomonCardAdding addItemToCard(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(addCardButton))
                .click();
        return new SalomonCardAdding(driver);
    }

    public SalomonCardAdding goToCard(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(CardButton))
                .click();
        return new SalomonCardAdding(driver);
    }

    public String getCardItemTitle(){
        String ResultCardItemTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(CardItemTitle))
                .getText();

        return ResultCardItemTitle;
    }

    public String getCardItemSize(){
        String ResultCardItemSize = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(CardItemSize))
                .getText();

        return ResultCardItemSize;
    }

    public String getCardItemPrice(){
        String ResultCardItemPrice = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(CardItemPrice))
                .getText();

        return ResultCardItemPrice;
    }

}
