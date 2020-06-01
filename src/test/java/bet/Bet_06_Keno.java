package bet;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.KenoPageObject;

import java.lang.reflect.Method;
import java.util.List;

public class Bet_06_Keno extends CommonsTest {

	WebDriver driver;
	KenoPageObject kenoPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		kenoPage = PageFactoryManager.getKenoPage(driver);

	}

	String BET_MONEY = "10";

	@Test
	public void TC_1_Keno(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_Keno");

		log.info("Keno - Step 01: Login valid account\n");
    	kenoPage.loginKenoAccount();
		try {
			Thread.sleep(5*1000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("Keno - Step 02: Switch to Keno page\n");
		kenoPage.openKenoPage();

		log.info("Keno - Step 03: Switch to keno iframe\n");
		kenoPage.switchToKenoIframe();

		log.info("Keno - Step 04: Play Quick Keno 1\n");
		betKeno("kn-1");

		log.info("Keno - Step 05: Play Max Keno 1\n");
		betKeno("kn-20");

		log.info("Keno - Step 06: Play Keno Xuan\n");
		betKeno("kn-22");

		log.info("Keno - Step 07: Exit Keno iframe");
		kenoPage.quitKenoIframe();

		log.info("Keno - Step 08: Logout");
		kenoPage.logoutToHomePage();

	}

	private void betKeno(String gameId) {
		boolean i = true;

		while (i) {
			log.info("First i = " + i + "\n");

			log.info("Wait for bet time\n");
			kenoPage.waitForBetTime(gameId);

			Integer countDownTime = kenoPage.getBetTimeCountDown(gameId);
			log.info("Remain time is " + countDownTime + "s\n");

			if (countDownTime >= 8) {
				String beforeBalance = kenoPage.getBalance();
				log.info("Before balance = " + beforeBalance + "\n");

				List<WebElement> noBet = kenoPage.getBet(gameId);
				log.info("There are " + noBet.size() + " bet points\n");

				int numberBet = randomNumber(noBet.size());

				log.info("Select bet point in order " + numberBet + "\n");
				kenoPage.openBetPanel(noBet.get(numberBet));

				log.info("Select bet money = " + BET_MONEY + "\n");
				kenoPage.selectMoneyToBet(BET_MONEY);

				log.info("Press bet button\n");
				kenoPage.clickBetButton();
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				String afterBalance = kenoPage.getBalance();
				log.info("After balance = " + afterBalance + "\n");

				log.info("Verify balance is updated correctly\n");
				verifyFalse(beforeBalance.equals(afterBalance));

				i = false;
				log.info("Then i = " + i + "\n");

				log.info("====================\n");

			} else {
				i = true;
				log.info("Then i = " + i + "\n");
				try {
					Thread.sleep(3*1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}

		}
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
