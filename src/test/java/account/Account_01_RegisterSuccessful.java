package account;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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

		driver = openMultiBrowser(browserName, Constants.REGISTER_URL);
		registerPage = PageFactoryManager.getRegisterPage(driver);

	}

//	private final String USERNAME_6 = "auto-" + randomString(1, true, true);
//	private final String USERNAME_30 = "auto-seta" + randomString(21, true, true);
//	private final String PASSWORD_6 = randomString(6, false, true);
//	private final String PHONE_14 = randomString(14, false, true);
//
	@Test
	public void TC_01_RegisterSuccessful(Method method) {
		try {
			ExtentTestManager.startTest("TC_01_RegisterSuccessful", "TC_01_RegisterSuccessful");

			String USERNAME_TODAY = "auto-seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

			log.info("RegisterSuccessful - Step 01: Register account");
			register(USERNAME_TODAY, Constants.PASSWORD, Constants.PHONE);

			log.info("RegisterSuccessful - Step 02: Logout");
			accountPage.logoutToHomePage();
//			registerPage.registers();
		} catch (Throwable e) {
			convertException(e, Constants.prefix + method.getName() + "\n");
		}

	}
//
////	@Test
////	public void TC_02_RegisterSuccessfulWithName6Letters(Method method) {
////		try {
////			ExtentTestManager.startTest("TC_02_RegisterSuccessfulWithName6Letters", "TC_02_RegisterSuccessfulWithName6Letters");
////
////			log.info("RegisterSuccessfulWithName6Letters - Step 01: Register account with name 6 letters");
////			register(USERNAME_6, Constants.PASSWORD, Constants.PHONE);
////
////			log.info("RegisterSuccessfulWithName6Letters - Step 02: Logout");
////			accountPage.logoutToHomePage();
////		} catch (Throwable e) {
////			convertException(e, Constants.prefix + method.getName() + "\n");
////		}
////
////	}
//
////	@Test
////	public void TC_03_RegisterSuccessfulWithName30Letters(Method method) {
////		try {
////			ExtentTestManager.startTest("TC_03_RegisterSuccessfulWithName30Letters", "TC_03_RegisterSuccessfulWithName30Letters");
////
////			log.info("RegisterSuccessfulWithName30Letters - Step 01: Register account with name 30 letters");
////			register(USERNAME_30, Constants.PASSWORD, Constants.PHONE);
////
////			log.info("RegisterSuccessfulWithName30Letters - Step 02: Logout");
////			accountPage.logoutToHomePage();
////		} catch (Throwable e) {
////			convertException(e, Constants.prefix + method.getName() + "\n");
////		}
////
////	}
//
//	@Test
//	public void TC_04_RegisterSuccessfulWithPass6Letters(Method method) {
//		try {
//			ExtentTestManager.startTest("TC_04_RegisterSuccessfulWithPass6Letters", "TC_04_RegisterSuccessfulWithPass6Letters");
//
//			String USERNAME_PASS6 = "auto-seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//			log.info("RegisterSuccessfulWithPass6Letters - Step 01: Register account with pass 6 letters");
//			register(USERNAME_PASS6, PASSWORD_6, Constants.PHONE);
//
//			log.info("RegisterSuccessfulWithPass6Letters - Step 02: Logout");
//			accountPage.logoutToHomePage();
//		} catch (Throwable e) {
//			convertException(e, Constants.prefix + method.getName() + "\n");
//		}
//
//	}
//
//	@Test
//	public void TC_05_RegisterSuccessfulWithPhone14Letters(Method method) {
//		try {
//			ExtentTestManager.startTest("TC_05_RegisterSuccessfulWithPhone14Letters", "TC_05_RegisterSuccessfulWithPhone14Letters");
//
//			String USERNAME_PHONE14 = "auto-seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//			log.info("RegisterSuccessfulWithPhone14Letters - Step 01: Register account with phone 14 letters");
//			register(USERNAME_PHONE14, Constants.PASSWORD, PHONE_14);
//
//			log.info("RegisterSuccessfulWithPhone14Letters - Step 02: Logout");
//			accountPage.logoutToHomePage();
//		} catch (Throwable e) {
//			convertException(e, Constants.prefix + method.getName() + "\n");
//		}
//
//	}
//
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

//	@DataProvider(name = "network")
//	public static Object[][] AccountData(){
//		return new Object[][] {
//				{"setastgxocdia9"},
//				{"setastgxocdia10"},
//				{"setastgxocdia11"},
//				{"setastgxocdia12"},
//				{"setastgxocdia13"},
//				{"setastgxocdia14"},
//				{"setastgxocdia15"},
//				{"setastgxocdia16"},
//				{"setastgxocdia17"},
//				{"setastgxocdia18"},
//				{"setastgxocdia19"},
//				{"setastgxocdia20"}
//
//		};
//
//	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {

		closeBrowserAndDriver(driver);

	}

	private void register(String username, String password, String phone) {
			registerPage.inputToUsernameTextBox(username);
			registerPage.inputToPasswordTextBox(password);
			registerPage.inputToPhoneTextBox(phone);
			accountPage = registerPage.clickToSubmitButton();
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			accountPage.verifyAfterRegister(accountPage.getAccountPageUrl(), Constants.DEPOSIT_URL);
			String userName = accountPage.getUsername();
			verifyEquals(userName, username.toUpperCase());
			log.info("Username = " + userName);

			log.info("Record " + userName + " into data file");
			accountPage.saveUsername(userName);

	}

}
