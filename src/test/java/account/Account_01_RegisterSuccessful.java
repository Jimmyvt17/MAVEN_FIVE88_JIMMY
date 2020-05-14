package account;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.AccountPageObject;
import pageObjects.RegisterPageObject;

import java.lang.reflect.Method;
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
	private final String USERNAME_30 = "seta" + randomString(26, true, true);
	private final String PASSWORD_6 = randomString(6, false, true);
	private final String PHONE_14 = randomString(14, false, true);

//	@Test
//	public void TC_01_RegisterSuccessful(Method method) {
//		ExtentTestManager.startTest(method.getName(), "TC_01_RegisterSuccessful");
//
//		String USERNAME_TODAY = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//		log.info("RegisterSuccessful - Step 01: Register account");
//		register(USERNAME_TODAY, Constants.PASSWORD, Constants.PHONE);
//
//		log.info("RegisterSuccessful - Step 02: Logout");
//		accountPage.logoutToHomePage();
//		log.info("Register Successful");
//
//	}
//
//	@Test(dependsOnMethods = "TC_01_RegisterSuccessful")
//	public void TC_02_RegisterSuccessfulWithName6Letters(Method method) {
//		ExtentTestManager.startTest(method.getName(), "TC_02_RegisterSuccessfulWithName6Letters");
//
//		log.info("RegisterSuccessful - Step 01: Register account with name 6 letters");
//		register(USERNAME_6, Constants.PASSWORD, Constants.PHONE);
//
//		log.info("RegisterSuccessful - Step 02: Logout");
//		accountPage.logoutToHomePage();
//		log.info("Register Successful With Name 6 Letters");
//
//	}
//
//	@Test(dependsOnMethods = "TC_02_RegisterSuccessfulWithName6Letters")
//	public void TC_03_RegisterSuccessfulWithName30Letters(Method method) {
//		ExtentTestManager.startTest(method.getName(), "TC_03_RegisterSuccessfulWithName30Letters");
//
//		log.info("RegisterSuccessful - Step 01: Register account with name 30 letters");
//		register(USERNAME_30, Constants.PASSWORD, Constants.PHONE);
//
//		log.info("RegisterSuccessful - Step 02: Logout");
//		accountPage.logoutToHomePage();
//		log.info("Register Successful With Name 30 Letters");
//
//	}
//
//	@Test(dependsOnMethods = "TC_03_RegisterSuccessfulWithName30Letters")
//	public void TC_04_RegisterSuccessfulWithPass6Letters(Method method) {
//		ExtentTestManager.startTest(method.getName(), "TC_04_RegisterSuccessfulWithPass6Letters");
//
//		String USERNAME_PASS6 = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//		log.info("RegisterSuccessful - Step 01: Register account with pass 6 letters");
//		register(USERNAME_PASS6, PASSWORD_6, Constants.PHONE);
//
//		log.info("RegisterSuccessful - Step 02: Logout");
//		accountPage.logoutToHomePage();
//		log.info("Register Successful With Pass 6 Letters");
//
//	}
//
//	@Test(dependsOnMethods = "TC_04_RegisterSuccessfulWithPass6Letters")
//	public void TC_05_RegisterSuccessfulWithPhone14Letters(Method method) {
//		ExtentTestManager.startTest(method.getName(), "TC_05_RegisterSuccessfulWithPhone14Letters");
//
//		String USERNAME_PHONE14 = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//		log.info("RegisterSuccessful - Step 01: Register account with phone 14 letters");
//		register(USERNAME_PHONE14, Constants.PASSWORD, PHONE_14);
//
//		log.info("RegisterSuccessful - Step 02: Logout");
//		accountPage.logoutToHomePage();
//		log.info("Register Successful With Phone 14 Letters");
//
//	}

	@Test(dataProvider = "network")
	public void TC_06_RegisterData(String usernameData) {
		log.info("RegisterData - Step 01: Register account");
		register(usernameData, "@16WINner", Constants.PHONE);

		log.info("RegisterData - Step 02: Logout");
		accountPage.logoutToHomePage();

	}

	@DataProvider(name = "network")
	public static Object[][] AccountData(){
		return new Object[][] {
				{"seta011"},
				{"seta012"},
				{"seta013"},
				{"seta014"}

		};

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

	private void register(String username, String password, String phone) {
		registerPage.openRegisterPage();
		registerPage.inputToUsernameTextbox(username);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToPhoneTextbox(phone);
		accountPage = registerPage.clickToSubmitButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		verifyEquals(accountPage.getAccountPageUrl(), Constants.DEPOSIT_URL);
		String userName = accountPage.getUsername();
		verifyEquals(userName, username.toUpperCase());
		log.info("Ten nguoi dung la " + userName);

	}

}
