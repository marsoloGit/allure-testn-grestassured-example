package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public static final String pageUrl = "https://passport.yandex.ru/profile";

    @FindBy(id = "passp-field-login")
    private WebElement inputLogin;
    @FindBy(id = "passp-field-passwd")
    private WebElement inputPassword;
    @FindBy(css = ".passp-sign-in-button")
    private WebElement btnLogin;
    @FindBy(css = "[data-t=\"phone_skip\"]")
    private WebElement btnNotNow;
    @FindBy(css = "div.passp-form-field__error")
    private WebElement passwordError;


    @Step("Enter login")
    public LoginPage enterLogin(String username) {
        inputLogin.sendKeys(username);
        btnLogin.click();
        return this;
    }

    @Step("Verify that error message appeared")
    public void checkForErrorAboutWrongPassword(){
        String expectedError = "Такой логин не подойдет";
        waitVisibility(passwordError,true);
        String actualError = passwordError.getText();
        Assert.assertTrue(actualError.contains(expectedError),
                "\nExpected: "+expectedError+ "\nActual:"+ actualError);
    }

    @Step("Login")
    public HomePage login(String username, String password) {
        enterLogin(username);
        waitVisibility(inputPassword,true);
        inputPassword.sendKeys(password);
        waitVisibility(btnLogin,true);
        btnLogin.click();
//        click on the Не сейчас btn in case if it appears
        if (waitVisibility(btnNotNow,false)){
            btnNotNow.click();
        };
        return new HomePage(driver);
    }


}
