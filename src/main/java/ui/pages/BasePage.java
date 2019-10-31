package ui.pages;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import java.util.Properties;

public class BasePage {
    public static final int WEBELEMENT_TIMEOUT = 5;
    private static final String PATH_SETTINGS = "./resources/settings";
    private static String pageUrl;
    protected static Properties settings = PropertiesReader.load(PATH_SETTINGS);


    protected WebDriver driver;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //Wait
    public boolean waitVisibility(WebElement webElement, boolean toErrorIfElementNotFound, int timeout) {
        boolean elementFound;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            elementFound=true;
        } catch (Exception e) {
            elementFound=false;
            if(toErrorIfElementNotFound){
                throw new IllegalStateException("Element not found", e);
            }
        }
        return elementFound;
    }

    public boolean waitVisibility(WebElement webElement, boolean toErrorIfElementNotFound){
        return this.waitVisibility(webElement,toErrorIfElementNotFound,WEBELEMENT_TIMEOUT);
    }
    public void open() throws NoSuchFieldException, IllegalAccessException {
        String url = String.valueOf(getClass().getField("pageUrl").get(null));
        driver.get(url);

    }



}
