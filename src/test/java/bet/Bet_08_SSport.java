package bet;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import five88.SSportPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.SSportPageObject;
import java.lang.reflect.Method;
import java.util.List;


public class Bet_08_SSport extends CommonsTest {

	WebDriver driver;
	SSportPageObject sSportPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.STHETHAO_URL);

		sSportPage = PageFactoryManager.getSSportPage(driver);

	}

	private String BET_MONEY = "30";

	@Test
	public void TC_1_SSport(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_SSport");

		log.info("SSport - Step01: Login with valid account\n");
		sSportPage.loginSportAccount();
		try {
			Thread.sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("SSport - Step02: Navigate sport iframe to play\n");
		sSportPage.navigateSSportIframe();

		log.info("SSport - Step03: Betting\n");
		betSSport();

		log.info("The thao S thanh cong\n====================\n");

	}

	protected void betSSport() {
		log.info("Bet Asia version\n");
		betAsiaSport();

		log.info("Bet Euro version\n");
		sSportPage.switchToEUMode();
		betEUSport();


	}

	protected void betAsiaSport() {
		boolean i = true;
		while (i) {
			List<WebElement> listBet = sSportPage.getListBets(SSportPageUI.betAsiaSSportLocator);
			log.info("So luong cua bet la " + listBet.size() + "\n");

			int betSelect = randomNumber(listBet.size());
			log.info("Chon bet cua thu " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			String oddBet = sSportPage.getAsiaOddBet(listBet.get(betSelect));
			log.info("Ty le cuoc da chon la " + oddBet + "\n");

			log.info("Tao phieu cuoc\n");
			sSportPage.createBetOder(listBet.get(betSelect));

			boolean condition = sSportPage.isTicketCreated();
			log.info("Phieu cuoc duoc tao: " + condition + "\n");

			if (condition = true) {
				String oddSelect = sSportPage.getOddSelect();
				log.info("Ty le cuoc trong phieu la " + oddSelect + "\n");

				log.info("Kiem tra ty le cuoc da chon va ty le cuoc trong phieu bang nhau");
				verifyEquals(oddBet, oddSelect);

				log.info("Nhap so tien cuoc = " + BET_MONEY + "\n");
				sSportPage.inputBetMoney(BET_MONEY);

				String totalReturn = sSportPage.getReturn();
				log.info("Tien thang cuoc = " + totalReturn + "\n");

				log.info("Kiem tra tien thang cuoc chinh xac\n");
				sSportPage.verifyReturn(totalReturn, oddSelect, BET_MONEY);

				log.info("Xac nhan cuoc\n");
				sSportPage.confirmBet();
				sSportPage.acceptConfirmAlert();
				try {
					Thread.sleep(5000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				log.info("Kiem tra cuoc thanh cong\n");
				sSportPage.verifyBetSuccess();
				i = false;
			} else {
				log.info("Phieu cuoc chua duoc tao\n");
				i = true;
			}

		}



	}

	protected void betEUSport() {
		boolean i = true;
		while (i) {
			List<WebElement> listBet = sSportPage.getListBets(SSportPageUI.betEuroSSportLocator);
			log.info("So luong cua bet la " + listBet.size() + "\n");

			List<WebElement> listOdd = sSportPage.getListBets(SSportPageUI.betOddEuroSSportLocator);

			List<WebElement> listContentBet = sSportPage.getListBets(SSportPageUI.betContentEuroSSportLocator);

			int betSelect = randomNumber((listBet.size() - 1));
			log.info("Chon bet cua thu " + betSelect + "\n");
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
				e.printStackTrace();
			}

			String oddBet = sSportPage.getEuroOddBet(listOdd.get(betSelect));
			log.info("Ty le cuoc da chon la: " + oddBet + "\n");

			String contentBet = sSportPage.getContentBet(listContentBet.get(betSelect));
			log.info("Noi dung cuoc da chon la: " + contentBet + "\n");

			log.info("Tao phieu cuoc\n");
			sSportPage.createBetOder(listBet.get(betSelect));

			boolean condition = sSportPage.isTicketCreated();
			log.info("Phieu cuoc duoc tao: " + condition + "\n");

			if (condition = true) {
				String oddSelect = sSportPage.getOddSelect();
				log.info("Ty le cuoc trong phieu la: " + oddSelect + "\n");

				String contentSelect = sSportPage.getContentSelect();
				log.info("Noi dung trong phieu cuoc la: " + contentSelect + "\n");

				log.info("Kiem tra ty le cuoc da chon va ty le cuoc trong phieu bang nhau");
				verifyEquals(oddBet, oddSelect);

				log.info("Kiem tra noi dung cuoc da chon va noi dung trong phieu cuoc giong nhau");
				verifyEquals(contentBet, contentSelect);

				log.info("Nhap so tien cuoc = " + BET_MONEY + "\n");
				sSportPage.inputBetMoney(BET_MONEY);

				String totalReturn = sSportPage.getReturn();
				log.info("Tien thang cuoc = " + totalReturn + "\n");

				log.info("Kiem tra tien thang cuoc chinh xac\n");
				sSportPage.verifyReturn(totalReturn, oddSelect, BET_MONEY);

				log.info("Xac nhan cuoc\n");
				sSportPage.confirmBet();
				try {
					Thread.sleep(5000);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				log.info("Kiem tra cuoc thanh cong\n");
				sSportPage.verifyBetSuccess();
				i = false;
			} else {
				log.info("Phieu cuoc chua duoc tao\n");
				i = true;
			}

		}

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
