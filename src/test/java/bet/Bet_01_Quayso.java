package bet;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.QuaysoPageObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Bet_01_Quayso extends CommonsTest {

	WebDriver driver;
	QuaysoPageObject quaysoPage;

	@Parameters({"network", "pass", "browser"})
	@BeforeClass
	public void preConditions(String networkName, String networkPass, String browserName) {

		switchToSpecificNetwork(networkName, networkPass);

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		quaysoPage = PageFactoryManager.getQuaysoPage(driver);

	}

	private Integer BET_MONEY = 10;

	@Parameters(value = "file")
	@Test
	public void Run(Method method, String fileName) {
		ExtentTestManager.startTest(method.getName(), "TC_1_Quayso");

		log.info("Quayso - Step 01: Login with valid account");
		quaysoPage.loginQuaysoAccount();

		log.info("Quayso - Step 02: Switch to Quayso page");
		quaysoPage.openQuaysoPage();

		log.info("Quayso - Step 03: Switch to iframe to play");
		quaysoPage.switchToLotteryIframe(fileName);

		log.info("Quayso - Step 04: Play lottery");

		log.info("Quayso - Step 05: Exit iframe");
		quaysoPage.quitLotteryIframe();

		log.info("Quayso - Step 06: Logout");
		quaysoPage.logoutToHomePage();

		log.info("Quay so thanh cong\n================================================================================\n");

	}

	private void betLotteryGame() {
		List<String> listA = new ArrayList<>();
		listA.add("97");
		listA.add("98");
		listA.add("99");
		listA.add("101");
		listA.add("102");
		listA.add("106");
		log.info("First game list = " + listA + "\n");

		while (listA.size() > 0) {
			for (int count = 1; count <= 20; count++) {
				log.info("count = " + count + "\n========================================\n");
				for (int i = 0; i < listA.size(); i++) {
					Assert.assertTrue(count < 20);
					String x = listA.get(i);
					if (!quaysoPage.isBetStartPresent(x)) {
						log.info(x + " can not bet now\n====================\n");
					} else {
						betALotteryGame(listA, x);
					}
				}
			}
		}

	}

	private void betALotteryGame(List<String> gameList, String gameId) {
		log.info(gameId + " can bet now\n");
		String beforeBet = quaysoPage.getBalance();
		log.info("Before balance = " + beforeBet + "\n");

		List<WebElement> noBet = quaysoPage.getBets(gameId);
		log.info("There are " + noBet.size() + " bet points " + "\n");

		int  number = randomNumber(noBet.size() - 1);

		log.info("Select bet point at order " + number + 1 + "\n");
		quaysoPage.openBetPanel(noBet.get(number));

		log.info("Select money to bet = " + BET_MONEY + "\n");
		quaysoPage.selectMoneyToBet(BET_MONEY);

		log.info("Confirm betting\n");
		quaysoPage.clickBetButton();
		try {
			Thread.sleep(1000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		String afterBet = quaysoPage.getBalance();
		log.info("After balance = " + afterBet + "\n");

		log.info("Verify that balance is updated correctly\n");
		verifyFalse(beforeBet.equals(afterBet));

		log.info("Remove " + gameId + " from game list\n");
		gameList.remove(gameId);
		log.info("Then game list = " + gameList + "\n====================\n");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {

		closeBrowserAndDriver(driver);

	}

}
