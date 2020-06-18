package account;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import five88.AbstractPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;

import java.lang.reflect.Method;


public class Account_04_LoginUnsuccessful extends CommonsTest {

	WebDriver driver;
	HomePageObject homePage;

    @Parameters("browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		homePage = PageFactoryManager.getHomePage(driver);

	}


	private final String USERNAME_INVALID = "setainvalid";
	private final String PASSWORD_INVALID = "testjimmyinvalid";
	private final String usernameLoginError = "Yêu cầu nhập tên đăng nhập";
	private final String usernameLoginInvalid = "Tên đăng nhập hoặc mật khẩu không đúng\n" +
			"Quên mật khẩu";

	@Test
	public void TC_01_LoginUnsuccessfulWithoutInformation(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_LoginUnsuccessfulWithoutInformation");

		log.info("LoginUnsuccessfulWithoutInformation - Step 01: Click login button without inputting data");
		homePage.clickToLoginButton(driver);

		log.info("LoginUnsuccessfulWithoutInformation - Step 02: Verify warning dialog");
		verifyEquals(homePage.getLoginErrorText(), usernameLoginError);
		homePage.refreshHomePage();

		log.info("Dang nhap ko thanh cong ko nhap thong tin");

	}

	@Test
	public void TC_02_LoginUnsuccessfulWithInvalidName(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_02_LoginUnsuccessfulWithInvalidName");

		log.info("LoginUnsuccessfulWithInvalidName - Step 01: Input invalid username");
		homePage.login(driver, USERNAME_INVALID, Constants.PASSWORD);

		log.info("LoginUnsuccessfulWithInvalidName - Step 02: Verify warning dialog");
		verifyEquals(homePage.getLoginErrorText(), usernameLoginInvalid);
		homePage.refreshHomePage();

		log.info("Dang nhap ko thanh cong nhap ten sai");

	}

	@Test
	public void TC_03_LoginUnsuccessfulWithInvalidPass(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_LoginUnsuccessfulWithInvalidPass");

		log.info("LoginUnsuccessfulWithInvalidPass - Step 01: Input invalid password");
		homePage.login(driver, Constants.USERNAME_LOGIN, PASSWORD_INVALID);

		log.info("LoginUnsuccessfulWithInvalidPass - Step 02: Verify warning dialog");
		verifyEquals(homePage.getLoginErrorText(), usernameLoginInvalid);
		homePage.refreshHomePage();

		log.info("Dang nhap ko thanh cong nhap pass sai");

	}

	@Test
	public void TC_04_LoginUnsuccessfulWithInvalidNamePass(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_04_LoginUnsuccessfulWithInvalidNamePass");

		log.info("LoginUnsuccessfulWithInvalidNamePass - Step 01: Input invalid username and password");
		homePage.login(driver, USERNAME_INVALID, PASSWORD_INVALID);

		log.info("LoginUnsuccessfulWithInvalidNamePass - Step 02: Verify warning dialog");
		verifyEquals(homePage.getLoginErrorText(), usernameLoginInvalid);
		homePage.refreshHomePage();

		log.info("Dang nhap ko thanh cong nhap ten va pass sai");

	}

	@Test
	public void TC_05_LoginUnsuccessfulWithoutName(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_05_LoginUnsuccessfulWithoutName");

		log.info("LoginUnsuccessfulWithoutName - Step 01: Input data without username");
		homePage.clearTextElement(driver, AbstractPageUI.usernameLoginLocator);
		homePage.inputToPasswordTextBox(driver, Constants.PASSWORD);
		homePage.clickToLoginButton(driver);

		log.info("LoginUnsuccessfulWithoutName - Step 02: Verify warning dialog");
		verifyEquals(homePage.getLoginErrorText(), usernameLoginError);
		homePage.refreshHomePage();

		log.info("Dang nhap ko thanh cong ko nhap ten");

	}

	@Test
	public void TC_06_LoginUnsuccessfulWithoutPass(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_06_LoginUnsuccessfulWithoutPass");

		log.info("LoginUnsuccessfulWithoutPass - Step 01: Input data without password");
		homePage.inputToUsernameTextBox(driver, Constants.USERNAME_LOGIN);
		homePage.clearTextElement(driver, AbstractPageUI.passwordLoginLocator);
		homePage.clickToLoginButton(driver);

		log.info("LoginUnsuccessfulWithoutPass - Step 02: Verify warning dialog");
		verifyEquals(homePage.getLoginErrorText(), "Yêu cầu nhập mật khẩu đăng nhập");
		homePage.refreshHomePage();

		log.info("Dang nhap ko thanh cong ko nhap pass");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
