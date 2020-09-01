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

public class Alive_03_HoGaming extends BaseTest {

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
		ExtentTestManager.startTest("TC_1_Hogaming", "TC_1_Hogaming");

		log.info("Hogaming - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		String mainID = casinoPage.getPageID();

		log.info("Hogaming - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("Hogaming - Step 03: Select Hogaming casino\n");
		casinoPage.selectCasinoProvider("hogaming");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("Hogaming - Step 04: Enter Hogaming lobby\n");
		casinoPage.openCasinoTab(noBanner.get(0));

		log.info("Switch to Hogaming tab\n");
		casinoPage.switchToCasinoTab(mainID);

		log.info("Hogaming - Step 05: Wait for Hogaming lobby");
		casinoPage.waitForCasinoGame(CasinoPageUI.hoGamingLoadingLocator, CasinoPageUI.hoGamingLobbyMenuLocator);

		log.info("Hogaming - Step 06: Verify Baccarat games alive\n");
		verifyHoGaimgGameAlive("Bài cào 3 lá");

		log.info("Hogaming - Step 07: Verify Roulette games alive\n");
		verifyHoGaimgGameAlive("Cò quay");

		log.info("Hogaming - Step 08: Return to main page\n");
		casinoPage.returnToMainTab(mainID);

		log.info("Hogaming - Step 09: Logout\n");
		casinoPage.logoutToHomePage();

	}

	private void verifyHoGaimgGameAlive(String category) {
		log.info("Select a category\n");
		casinoPage.selectHoGamingCategory(category);

		List<WebElement> gameNo = casinoPage.getHoGamingGames();
		log.info("There are " + gameNo.size() + " opening rooms\n================================================================================\n");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
