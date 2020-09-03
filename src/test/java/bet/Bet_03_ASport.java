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
import pageObjects.ASportPageObject;

import java.util.List;

public class Bet_03_ASport extends BaseTest {

	WebDriver driver;
	ASportPageObject aSportPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		aSportPage = PageFactoryManager.getASportPage(driver);

	}

	@Override
	public void Run() {
		ExtentTestManager.startTest("TC_1_ASport", "TC_1_ASport");

		log.info("ASport - Step01: Login with valid account\n");
		aSportPage.loginSportAccount();

		log.info("ASport - Step02: Open ASport page\n");
		aSportPage.openASportPage("item-sb asport", "icon-vi-a");

		log.info("ASport - Step03: Switch to sport iframe to play");
		aSportPage.switchToASportIframe();

		log.info("ASport - Step04: Betting");
		betASport();

		log.info("ASport - Step05: Exit iframe\n");
		aSportPage.quitASportIframe();

		log.info("ASport - Step06: Logout\n");
		aSportPage.logoutToHomePage();

	}

	protected void betASport() {
		boolean i = true;

		while (i) {
			log.info("Now we have " + i + "\n");
			String beforeBalance = aSportPage.getBalance();
			log.info("Before balance is " + beforeBalance + "\n");

			List<WebElement> listBet = aSportPage.getBets();
			log.info("The number of odds is " + listBet.size() + "\n");

			int betSelect = randomNumber(listBet.size());
			log.info("Select the odd in order " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Click to open bet panel\n");
			aSportPage.openBetPanel(listBet.get(betSelect));

			String betOrderDetails = aSportPage.getBetDetails();
			log.info("Bet details is\n" +betOrderDetails + "\n");

			String BET_MONEY = "50";
			log.info("Select money to bet = " + BET_MONEY + "\n");
			aSportPage.selectBetMoney(BET_MONEY);

			log.info("Confirm betting\n");
			aSportPage.confirmBet();

			log.info("Verify warning text\n");
			aSportPage.verifyWarningDisplayed();

			log.info("Close bet panel\n");
			aSportPage.closeWarningDialog();
			try {
				Thread.sleep(5 * 1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Open bet board\n");
			aSportPage.openBetBoard();
			try {
				Thread.sleep(3 * 1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			if(aSportPage.isTicketDisplayed()){
				log.info("Bet ticket in process\n");

				String betTicketDetails = aSportPage.getTicketDetails();
				log.info("Ticket details is\n" + betTicketDetails + "\n");

				log.info("Verify ticket is correct\n");
				verifyEquals(betOrderDetails + "\n" + BET_MONEY, betTicketDetails);

				String afterBalance = aSportPage.getBalance();
				log.info("After balance is " + afterBalance + "\n");

				log.info("Verify balance is updated correct\n");
				aSportPage.verifyBalanceUpdated(beforeBalance, afterBalance);

				i = false;

				log.info("Then we have " + i + "\n");

			} else {

				i = true;
				log.info("Then we have " + i + "\n");

				log.info(Constants.betUnsuccessful);

			}

		}
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
