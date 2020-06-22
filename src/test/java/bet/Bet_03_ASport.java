package bet;


import commons.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.ASportPageObject;

import java.lang.reflect.Method;
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

	private String BET_MONEY = "50";

	@Override
	public void Run(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_ASport");

		log.info("ASport - Step01: Login with valid account");
		aSportPage.loginSportAccount();
		try {
			Thread.sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("ASport - Step02: Open ASport page");
		aSportPage.openASportPage();

		log.info("ASport - Step03: Switch to sport iframe to play");
		aSportPage.switchToASportIframe();

		log.info("ASport - Step04: Betting");
		betASport();

		log.info("ASport - Step05: Exit iframe");
		aSportPage.quitASportIframe();

		log.info("ASport - Step06: Logout");
		aSportPage.logoutToHomePage();

		log.info("The thao A thanh cong\n====================\n");

	}

	protected void betASport() {
		boolean i = true;

		while (i) {
			log.info("Ban dau dieu kien la " + i + "\n");

			String beforeBalance = aSportPage.getBalance();
			log.info("So tien luc dau la " + beforeBalance + "\n");

			List<WebElement> listBet = aSportPage.getBets();
			log.info("So luong cua bet la " + listBet.size() + "\n");

			int betSelect = randomNumber(listBet.size());
			log.info("Chon bet cua thu " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Click to open bet panel\n");
			aSportPage.openBetPanel(listBet.get(betSelect));

			String betOrderDetails = aSportPage.getBetDetails();
			log.info("Noi dung dat cuoc la\n" +betOrderDetails + "\n");

			log.info("Select money to bet = " + BET_MONEY + "\n");
			aSportPage.selectBetMoney(BET_MONEY);

			log.info("Confirm betting\n");
			aSportPage.confirmBet();

			log.info("Close bet panel\n");
			aSportPage.closeWarningDialog();
			try {
				Thread.sleep(5000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Open bet board\n");
			aSportPage.openBetBoard();
			try {
				Thread.sleep(3000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			if(aSportPage.isTicketDispled()){
				log.info("Bet ticket in process\n");

				String betTicketDetails = aSportPage.getTicketDetails();
				log.info("Noi dung ve cuoc la\n" + betTicketDetails + "\n");

				log.info("Verify ticket is correct\n");
				verifyEquals(betOrderDetails + "\n" + BET_MONEY, betTicketDetails);

				String afterBalance = aSportPage.getBalance();
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
