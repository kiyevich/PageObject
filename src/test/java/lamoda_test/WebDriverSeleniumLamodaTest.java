package lamoda_test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSeleniumLamodaTest{
    WebDriver chrome;

    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chrome = new ChromeDriver(chromeOptions);
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void AddingItemsToCartTest() throws InterruptedException {


        chrome.get("https://www.lamoda.by/men-home/");
        WebElement searchButton = chrome.findElement(By.xpath("//div[@class='button button_blue search__button js-search-button']"));
        searchButton.click();
        WebElement searchInput = chrome.findElement(By.xpath("//input[@placeholder='Поиск']"));
        searchInput.sendKeys("SA007AMJONT5");
        searchInput.sendKeys(Keys.ENTER);
        WebElement sizeChoiceCombobox = chrome.findElement(By.xpath("//div[@class='product-sizes-select ii-select__value ii-select__value_2line']"));
        sizeChoiceCombobox.click();
        WebElement targetSize = chrome.findElement(By.xpath("//div[@data-brand-size='6,5']"));
        targetSize.click();
        WebElement addToCartButton = chrome.findElement(By.xpath("//button[@title='Добавить в корзину']"));
        addToCartButton.click();
        WebElement goToCartButton = chrome.findElement(By.xpath("//div[@class='post-cart-add__footer']/div[2]"));
        goToCartButton.click();
        List<WebElement> itemsInCart = chrome.findElements(By.xpath("//div[@data-sku='SA007AMJONT5B065']"));
        Assert.assertTrue(itemsInCart.size()>0,"The item has not been added to the cart");



    }

    @AfterMethod(alwaysRun = true)
    public void BrowserTearDown() {
        chrome.quit();
    }

    }
