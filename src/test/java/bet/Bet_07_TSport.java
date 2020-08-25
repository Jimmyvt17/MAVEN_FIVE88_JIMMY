package bet;

import commons.utility.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.TSportPageObject;

import java.util.List;

public class Bet_07_TSport extends BaseTest {

	WebDriver driver;
	TSportPageObject tSportPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);
		tSportPage = PageFactoryManager.getTSportPage(driver);

	}

	private final String BET_MONEY = "30";

	@Override
	public void Run() {
		ExtentTestManager.startTest("TC_1_TSport", "TC_1_TSport");

		log.info("TSport - Step01: Login with valid account\n");
		tSportPage.loginSportAccount();

		log.info("TSport - Step02: Open TSport page");
		tSportPage.openTSportPage("item-sb tsport", "icon-vi-s");

		log.info("TSport - Step03: Switch to sport iframe to play\n");
		tSportPage.switchToTSportIframe();

		log.info("TSport - Step04: Betting\n");
		betTSport();
		//betTSportManyTimes();

		log.info("TSport - Step05: Verify min bet and max bet\n");
		//verifyTSport();

		log.info("TSport - Step06: Exit iframe\n");
		tSportPage.quitTSportIframe();

		log.info("TSport - Step07: Logout\n");
		tSportPage.logoutToHomePage();

	}

	protected void betTSport() {
		for (int i = 0; i <= 5; i++) {
			if (i < 5) {
				String beforeBalance = tSportPage.getBalance();
				log.info("Before balance is " + beforeBalance + "\n");

				List<WebElement> listBet = tSportPage.getBets();
				log.info("The number of odd is " + listBet.size() + "\n");

				int betSelect = randomNumber(listBet.size());
				log.info("Select odd at order " + betSelect + "\n");
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				log.info("Confirm betting\n");
				tSportPage.confirmBet(listBet.get(betSelect), BET_MONEY);

				log.info("Verify betting successful");
				if (tSportPage.isTicketDisplayed()) {
					String afterBalance = tSportPage.getBalance();
					log.info("After balance is " + afterBalance + "\n");

					log.info("Verify balance is updated correct\n");
					tSportPage.verifyBalanceUpdated(beforeBalance, afterBalance);
					break;
				} else {
					log.info(Constants.betUnsuccessful);
				}
			} else {
				throw new RuntimeException(Constants.betUnsuccessful);
			}
		}

	}

	protected void betTSportManyTimes() {
		for (int i = 0; i <= 5; i++) {
			if (i < 5) {
				log.info("Now we have " + i + "\n");

				String beforeBalance = tSportPage.getBalance();
				log.info("Before balance is " + beforeBalance + "\n");

				List<WebElement> listBet = tSportPage.getBets();
				log.info("The number of odd is " + listBet.size() + "\n");

				int betSelect = randomNumber(100);
				log.info("Select odd at order " + betSelect + "\n");
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				log.info("Confirm betting\n");
				tSportPage.confirmBet(listBet.get(betSelect), BET_MONEY);

				log.info("Verify betting successfully\n");
				if (tSportPage.isTicketDisplayed()) {
					String afterBalance = tSportPage.getBalance();
					log.info("After balance is " + afterBalance + "\n");

					log.info("Verify balance is updated correct\n");
					tSportPage.verifyBalanceUpdated(beforeBalance, afterBalance);
				} else {
					log.info(Constants.betUnsuccessful);
				}
			} else {
				throw new RuntimeException(Constants.betUnsuccessful);
			}
		}

	}


	protected void verifyTSport() {
		for (int i = 0; i < 10; i++) {
			log.info("i = " + i + "\n");

			List<WebElement> listBet = tSportPage.getBets();
			log.info("The number of odd is " + listBet.size() + "\n");

			int betSelect = randomNumber(listBet.size());
			log.info("Select odd at order " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Click to open bet panel\n");
			tSportPage.openBetPanel(listBet.get(betSelect));

			String betOrderDetails = tSportPage.getBetDetails();
			log.info("Bet details is\n" +betOrderDetails + "\n");

			String betTicketDetails = tSportPage.getBetTicketDetails();
			log.info("Ticket details is\n" + betTicketDetails + "\n");

			log.info("Verify ticket is correct\n");
			verifyEquals(betOrderDetails, betTicketDetails);
			try {
				Thread.sleep(3 * 1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Verify min bet exist\n");
			tSportPage.verifyMinBet();

		}
	}


	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
