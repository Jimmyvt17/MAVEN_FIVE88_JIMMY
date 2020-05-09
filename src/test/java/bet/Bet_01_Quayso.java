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
import pageObjects.QuaysoPageObject;

import java.lang.reflect.Method;
import java.util.List;

public class Bet_01_Quayso extends CommonsTest {

	WebDriver driver;
	QuaysoPageObject quaysoPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {
		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		quaysoPage = PageFactoryManager.getQuaysoPage(driver);

	}

	private Integer BET_MONEY = 10;

	@Test//(invocationCount = 10)
	public void TC_1_Quayso(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_Quayso");

		log.info("Quayso - Step 01: Login with valid account");
    	quaysoPage.loginQuaysoAccount();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		quaysoPage.openQuaysoPage();

		log.info("Quayso - Step 02: Switch to iframe to play");
    	quaysoPage.switchToLotteryIframe();

		log.info("Quayso - Step 03: Play roulette");
		betGameQuayso("99", " game-roulette");

		log.info("Quayso - Step 04: Play racing");
		betGameQuayso("106", "");

		log.info("Quayso - Step 05: Play taixiu");
		betGameQuayso("101", "");

		log.info("Quayso - Step 06: Play xocdia");
		betGameQuayso("102", "");

		log.info("Quayso - Step 07: Play dragon-tiger");
		betGameQuayso("97", "");

		log.info("Quayso - Step 08: Exit iframe");
		quaysoPage.quitLotteryIframe();

		log.info("Quayso - Step 09: Logout");
		quaysoPage.logoutToHomePage();

		log.info("Quay so thanh cong\n================================================================================\n");

	}

	protected void betGameQuayso(String number, String game) {
		boolean conditionRoul = true;

		while (conditionRoul) {
			log.info("Ban dau dieu kien la " + conditionRoul + "\n");

			log.info("Cho den thoi gian bet\n");
			quaysoPage.scrollToQuaysoGame(number, game);
			quaysoPage.waitForBetStartPresent(number, game);

			Integer countDownTime = quaysoPage.getBetTimeCountDown(number, game);
			log.info("Thoi gian bet con lai la " + countDownTime + "s\n");

			if (countDownTime >= 10) {
				String beforeBet = quaysoPage.getBalance();
				log.info("So tien luc dau la " + beforeBet + "\n");

				List<WebElement> noBet = quaysoPage.getBets(number, game);
				log.info("Co " + noBet.size() + " cua " + "\n");

				int numberRoulette = randomNumber(noBet.size());

				log.info("Chon cua bet thu " + numberRoulette + "\n");
				quaysoPage.openBetPanel(noBet.get(numberRoulette));

				log.info("Chon bet so tien = " + BET_MONEY + "\n");
				quaysoPage.selectMoneyToBet(BET_MONEY);

				log.info("Bam nut Cuoc\n");
				quaysoPage.clickBetButton();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String afterBet = quaysoPage.getBalance();
				log.info("So tien sau khi quay roulette la " + afterBet + "\n");

				log.info("Kiem tra tai khoan bi tru tien sau khi bet\n");
				verifyFalse(beforeBet==afterBet);

				conditionRoul = false;
				log.info("Sau do dieu kien la " + conditionRoul + "\n");

				log.info("====================\n");

			} else {

				conditionRoul = true;
				log.info("Sau do dieu kien la " + conditionRoul + "\n");
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() throws Exception {
		
		closeBrowserAndDriver(driver);
		
	}

}
