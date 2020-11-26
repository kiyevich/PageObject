package pageobject.salomon;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.Page;

public class SalomonSearchResult extends Page {

    @FindBy(xpath = "//button[@class='header__button mod-no-lg js-search']")
    WebElement searchButton;

    @FindBy(xpath = "//*[@id='header-search']")
    WebElement searchField;

    @FindBy(xpath = "(//h3[1][@class='product-card__title'])[1]")
    WebElement firstSearchResult;

    private static final String HOMEPAGE_URL = "https://salomon.ru/";
    public SalomonSearchResult(WebDriver driver){
        super(driver);
    }
    public SalomonSearchResult openPage(){
        driver.get(HOMEPAGE_URL);

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public SalomonSearchResult searchForItem(String itemName){


        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(searchField))
                .click();

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(searchField))
                .sendKeys( itemName + Keys.ENTER);

        return new SalomonSearchResult(driver);
    }

    public String getTitleOfFirstSearchResult() {
       String firstSearchResultTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(firstSearchResult))
                .getText();

        return firstSearchResultTitle;
    }

}
