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

	@Override
	public void Run() {
		ExtentTestManager.startTest("TC_1_TSport", "TC_1_TSport");

		log.info("TSport - Step01: Login with valid account\n");
		tSportPage.loginSportAccount();

		log.info("TSport - Step02: Open TSport page\n");
		tSportPage.openTSportPage("item-sb tsport", "icon-vi-s");

		log.info("TSport - Step03: Switch to sport iframe to play");
		tSportPage.switchToTSportIframe();

		log.info("TSport - Step04: Betting\n");
		betTSport();

		log.info("TSport - Step05: Exit iframe\n");
		tSportPage.quitTSportIframe();

		log.info("TSport - Step06: Logout\n");
		tSportPage.logoutToHomePage();

	}

	protected void betTSport() {
		for (int i = 0; i <= 5; i++) {
			if (i < 5) {
				String beforeBalance = tSportPage.getBalance();
				log.info("Before balance is " + beforeBalance + "\n");

				List<WebElement> listBet = tSportPage.getBets();
				log.info("The number of odd is " + listBet.size() + "\n");

				if (listBet.size() == 0) {
					throw new RuntimeException("There is no available bet");
				} else {
					for (int x = 0; x <= 5; x++) {
						if (x < 5) {
							int betSelect = randomNumber(30);
							log.info("Select odd at order " + betSelect);

							String betStatus = tSportPage.isOddCanBet(listBet.get(betSelect));
							if (betStatus.equals("Odd can be bet")) {
								break;
							} else {
								String oddDetails = tSportPage.getBetDetails();
								sendBot(getClass().getName() + "\n" + oddDetails + "\n" + betStatus);
							}
						} else {
							throw new RuntimeException("Odd is paused");
						}
					}

					log.info("Confirm betting");
					tSportPage.confirmBet("30");

					log.info("Verify betting successful");
					String ticketWarning = tSportPage.ticketDisplayed();
					if (ticketWarning.contains("Bet successfully")) {
						String afterBalance = tSportPage.getBalance();
						log.info("After balance is " + afterBalance + "\n");

						log.info("Verify balance is updated correct\n");
						tSportPage.verifyBalanceUpdated(beforeBalance, afterBalance);
						break;
					} else {
						log.info(ticketWarning + "\n");
					}
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
