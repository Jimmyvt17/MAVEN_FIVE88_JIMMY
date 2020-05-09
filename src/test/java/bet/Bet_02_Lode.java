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
import pageObjects.LodePageObject;

import java.lang.reflect.Method;
import java.util.List;


public class Bet_02_Lode extends CommonsTest {

	WebDriver driver;
	LodePageObject lodePage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.LODE_URL);

		lodePage = PageFactoryManager.getLodePage(driver);

	}

	@Test
	public void TC_1_Lode(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_Lode");

		log.info("Lode - Step 01: Login with valid account");
		lodePage.loginLodeAccount();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		log.info("Lode - Step 02: Switch to iframe to play");
		lodePage.switchToLodeIframe();

		log.info("Lode - Step 03: Play Lode 2 so");
		betLode();

		log.info("Lode - Step 04: Play Lode 3 so");
		lodePage.switchToLo3So();
		betLode();

		log.info("Lode - Step 05: Exit iframe");
		lodePage.quitLodeIframe();

		log.info("Lode - Step 06: Logout");
		lodePage.logoutToHomePage();

		log.info("Lo de thanh cong");

	}

	protected void betLode() {
		String beforeBet = lodePage.getBalance();

		List<WebElement> betNo = lodePage.getBets();
		log.info("Co " + betNo.size() + " so" + "\n");

		int number = randomNumber(betNo.size());
		lodePage.selectNoBet(betNo.get(number));
		log.info("Select number " + betNo.get(number).getText() + "\n");

		String selectNo = lodePage.getNumberSelected();
		log.info(selectNo);

		verifyEquals(betNo.get(number).getText(), selectNo);

		log.info("Bam nut dat cuoc\n");
		lodePage.clickBetButton();

		log.info("Bam nut OK bang xac nhan\n");
		lodePage.clickConfirmButton();

		log.info("Close bang xac nhan\n");
		lodePage.closeConfirmDialog();

		String afterBet = lodePage.getBalance;
		log.info("So tien sau khi danh lo 2 so la " + afterBet + "\n");
		verifyFalse(beforeBet==afterBet);

		log.info("Danh lo thanh cong\n======================\n");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
