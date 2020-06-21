package bet;

import commons.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.KenoPageObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Bet_06_Keno extends BaseTest {

	WebDriver driver;
	KenoPageObject kenoPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		kenoPage = PageFactoryManager.getKenoPage(driver);

	}

	String BET_MONEY = "10";

	@Override
	public void Run(Method method) {
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
		betKenoGame();

		log.info("Keno - Step 05: Exit Keno iframe");
		kenoPage.quitKenoIframe();

		log.info("Keno - Step 06: Logout");
		kenoPage.logoutToHomePage();

	}

	private void betKenoGame() {
		List<String> list = new ArrayList<>();
		list.add("kn-1");
		list.add("kn-20");
		list.add("kn-22");
		log.info("First game list = " + list + "\n");

		while (list.size() > 0) {
			for (int count = 1; count <= 20; count++) {
				log.info("count = " + count + "\n========================================\n");
				for (int i = 0; i < list.size(); i++) {
					Assert.assertTrue(count < 20);
					String x = list.get(i);
					if (!kenoPage.isBetTimePresent(x)) {
						log.info(x + " can not bet now\n====================\n");
					} else {
						betAKenoGame(list, x);
					}
				}
			}
		}

	}

	private void betAKenoGame(List<String> gameList, String gameId) {
		log.info(gameId + " can bet now\n");
		String beforeBalance = kenoPage.getBalance();
		log.info("Before balance = " + beforeBalance + "\n");

		List<WebElement> noBet = kenoPage.getBet(gameId);
		log.info("There are " + noBet.size() + " bet points\n");

		int numberBet = randomNumber(noBet.size() - 1);

		log.info("Select bet point in order " + numberBet  + "\n");
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

		log.info("Remove " + gameId + " from game list\n");
		gameList.remove(gameId);
		log.info("Then game list = " + gameList + "\n");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
