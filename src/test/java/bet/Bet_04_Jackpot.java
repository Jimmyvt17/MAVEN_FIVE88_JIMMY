package bet;


import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.JackpotPageObject;


public class Bet_04_Jackpot extends CommonsTest {

	WebDriver driver;
	JackpotPageObject jackpotPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		jackpotPage = PageFactoryManager.getJackpotPage(driver);

	}

	@Test
	public void TC_1_Jackpot() {
		ExtentTestManager.startTest("TC_1_Jackpot", "TC_1_Jackpot");

		log.info("Jackpot - Step 01: Login test slot account\n");
    	jackpotPage.loginTestSlot();

		log.info("Jackpot - Step 02: Switch to Jackpot page\n");
		jackpotPage.openJackpotPage();

		log.info("Jackpot - Step 03: Open Jackpot game\n");
		jackpotPage.openJackpotGame();

//		log.info("Jackpot - Step 04: Delay 12 hours to finish");
//		delayInHour(12, 1);

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
