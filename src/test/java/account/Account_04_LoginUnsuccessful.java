package account;

import commons.CommonsTest;


public class Account_04_LoginUnsuccessful extends CommonsTest {

//	WebDriver driver;
//	HomePageObject homePage;
//
//    @Parameters("browser")
//	@BeforeClass
//	public void preConditions(String browserName) {
//
//		driver = openMultiBrowser(browserName, Constants.HOME_URL);
//		homePage = PageFactoryManager.getHomePage(driver);
//
//	}
//
//	private final String USERNAME_INVALID = "setainvalid";
//	private final String PASSWORD_INVALID = "testjimmyinvalid";
//	private final String usernameLoginError = "Yêu cầu nhập tên đăng nhập";
//	private final String usernameLoginInvalid = "Tên đăng nhập hoặc mật khẩu không đúng\n" +
//			"Quên mật khẩu";
//
//	@Test
//	public void TC_01_LoginUnsuccessfulWithoutInformation() {
//		ExtentTestManager.startTest("TC_01_LoginUnsuccessfulWithoutInformation", "TC_01_LoginUnsuccessfulWithoutInformation");
//
//		log.info("LoginUnsuccessfulWithoutInformation - Step 01: Click login button without inputting data");
//		homePage.clickToLoginButton(driver);
//
//		log.info("LoginUnsuccessfulWithoutInformation - Step 02: Verify warning dialog");
//		verifyEquals(homePage.getLoginErrorText(), usernameLoginError);
//		homePage.closeWarningDialog();
//
//		log.info("Dang nhap ko thanh cong ko nhap thong tin");
//
//	}
//
//	@Test
//	public void TC_02_LoginUnsuccessfulWithInvalidName() {
//		ExtentTestManager.startTest("TC_02_LoginUnsuccessfulWithInvalidName", "TC_02_LoginUnsuccessfulWithInvalidName");
//
//		log.info("LoginUnsuccessfulWithInvalidName - Step 01: Input invalid username");
//		homePage.login(driver, USERNAME_INVALID, Constants.PASSWORD);
//
//		log.info("LoginUnsuccessfulWithInvalidName - Step 02: Verify warning dialog");
//		verifyEquals(homePage.getLoginErrorText(), usernameLoginInvalid);
//		homePage.closeWarningDialog();
//
//		log.info("Dang nhap ko thanh cong nhap ten sai");
//
//	}
//
//	@Test
//	public void TC_03_LoginUnsuccessfulWithInvalidPass() {
//		ExtentTestManager.startTest("TC_03_LoginUnsuccessfulWithInvalidPass", "TC_03_LoginUnsuccessfulWithInvalidPass");
//
//		log.info("LoginUnsuccessfulWithInvalidPass - Step 01: Input invalid password");
//		homePage.login(driver, Constants.USERNAME_LOGIN, PASSWORD_INVALID);
//
//		log.info("LoginUnsuccessfulWithInvalidPass - Step 02: Verify warning dialog");
//		verifyEquals(homePage.getLoginErrorText(), usernameLoginInvalid);
//		homePage.closeWarningDialog();
//
//		log.info("Dang nhap ko thanh cong nhap pass sai");
//
//	}
//
//	@Test
//	public void TC_04_LoginUnsuccessfulWithInvalidNamePass() {
//		ExtentTestManager.startTest("TC_04_LoginUnsuccessfulWithInvalidNamePass", "TC_04_LoginUnsuccessfulWithInvalidNamePass");
//
//		log.info("LoginUnsuccessfulWithInvalidNamePass - Step 01: Input invalid username and password");
//		homePage.login(driver, USERNAME_INVALID, PASSWORD_INVALID);
//
//		log.info("LoginUnsuccessfulWithInvalidNamePass - Step 02: Verify warning dialog");
//		verifyEquals(homePage.getLoginErrorText(), usernameLoginInvalid);
//		homePage.closeWarningDialog();
//
//		log.info("Dang nhap ko thanh cong nhap ten va pass sai");
//
//	}
//
//	@Test
//	public void TC_05_LoginUnsuccessfulWithoutName() {
//		ExtentTestManager.startTest("TC_05_LoginUnsuccessfulWithoutName", "TC_05_LoginUnsuccessfulWithoutName");
//
//		log.info("LoginUnsuccessfulWithoutName - Step 01: Input data without username");
//		homePage.clearTextElement(driver, AbstractPageUI.usernameLoginLocator);
//		homePage.inputToPasswordTextBox(driver, Constants.PASSWORD);
//		homePage.clickToLoginButton(driver);
//
//		log.info("LoginUnsuccessfulWithoutName - Step 02: Verify warning dialog");
//		verifyEquals(homePage.getLoginErrorText(), usernameLoginError);
//		homePage.closeWarningDialog();
//
//		log.info("Dang nhap ko thanh cong ko nhap ten");
//
//	}
//
//	@Test
//	public void TC_06_LoginUnsuccessfulWithoutPass() {
//		ExtentTestManager.startTest("TC_06_LoginUnsuccessfulWithoutPass", "TC_06_LoginUnsuccessfulWithoutPass");
//
//		log.info("LoginUnsuccessfulWithoutPass - Step 01: Input data without password");
//		homePage.inputToUsernameTextBox(driver, Constants.USERNAME_LOGIN);
//		homePage.clearTextElement(driver, AbstractPageUI.passwordLoginLocator);
//		homePage.clickToLoginButton(driver);
//
//		log.info("LoginUnsuccessfulWithoutPass - Step 02: Verify warning dialog");
//		verifyEquals(homePage.getLoginErrorText(), "Yêu cầu nhập mật khẩu đăng nhập");
//		homePage.closeWarningDialog();
//
//		log.info("Dang nhap ko thanh cong ko nhap pass");
//
//	}
//
//	@AfterClass(alwaysRun=true)
//	public void afterClass() {
//
//		closeBrowserAndDriver(driver);
//
//	}

}
