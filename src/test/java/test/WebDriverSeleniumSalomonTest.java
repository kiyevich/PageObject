package test;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.salomon.SalomonSearchResult;

import java.util.concurrent.TimeUnit;


public class WebDriverSeleniumSalomonTest {
    WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Test
    public void SearchItemTest() throws InterruptedException {
        String searchData = "OUTBLAST TS CSWP";
        String firstSearchResultTitle = new SalomonSearchResult(driver).openPage()
                .searchForItem(searchData)
                .getTitleOfFirstSearchResult();
        System.out.print(searchData.toLowerCase() + " - " + firstSearchResultTitle.toLowerCase());

        Assert.assertEquals(searchData.toLowerCase(), firstSearchResultTitle.toLowerCase());
    }

    @AfterMethod(alwaysRun = true)
    public void BrowserTearDown() {
        driver.quit();
    }

    }
