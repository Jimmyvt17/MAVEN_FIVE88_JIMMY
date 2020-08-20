package account;

import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import commons.utility.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObject;

public class Account_03_LoginSuccessful extends BaseTest {

	WebDriver driver;
	HomePageObject homePage;

	@Parameters("browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);
		homePage = PageFactoryManager.getHomePage(driver);

	}

	@Override
	public void Run(){
		ExtentTestManager.startTest("TC_01_LoginSuccessful", "TC_01_LoginSuccessful");

		log.info("LoginSuccessful- Step 01: Login with valid account");
		homePage.loginAccount(Constants.USERNAME_LOGIN);

		log.info("LoginSuccessful- Step 02: Logout");
		homePage.logout();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
