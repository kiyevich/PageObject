package test;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject.salomon.SalomonCardAdding;
import pageobject.salomon.SalomonSearchResult;

import java.util.concurrent.TimeUnit;


public class WebDriverSeleniumSalomonTest {
    WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup(){
        System.setProperty("webdriver.chrome.driver", "D://WebDriver/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.addArguments("--lang=ru");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test()
    public void SearchItemTest() throws InterruptedException {
        String searchData = "OUTBLAST TS CSWP";
        String firstSearchResultTitle = new SalomonSearchResult(driver)
                .openPage()
                .searchForItem(searchData)
                .getTitleOfFirstSearchResult();
        Assert.assertEquals(searchData.toLowerCase(), firstSearchResultTitle.toLowerCase());
    }

    @Test
    public void CardAddingTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        new SalomonCardAdding(driver)
                .openPage()
                .addItemToCard()
                .goToCard();

        softAssert.assertEquals(new SalomonCardAdding(driver).getCardItemTitle(), "OUTBLAST TS CSWP");
        softAssert.assertEquals(new SalomonCardAdding(driver).getCardItemSize(), "Размер : 9 uk");
        softAssert.assertEquals(new SalomonCardAdding(driver).getCardItemPrice(), "11 990 ₽");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void BrowserTearDown() {
        driver.quit();
    }

}
