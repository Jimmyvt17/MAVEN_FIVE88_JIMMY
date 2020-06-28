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
import pageObjects.TSportPageObject;

import java.lang.reflect.Method;
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

	private String BET_MONEY = "30";

	@Override
	public void Run(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_TSport");

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
		verifyTSport();

		log.info("TSport - Step06: Exit iframe\n");
		tSportPage.quitTSportIframe();

		log.info("TSport - Step07: Logout\n");
		tSportPage.logoutToHomePage();

		log.info("The thao T thanh cong\n====================\n");

	}

	protected void betTSport() {
		boolean i = true;

		while (i) {
			log.info("Ban dau dieu kien la " + i + "\n");

			String beforeBalance = tSportPage.getBalance();
			log.info("So tien luc dau la " + beforeBalance + "\n");

			List<WebElement> listBet = tSportPage.getBets();
			log.info("So luong cua bet la " + listBet.size() + "\n");

			int betSelect = randomNumber(100);
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
			try {
				Thread.sleep(3 * 1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			log.info("Confirm betting\n");
			tSportPage.confirmBet(BET_MONEY);

			log.info("Verify cuoc thanh cong\n");
			if(tSportPage.isTicketDisplayed()){
				log.info("Mo bang cuoc dang dien ra\n");
				tSportPage.openBetBoard();

				String ticketDetails = tSportPage.getTicketDetails();
				log.info("Noi dung ve cuoc la\n" + ticketDetails + "\n");

				log.info("Verify ticket is correct\n");
				verifyEquals(betOrderDetails, ticketDetails);

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

	protected void betTSportManyTimes() {
		boolean i = true;

		while (i) {
			log.info("Ban dau dieu kien la " + i + "\n");

			String beforeBalance = tSportPage.getBalance();
			log.info("So tien luc dau la " + beforeBalance + "\n");

			List<WebElement> listBet = tSportPage.getBets();
			log.info("So luong cua bet la " + listBet.size() + "\n");

			int betSelect = randomNumber(100);
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

			log.info("Confirm betting\n");
			tSportPage.confirmBet(BET_MONEY);

			log.info("Verify cuoc thanh cong\n");
			if(tSportPage.isTicketDisplayed()){
				log.info("Mo bang cuoc dang dien ra\n");
				tSportPage.openBetBoard();

				String ticketDetails = tSportPage.getTicketDetails();
				log.info("Noi dung ve cuoc la\n" + ticketDetails + "\n");

				log.info("Verify ticket is correct\n");
				verifyEquals(betOrderDetails, ticketDetails);

				String afterBalance = tSportPage.getBalance();
				log.info("So tien sau khi bet la " + afterBalance + "\n");

				log.info("Verify balance is updated correct\n");
				verifyFalse(beforeBalance==afterBalance);

				i = true;
			} else {
				i = true;
				log.info("Bet ko thanh cong, thu lai\n==========\n");
			}

		}
	}


	protected void verifyTSport() {
		for (int i = 0; i < 10; i++) {
			log.info("i = " + i + "\n");

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

			String betTicketDetails = tSportPage.getBetTicketDetails();
			log.info("Noi dung ve cuoc la\n" + betTicketDetails + "\n");

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
