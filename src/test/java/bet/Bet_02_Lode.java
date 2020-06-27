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

	@Parameters({"network", "pass", "browser"})
	@BeforeClass
	public void preConditions(String networkName, String networkPass, String browserName) {

		switchToSpecificNetwork(networkName, networkPass);

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		lodePage = PageFactoryManager.getLodePage(driver);

	}

	@Parameters(value = "file")
	@Test
	public void Run(Method method, String fileName) {
		ExtentTestManager.startTest(method.getName(), "TC_1_Lode");

		log.info("Lode - Step 01: Login with valid account");
		lodePage.loginLodeAccount();

		log.info("Lode - Step 02: Switch to Lode page");
		lodePage.openLodePage();

		log.info("Lode - Step 03: Switch to iframe to play");
		lodePage.switchToLodeIframe(fileName);

		log.info("Lode - Step 04: Play Lode 2 so");
		//betLode();

		log.info("Lode - Step 05: Play Lode 3 so");
		lodePage.switchToLo3So();
		//betLode();

		log.info("Lode - Step 06: Exit iframe");
		lodePage.quitLodeIframe();

		log.info("Lode - Step 07: Logout");
		lodePage.logoutToHomePage();

		log.info("Lo de thanh cong");

	}

	protected void betLode() {
		String beforeBet = lodePage.getBalance();
		log.info("So tien truoc khi danh la " + beforeBet + "\n");

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
