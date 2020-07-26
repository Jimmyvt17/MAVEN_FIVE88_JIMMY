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
import pageObjects.HomePageObject;

public class Account_03_LoginSuccessful extends CommonsTest {

	WebDriver driver;
	HomePageObject homePage;

	@Parameters("browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		homePage = PageFactoryManager.getHomePage(driver);

	}

	@Test
	public void TC_01_LoginSuccessful(){
		ExtentTestManager.startTest("TC_01_LoginSuccessful", "TC_01_LoginSuccessful");

		log.info("LoginSuccessful- Step 01: Login with valid account");
		homePage.loginAccount(Constants.USERNAME_LOGIN);

		log.info("LoginSuccessful- Step 02: Logout");
		homePage.logout();

		log.info("Dang nhap thanh cong");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
