package bet;

import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import commons.utility.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.TSportPageObject;

import java.util.List;

public class Bet_11_StgTSport extends BaseTest {
	WebDriver driver;
	TSportPageObject tSportPage;

	@Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);
		tSportPage = PageFactoryManager.getTSportPage(driver);

	}

	@Override
	public void Run() {
		ExtentTestManager.startTest("STG_TSPORT", "STG_TSPORT");

        log.info("STG_TSPORT - Step01: Login with valid account\n");
        tSportPage.loginSportStgAccount();

        log.info("STG_TSPORT - Step02: Open TSport page\n");
        tSportPage.openTSportPage("item-sb tsport", "icon-vi-s");

        log.info("STG_TSPORT - Step03: Open stg tsport page\n");
        tSportPage.openStgUrl();

		log.info("STG_TSPORT - Step04: Betting\n");
		betTSport();

	}

	protected void betTSport() {
		for (int i = 0; i <= 5; i++) {
			if (i < 5) {
				String beforeBalance = tSportPage.getBalance();
				log.info("Before balance is " + beforeBalance + "\n");

				List<WebElement> listBet = tSportPage.getLiveBets();
				log.info("The number of odd is " + listBet.size() + "\n");

				int betSelect = randomNumber(listBet.size());
				log.info("Select odd at order " + betSelect + "\n");
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				log.info("Confirm betting\n");
				tSportPage.confirmBet(listBet.get(betSelect), "30");

				log.info("Verify betting successful");
				String ticketWarning = tSportPage.ticketDisplayed();
				if (ticketWarning.contains("Bet successfully")) {
					String afterBalance = tSportPage.getBalance();
					log.info("After balance is " + afterBalance + "\n");

					log.info("Verify balance is updated correct\n");
					tSportPage.verifyBalanceUpdated(beforeBalance, afterBalance);
					break;
				} else {
					log.info(ticketWarning);
					sendBot(getClass().getName() + ticketWarning);
				}
			} else {
				throw new RuntimeException(Constants.betUnsuccessful);
			}
		}

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {

		closeBrowserAndDriver(driver);

	}

}

