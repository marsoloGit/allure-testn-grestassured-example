package ui.pages;

import org.testng.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    public static final String pageUrl = "https://passport.yandex.ru/registration";

    @FindBy(id = "firstname")
    private WebElement inputFirstName;
    @FindBy(id = "lastname")
    private WebElement inputLastName;
    @FindBy(id = "login")
    private WebElement inputLogin;
    @FindBy(id = "password")
    private WebElement inputPassword;
    @FindBy(id = "password_confirm")
    private WebElement inputPasswordConfirm;
    @FindBy(id = "phone")
    private WebElement inputPhone;
    @FindBy(id = "phoneCode")
    private WebElement inputPhoneCode;
    @FindBy(css = "button.button2_view_classic")
    private WebElement btnConfirm;
    @FindBy(css = "span.toggle-link.link_has-no-phone")
    private WebElement linkHasNoPhone;
    @FindBy(css = "button.js-submit")
    private WebElement btnRegister;
    @FindBy(css = "div.error-message")
    private WebElement errorMessage;

    @Step("Go through registration steps and enter wrong sms code")
    public RegistrationPage registerUserWithWrongPhoneCode(String firstname, String lastname, String login, String password, String phone, String phoneCode){
        waitVisibility(inputFirstName,true);
        inputFirstName.sendKeys(firstname);
        inputLastName.sendKeys(lastname);
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        inputPasswordConfirm.sendKeys(password);
        inputPhone.clear();
        inputPhone.sendKeys(phone);
        btnRegister.click();
        waitVisibility(inputPhoneCode,true);
        inputPhoneCode.sendKeys(phoneCode);
        btnConfirm.click();
        return this;


    }

    @Step("Verify that error message appeared")
    public void checkForErrorAboutWrongCode(){
        String expectedError = "Неправильный код, попробуйте ещё раз";
        waitVisibility(errorMessage,true);
        Assert.assertTrue(errorMessage.getText().contains(expectedError),
                "Expected: "+expectedError+ "\nActual:"+ errorMessage.getText());
    }


}
