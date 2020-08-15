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
import pageObjects.NumberPageObject;

import java.util.List;

public class Bet_05_NumberGame extends BaseTest {

	WebDriver driver;
	NumberPageObject numberPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		numberPage = PageFactoryManager.getNumberPage(driver);

	}

	String BET_MONEY = "10";

	@Override
	public void Run() {
		ExtentTestManager.startTest("TC_1_NumberGame", "TC_1_NumberGame");

		log.info("NumberGame - Step 01: Login valid account\n");
    	numberPage.loginNumberAccount();

		log.info("NumberGame - Step 02: Switch to Number page\n");
		numberPage.openNumberGamePage();

		log.info("NumberGame - Step 03: Switch to number iframe\n");
		numberPage.switchToNumberIframe();

		log.info("NumberGame - Step 04: Play Turbo Number Game\n");
		betNumberGame("nb-100123");

		log.info("NumberGame - Step 05: Play Number Game\n");
		betNumberGame("nb-100127");

		log.info("NumberGame - Step 06: Exit Number iframe");
		numberPage.quitNumberIframe();

		log.info("NumberGame - Step 07: Logout");
		numberPage.logoutToHomePage();

	}

	private void betNumberGame(String value) {
		boolean i = true;

		while (i) {
			log.info("First i = " + i + "\n");

			log.info("Wait for bet time");
			numberPage.waitForBetTime(value);

			Integer countDownTime = numberPage.getBetTimeCountDown(value);
			log.info("Remain time is " + countDownTime + "s\n");

			if (countDownTime >= 10) {
				String beforeBalance = numberPage.getBalance();
				log.info("Before balance = " + beforeBalance + "\n");

				List<WebElement> noBet = numberPage.getBet(value);
				log.info("There are " + noBet.size() + " bet points\n");

				int numberBet = randomNumber(noBet.size());

				log.info("Select bet point in order " + numberBet + "\n");
				numberPage.clickToBetPoint(noBet.get(numberBet));

				String betDetail = numberPage.getBetDetail();
				log.info("Bet detail is\n" + betDetail + "\n");

				log.info("Input money = " + BET_MONEY + "\n");
				numberPage.inputMoneyToBet(BET_MONEY);

				log.info("Press bet button\n");
				numberPage.clickBetButton();
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				log.info("Close warning dialog\n");
				numberPage.closeWarning();

				String ticketDetail = numberPage.getTicketDetail();
				log.info("Ticket detail is\n" + ticketDetail + "\n");

				String afterBalance = numberPage.getBalance();
				log.info("After balance = " + afterBalance + "\n");

				log.info("Verify balance is updated correctly\n");
				verifyBalanceUpdated(beforeBalance, afterBalance);;

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
