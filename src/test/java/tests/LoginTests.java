package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.RegistrationPage;

import java.util.Date;


@Severity(SeverityLevel.BLOCKER)
public class LoginTests extends BasicTest {
	private WebDriver driver;
	private String timestamp= String.valueOf(new Date().getTime());
	private String firstname = "John";
	private String lastname = "Lee";
	private String login = "login"+timestamp;
	private String password = "myPassw0rd#";
	private String phone = "9215555555";
	private String phoneCode = "3333";



	@BeforeClass
	@Step("Open browser")
	public void setUp() {
 		driver = new ChromeDriver();

	}

	@AfterClass
	@Step("Close browser")
	public  void cleanUp() {
		driver.quit();
	}

	@Test (priority = 0, description="Register User")
	@Description("Register with all valid details and no phone")
	public void registerUser() throws NoSuchFieldException, IllegalAccessException {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.open();
		registrationPage.registerUserWithWrongPhoneCode(firstname, lastname,login,password,phone,phoneCode);
		registrationPage.checkForErrorAboutWrongCode();
	}

	@Test (priority = 1, description="Valid Login Scenario with valid username and password.")
	@Description("Login test with valid username and wrong password.")
	public void loginWithValidCredentials() throws NoSuchFieldException, IllegalAccessException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		HomePage homePage = loginPage.login(settings.getProperty("userLogin"),settings.getProperty("userPassword"));
		homePage.checkSuccessfulLogin();

	}

	@Test (priority = 0, description="Negative Login Scenario with valid but non existing username.")
	@Description("Login test with non existing username.")
	public void loginWithWrongCredentials() throws NoSuchFieldException, IllegalAccessException {
		String invalidUsername = "123@yandex.ru";
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		loginPage.enterLogin(invalidUsername);
		loginPage.checkForErrorAboutWrongPassword();

	}

}
