package account;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.AccountPageObject;
import pageObjects.RegisterPageObject;

import java.util.Date;

public class Account_01_RegisterSuccessful extends CommonsTest {

	WebDriver driver;
	RegisterPageObject registerPage;
	AccountPageObject accountPage;

	@Parameters("browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);
		registerPage = PageFactoryManager.getRegisterPage(driver);

	}
	
	private final String USERNAME_6 = "seta" + randomString(2, true, true);
	private final String USERNAME_25 = "seta" + randomString(21, true, true);
	private final String PASSWORD_6 = randomString(6, false, true);
	private final String PHONE_14 = randomString(14, false, true);

	@Test
	public void TC_01_RegisterSuccessful() {
		ExtentTestManager.startTest("TC_01_RegisterSuccessful", "TC_01_RegisterSuccessful");

		String USERNAME_TODAY = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		log.info("RegisterSuccessful - Step 01: Register account");
		register(USERNAME_TODAY, Constants.PASSWORD, Constants.PHONE);

		log.info("RegisterSuccessful - Step 02: Logout");
		accountPage.logoutToHomePage();
		log.info("Register Successful");

	}

	@Test
	public void TC_02_RegisterSuccessfulWithName6Letters() {
		ExtentTestManager.startTest("TC_02_RegisterSuccessfulWithName6Letters", "TC_02_RegisterSuccessfulWithName6Letters");

		log.info("RegisterSuccessfulWithName6Letters - Step 01: Register account with name 6 letters");
		register(USERNAME_6, Constants.PASSWORD, Constants.PHONE);

		log.info("RegisterSuccessfulWithName6Letters - Step 02: Logout");
		accountPage.logoutToHomePage();
		log.info("Register Successful With Name 6 Letters");

	}

	@Test
	public void TC_03_RegisterSuccessfulWithName25Letters() {
		ExtentTestManager.startTest("TC_03_RegisterSuccessfulWithName25Letters", "TC_03_RegisterSuccessfulWithName25Letters");

		log.info("RegisterSuccessfulWithName25Letters - Step 01: Register account with name 25 letters");
		register(USERNAME_25, Constants.PASSWORD, Constants.PHONE);

		log.info("RegisterSuccessfulWithName25Letters - Step 02: Logout");
		accountPage.logoutToHomePage();
		log.info("Register Successful With Name 30 Letters");

	}

	@Test
	public void TC_04_RegisterSuccessfulWithPass6Letters() {
		ExtentTestManager.startTest("TC_04_RegisterSuccessfulWithPass6Letters", "TC_04_RegisterSuccessfulWithPass6Letters");

		String USERNAME_PASS6 = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		log.info("RegisterSuccessfulWithPass6Letters - Step 01: Register account with pass 6 letters");
		register(USERNAME_PASS6, PASSWORD_6, Constants.PHONE);

		log.info("RegisterSuccessfulWithPass6Letters - Step 02: Logout");
		accountPage.logoutToHomePage();
		log.info("Register Successful With Pass 6 Letters");

	}

	@Test
	public void TC_05_RegisterSuccessfulWithPhone14Letters() {
		ExtentTestManager.startTest("TC_05_RegisterSuccessfulWithPhone14Letters", "TC_05_RegisterSuccessfulWithPhone14Letters");

		String USERNAME_PHONE14 = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		log.info("RegisterSuccessfulWithPhone14Letters - Step 01: Register account with phone 14 letters");
		register(USERNAME_PHONE14, Constants.PASSWORD, PHONE_14);

		log.info("RegisterSuccessfulWithPhone14Letters - Step 02: Logout");
		accountPage.logoutToHomePage();
		log.info("Register Successful With Phone 14 Letters");

	}

//	@Test(dataProvider = "network")
//	public void TC_06_RegisterData(String usernameData) {
//		ExtentTestManager.startTest("TC_06_RegisterData", "TC_06_RegisterData");
//
//		log.info("RegisterData - Step 01: Register account");
//		register(usernameData, Constants.PASSWORD, Constants.PHONE);
//
//		log.info("RegisterData - Step 02: Logout");
//		accountPage.logoutToHomePage();
//
//	}

	@DataProvider(name = "network")
	public static Object[][] AccountData(){
		return new Object[][] {
				{Constants.USERNAME},
				{Constants.USERNAME_CASINO},
				{Constants.USERNAME_KENO},
				{Constants.USERNAME_LODE},
				{Constants.USERNAME_LOGIN},
				{Constants.USERNAME_NUMBER},
				{Constants.USERNAME_QUAYSO},
				{Constants.USERNAME_THETHAO}

		};

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

	private void register(String username, String password, String phone) {
			registerPage.openRegisterPage();
			registerPage.inputToUsernameTextBox(username);
			registerPage.inputToPasswordTextBox(password);
			registerPage.inputToPhoneTextBox(phone);
			accountPage = registerPage.clickToSubmitButton();
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			verifyEquals(accountPage.getAccountPageUrl(), Constants.DEPOSIT_URL);
			String userName = accountPage.getUsername();
			verifyEquals(userName, username.toUpperCase());
			log.info("Ten nguoi dung la " + userName);

			log.info("Save " + userName + " vao data file");
			registerPage.saveUsername(userName);

	}

}
