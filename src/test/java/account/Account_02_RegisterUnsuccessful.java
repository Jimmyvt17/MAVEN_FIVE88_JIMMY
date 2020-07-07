package account;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import five88.RegisterPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.RegisterPageObject;

import java.util.Date;


public class Account_02_RegisterUnsuccessful extends CommonsTest {

	WebDriver driver;
	RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.REGISTER_URL);

		registerPage = PageFactoryManager.getRegisterPage(driver);

	}

	String USERNAME_VALID = "seta" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	private final String usRegisErInput = "Tên đăng nhập chưa nhập.";
	private final String usRegisErInvalid = "Tên đăng nhập không hợp lệ. Chỉ được chứa chữ cái, số hoặc dấu gạch ngang";
	private final String pwRegisErInput = "Bạn chưa khai báo mật khẩu.";
	private final String phRegisErInput = "Số điện thoại bạn chưa nhập";

	@Test
	public void TC_01_RegisterUnsuccessfulWithoutInformation() {
		ExtentTestManager.startTest("TC_01_RegisterUnsuccessfulWithoutInformation", "TC_01_RegisterUnsuccessfulWithoutInformation");

		log.info("RegisterUnsuccessfulWithoutInformation - Step 01: Click submit without inputting data");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutInformation - Step 02: Verify error of username, password, phone");
		verifyEquals(registerPage.getUsernameError(), usRegisErInput);
		verifyEquals(registerPage.getPasswordError(), pwRegisErInput);
		verifyEquals(registerPage.getPhoneError(), phRegisErInput);

		log.info("Register Unsuccessful Without Information");

	}

	@Test
	public void TC_02_RegisterUnsuccessfulWithoutName() {
		ExtentTestManager.startTest("TC_02_RegisterUnsuccessfulWithoutName", "TC_02_RegisterUnsuccessfulWithoutName");

		log.info("RegisterUnsuccessfulWithoutName - Step 01: Input data without username");
		registerPage.inputToPasswordTextBox(Constants.PASSWORD);
		registerPage.inputToPhoneTextBox(Constants.PHONE);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutName - Step 02: Verify error of username");
		verifyEquals(registerPage.getUsernameError(), usRegisErInput);

		log.info("Dang ky ko thanh cong ko nhap name");

	}

	@Test
	public void TC_03_RegisterUnsuccessfulWithoutPass() {
		ExtentTestManager.startTest("TC_03_RegisterUnsuccessfulWithoutPass", "TC_03_RegisterUnsuccessfulWithoutPass");

		log.info("RegisterUnsuccessfulWithoutPass - Step 01: Input data without password");
		registerPage.inputToUsernameTextBox(USERNAME_VALID);
		registerPage.clearTextElement(driver, RegisterPageUI.passwordRegisLocator);
		registerPage.inputToPhoneTextBox(Constants.PHONE);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutPass - Step 02: Verify error of password");
		verifyEquals(registerPage.getPasswordError(), pwRegisErInput);

		log.info("Dang ky ko thanh cong ko nhap pass");

	}

	@Test
	public void TC_04_RegisterUnsuccessfulWithoutPhone() {
		ExtentTestManager.startTest("TC_04_RegisterUnsuccessfulWithoutPhone", "TC_04_RegisterUnsuccessfulWithoutPhone");

		log.info("RegisterUnsuccessfulWithoutPhone - Step 01: Input data without phone number");
		registerPage.inputToUsernameTextBox(USERNAME_VALID);
		registerPage.inputToPasswordTextBox(Constants.PASSWORD);
		registerPage.clearTextElement(driver, RegisterPageUI.phoneRegisLocator);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutPhone - Step 02: Verify error of phone number");
		verifyEquals(registerPage.getPhoneError(), phRegisErInput);

		log.info("Dang ky ko thanh cong ko nhap so phone");

	}

	@Test
	public void TC_05_RegisterUnsuccessfulWithoutNamePass() {
		ExtentTestManager.startTest("TC_05_RegisterUnsuccessfulWithoutNamePass", "TC_05_RegisterUnsuccessfulWithoutNamePass");

		log.info("RegisterUnsuccessfulWithoutNamePass - Step 01: Input data without username and password");
		registerPage.clearTextElement(driver, RegisterPageUI.usernameResgisLocator);
		registerPage.clearTextElement(driver, RegisterPageUI.passwordRegisLocator);
		registerPage.inputToPhoneTextBox(Constants.PHONE);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutNamePass - Step 02: Verify error of username, password");
		verifyEquals(registerPage.getUsernameError(), usRegisErInput);
		verifyEquals(registerPage.getPasswordError(), pwRegisErInput);

		log.info("Dang ky ko thanh cong ko nhap name pass");

	}

	@Test
	public void TC_06_RegisterUnsuccessfulWithoutNamePhone() {
		ExtentTestManager.startTest("TC_06_RegisterUnsuccessfulWithoutNamePhone", "TC_06_RegisterUnsuccessfulWithoutNamePhone");

		log.info("RegisterUnsuccessfulWithoutNamePhone - Step 01: Input data without username and phone number");
		registerPage.clearTextElement(driver, RegisterPageUI.usernameResgisLocator);
		registerPage.inputToPasswordTextBox(Constants.PASSWORD);
		registerPage.clearTextElement(driver, RegisterPageUI.phoneRegisLocator);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutNamePhone - Step 02: Verify error of username, phone number");
		verifyEquals(registerPage.getUsernameError(), usRegisErInput);
		verifyEquals(registerPage.getPhoneError(), phRegisErInput);

		log.info("Dang ky ko thanh cong ko nhap name phone");

	}

	@Test
	public void TC_07_RegisterUnsuccessfulWithoutPassPhone() {
		ExtentTestManager.startTest("TC_07_RegisterUnsuccessfulWithoutPassPhone", "TC_07_RegisterUnsuccessfulWithoutPassPhone");

		log.info("RegisterUnsuccessfulWithoutPassPhone - Step 01: Input data without password and phone number");
		registerPage.inputToUsernameTextBox(USERNAME_VALID);
		registerPage.clearTextElement(driver, RegisterPageUI.passwordRegisLocator);
		registerPage.clearTextElement(driver, RegisterPageUI.phoneRegisLocator);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithoutPassPhone - Step 02: Verify error of password, phone number");
		verifyEquals(registerPage.getPasswordError(), pwRegisErInput);
		verifyEquals(registerPage.getPhoneError(), phRegisErInput);

		log.info("Dang ky ko thanh cong ko nhap pass phone");

	}

	@Test
	public void TC_08_RegisterUnsuccessfulWithSpecialName() {
		ExtentTestManager.startTest("TC_08_RegisterUnsuccessfulWithSpecialName", "TC_08_RegisterUnsuccessfulWithSpecialName");

		log.info("RegisterUnsuccessfulWithSpecialName - Step 01: Input username with special character");
		registerPage.inputToUsernameTextBox("setainvalid@");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithSpecialName - Step 02: Verify error of username");
		verifyEquals(registerPage.getUsernameError(), usRegisErInvalid);

		log.info("Dang ky ko thanh cong nhap ten chua ky tu dac biet");

	}

	@Test
	public void TC_09_RegisterUnsuccessfulWithVietnameseName() {
		ExtentTestManager.startTest("TC_09_RegisterUnsuccessfulWithVietnameseName", "TC_09_RegisterUnsuccessfulWithVietnameseName");

		log.info("RegisterUnsuccessfulWithVietnameseName - Step 01: Ipnut username in Vietnamese");
		registerPage.inputToUsernameTextBox("sétajim");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithVietnameseName - Step 02: Verify error of username");
		verifyEquals(registerPage.getUsernameError(), usRegisErInvalid);

		log.info("Dang ky ko thanh cong nhap ten tieng Viet");

	}

	@Test
	public void TC_10_RegisterUnsuccessfulWithNameUnder6Letters() {
		ExtentTestManager.startTest("TC_10_RegisterUnsuccessfulWithNameUnder6Letters", "TC_10_RegisterUnsuccessfulWithNameUnder6Letters");

		log.info("RegisterUnsuccessfulWithNameUnder6Letters - Step 01: Input username under 6 letters");
		registerPage.inputToUsernameTextBox("seta");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithNameUnder6Letters - Step 02: Verify error of username");
		verifyEquals(registerPage.getUsernameError(), "Tên đăng nhập không hợp lệ, phải có ít nhất 6 ký tự và không quá 30 ký tự.");

		log.info("Dang ky ko thanh cong ten duoi 6 ky tu");

	}

	@Test
	public void TC_11_RegisterUnsuccessfulWithNameOver30Letters() {
		ExtentTestManager.startTest("TC_11_RegisterUnsuccessfulWithNameOver30Letters", "TC_11_RegisterUnsuccessfulWithNameOver30Letters");

		log.info("RegisterUnsuccessfulWithNameOver30Letters - Step 01: Input username over 30 letters");
		registerPage.inputToUsernameTextBox("setajim123456789012345678901234");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithNameOver30Letters - Step 02: Verify error of username");
		verifyEquals(registerPage.getUsernameError(), "Tên đăng nhập không được nhiều hơn 30 ký tự");

		log.info("Dang ky ko thanh cong ten hon 30 ky tu");

	}

	@Test
	public void TC_12_RegisterUnsuccessfulWithNameAlready() {
		ExtentTestManager.startTest("TC_12_RegisterUnsuccessfulWithNameAlready", "TC_12_RegisterUnsuccessfulWithNameAlready");

		log.info("RegisterUnsuccessfulWithNameAlready - Step 01: Input username already exists");
		registerPage.inputToUsernameTextBox(Constants.USERNAME_LOGIN);
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithNameAlready - Step 02: Verify error of username");
		verifyEquals(registerPage.getUsernameError(), "Tên đăng nhập đã được sử dụng");

		log.info("Dang ky ko thanh cong ten da ton tai");

	}

	@Test
	public void TC_12_RegisterUnsuccessfulWithPassUnder6Letters() {
		ExtentTestManager.startTest("TC_12_RegisterUnsuccessfulWithPassUnder6Letters", "TC_12_RegisterUnsuccessfulWithPassUnder6Letters");

		log.info("RegisterUnsuccessfulWithPassUnder6Letters - Step 01: Input password under 6 letters");
		registerPage.inputToPasswordTextBox("jimmy");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithPassUnder6Letters - Step 02: Verify error of password");
		verifyEquals(registerPage.getPasswordError(), "Mật khẩu quá ngắn, phải ít nhất 6 ký tự.");

		log.info("Dang ky ko thanh cong pass duoi 6 ky tu");

	}

	@Test
	public void TC_14_RegisterUnsuccessfulWithPhoneUnder10Letters() {
		ExtentTestManager.startTest("TC_14_RegisterUnsuccessfulWithPhoneUnder10Letters", "TC_14_RegisterUnsuccessfulWithPhoneUnder10Letters");

		log.info("RegisterUnsuccessfulWithPhoneUnder10Letters - Step 01: Input phone number under 10 letters");
		registerPage.inputToPhoneTextBox("123456789");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithPhoneUnder10Letters - Step 02: Verify error of phone number");
		verifyEquals(registerPage.getPhoneError(), "Số điện thoại không được ít hơn 10 ký tự");

		log.info("Dang ky ko thanh cong so phone duoi 10 ky tu");

	}

	@Test
	public void TC_15_RegisterUnsuccessfulWithPhoneOver14Letters() {
		ExtentTestManager.startTest("TC_15_RegisterUnsuccessfulWithPhoneOver14Letters", "TC_15_RegisterUnsuccessfulWithPhoneOver14Letters");

		log.info("RegisterUnsuccessfulWithPhoneOver14Letters - Step 01: Input phone number over 14 letters");
		registerPage.inputToPhoneTextBox("123456789012345");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithPhoneOver14Letters - Step 02: Verify error of phone number");
		verifyEquals(registerPage.getPhoneError(), "Số điện thoại không được nhiều hơn 14 ký tự");

		log.info("Dang ky ko thanh cong so phone hon 14 ky tu");

	}

	@Test
	public void TC_16_RegisterUnsuccessfulWithInvalidPhonea() {
		ExtentTestManager.startTest("TC_16_RegisterUnsuccessfulWithInvalidPhone", "TC_16_RegisterUnsuccessfulWithInvalidPhone");

		log.info("RegisterUnsuccessfulWithInvalidPhone - Step 01: Input invalid phone number");
		registerPage.inputToPhoneTextBox("a");
		registerPage.clickToSubmitButton();

		log.info("RegisterUnsuccessfulWithInvalidPhone - Step 02: Verify error of phone number");
		verifyEquals(registerPage.getPhoneError(), "Số điện thoại không hợp lệ");

		log.info("Dang ky ko thanh cong so phone chua ky tu la");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
