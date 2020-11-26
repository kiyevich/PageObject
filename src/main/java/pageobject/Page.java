package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

public abstract class Page {

    protected static final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;

    protected Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}