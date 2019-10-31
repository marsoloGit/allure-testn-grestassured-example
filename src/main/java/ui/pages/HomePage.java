package ui.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    public static final String pageUrl = "https://passport.yandex.ru/auth/welcome";

    @FindBy(css = ".user-account.user2__current-account")
    private WebElement inputLogin;

    @Step("Verify that login is successful")
    public void checkSuccessfulLogin(){
        waitVisibility(inputLogin,true);
    }


}
