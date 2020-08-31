package alive;

import commons.utility.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import five88.CasinoPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.CasinoPageObject;

import java.util.List;

public class Alive_04_eBet extends BaseTest {

	WebDriver driver;
	CasinoPageObject casinoPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		casinoPage = PageFactoryManager.getCasinoPage(driver);

	}

	@Override
	public void Run() {
		ExtentTestManager.startTest("TC_1_eBet", "TC_1_eBet");

		log.info("eBet - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		String mainID = casinoPage.getPageID();

		log.info("eBet - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("eBet - Step 03: Select eBet casino\n");
		casinoPage.selectCasinoProvider("ebet");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("eBet - Step 04: Enter eBet lobby\n");
		casinoPage.openCasinoTab(noBanner.get(0));

		log.info("Switch to Sexy casino tab\n");
		casinoPage.switchToCasinoTab(mainID);

		log.info("eBet - Step 05: Wait for eBet lobby\n");
		casinoPage.waitForCasinoGame(CasinoPageUI.eBetLoadingPageLocator, CasinoPageUI.eBetLobbyLocator);

		log.info("eBet - Step 06: Return to main page\n");
		casinoPage.returnToMainTab(mainID);

		log.info("eBet - Step 07: Logout\n");
		casinoPage.logoutToHomePage();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
