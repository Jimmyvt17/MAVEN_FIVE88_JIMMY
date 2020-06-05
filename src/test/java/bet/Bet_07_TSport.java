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
import pageObjects.TSportPageObject;
import java.lang.reflect.Method;
import java.util.List;


public class Bet_07_TSport extends CommonsTest {

	WebDriver driver;
	TSportPageObject tSportPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.TTHETHAO_URL);

		tSportPage = PageFactoryManager.getTSportPage(driver);

	}

	private String BET_MONEY = "100";

	@Test
	public void TC_1_TSport(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_TSport");

		log.info("TSport - Step01: Login with valid account");
		tSportPage.loginSportAccount();
		try {
			Thread.sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("TSport - Step02: Switch to sport iframe to play");
		tSportPage.switchToTSportIframe();

		log.info("TSport - Step03: Betting");
		betASport();

		log.info("TSport - Step04: Exit iframe");
		tSportPage.quitTSportIframe();

		log.info("TSport - Step05: Logout");
		tSportPage.logoutToHomePage();

		log.info("The thao T thanh cong\n====================\n");

	}

	protected void betASport() {
		boolean i = true;

		while (i) {
			log.info("Ban dau dieu kien la " + i + "\n");

			String beforeBalance = tSportPage.getBalance();
			log.info("So tien luc dau la " + beforeBalance + "\n");

			List<WebElement> listBet = tSportPage.getBets();
			log.info("So luong cua bet la " + listBet.size() + "\n");

			int betSelect = randomNumber(listBet.size());
			log.info("Chon bet cua thu " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Click to open bet panel\n");
			tSportPage.openBetPanel(listBet.get(betSelect));

			String betOrderDetails = tSportPage.getBetDetails();
			log.info("Noi dung dat cuoc la\n" +betOrderDetails + "\n");

			log.info("Select money to bet = " + BET_MONEY + "\n");
			tSportPage.selectBetMoney(BET_MONEY);

			log.info("Confirm betting\n");
			tSportPage.confirmBet();
			try {
				Thread.sleep(5000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Open bet board\n");
			tSportPage.openBetBoard();
			try {
				Thread.sleep(3000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			if(tSportPage.isTicketDisplayed()){
				log.info("Bet ticket in process\n");

				String betTicketDetails = tSportPage.getTicketDetails();
				log.info("Noi dung ve cuoc la\n" + betTicketDetails + "\n");

				log.info("Verify ticket is correct\n");
				verifyEquals(betOrderDetails, betTicketDetails);

				String afterBalance = tSportPage.getBalance();
				log.info("So tien sau khi bet la " + afterBalance + "\n");

				log.info("Verify balance is updated correct\n");
				verifyFalse(beforeBalance==afterBalance);

				i = false;

				log.info("Sau do dieu kien la " + i + "\n");

			} else {

				i = true;
				log.info("Sau do dieu kien la " + i + "\n");

				log.info("Bet ko thanh cong, thu lai\n==========\n");

			}

		}
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}