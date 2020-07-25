package bet;

import commons.utility.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import five88.SSportPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.SSportPageObject;

import java.util.List;

public class Bet_08_SSport extends BaseTest {

	WebDriver driver;
	SSportPageObject sSportPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		sSportPage = PageFactoryManager.getSSportPage(driver);

	}

	private final String BET_MONEY = "30";

	@Override
	public void Run() {
		ExtentTestManager.startTest("TC_1_SSport", "TC_1_SSport");
		log.info("SSport - Step01: Login with valid account\n");
		sSportPage.loginSportAccount();

		log.info("TSport - Step02: Open SSport page\n");
		sSportPage.openSSportPage("item-sb ssport", "icon-vi-s");

		log.info("SSport - Step03: Navigate sport iframe to play\n");
		sSportPage.navigateToSSportIframe();

		log.info("SSport - Step04: Betting\n");
		betSSport();

	}

	protected void betSSport() {
		String viewMode = sSportPage.getViewMode();

		if (viewMode.equalsIgnoreCase("Version Châu Âu")) {
			log.info("Bet Asia version\n");
			betAsiaSport();
		} else {
			log.info("Bet Euro version\n");
			betEUSport();
		}

	}

	protected void betAsiaSport() {
		boolean i = true;
		while (i) {
			List<WebElement> listBet = sSportPage.getListBets(SSportPageUI.betAsiaSSportLocator);
			log.info("The number of odd is " + listBet.size() + "\n");

			int betSelect = randomNumber(listBet.size());
			log.info("Select odd at order " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			String oddBet = sSportPage.getAsiaOddBet(listBet.get(betSelect));
			log.info("Selected odd is " + oddBet + "\n");

			log.info("Create bet order\n");
			sSportPage.createBetOder(listBet.get(betSelect));

			log.info("Verify bet oder is created\n");
			if (sSportPage.isTicketCreated()) {
				String oddSelect = sSportPage.getOddSelect();
				log.info("The odd is " + oddSelect + "\n");

				log.info("Verify odd is correct\n");
				verifyEquals(oddBet, oddSelect);

//				String totalReturn = sSportPage.getReturn();
//				log.info("Tien thang cuoc = " + totalReturn + "\n");
//
//				log.info("Kiem tra tien thang cuoc chinh xac\n");
//				sSportPage.verifyReturn(totalReturn, oddSelect, BET_MONEY);

				log.info("Confirm betting\n");
				i = !sSportPage.isAsiaBetSuccess(BET_MONEY);
			} else {
				log.info("Order is not created successfully\n");
				i = true;
			}

		}

	}

	protected void betEUSport() {
		boolean i = true;
		while (i) {
			List<WebElement> listBet = sSportPage.getListBets(SSportPageUI.betEuroSSportLocator);
			log.info("The number of odds is " + listBet.size() + "\n");

			List<WebElement> listOdd = sSportPage.getListBets(SSportPageUI.betOddEuroSSportLocator);

			List<WebElement> listContentBet = sSportPage.getListBets(SSportPageUI.betContentEuroSSportLocator);

			int betSelect = randomNumber((listBet.size() - 1));
			log.info("Select odd at order " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			String oddBet = sSportPage.getEuroOddBet(listOdd.get(betSelect));
			log.info("Selected odd is " + oddBet + "\n");

			String contentBet = sSportPage.getContentBet(listContentBet.get(betSelect));
			log.info("Odd details is " + contentBet + "\n");

			log.info("Create bet order\n");
			sSportPage.createBetOder(listBet.get(betSelect));

			log.info("Verify bet order is created\n");
			if (sSportPage.isTicketCreated()) {
				String oddSelect = sSportPage.getOddSelect();
				log.info("The odd is " + oddSelect + "\n");

				String contentSelect = sSportPage.getContentSelect();
				log.info("Order details is " + contentSelect + "\n");

				log.info("Verify odd is correct\n");
				verifyEquals(oddBet, oddSelect);

				log.info("Verify odd details are correct\n");
				verifyEquals(contentBet, contentSelect);

//				String totalReturn = sSportPage.getReturn();
//				log.info("Tien thang cuoc = " + totalReturn + "\n");
//
//				log.info("Kiem tra tien thang cuoc chinh xac\n");
//				sSportPage.verifyReturn(totalReturn, oddSelect, BET_MONEY);

				log.info("Confirm betting\n");
				i = !sSportPage.isEUBetSuccess(BET_MONEY);
			} else {
				log.info("Bet order is not created successfully\n");
				i = true;
			}

		}

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}


}
