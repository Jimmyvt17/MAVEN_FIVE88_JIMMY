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

public class Alive_05_Ezugi extends BaseTest {

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
		ExtentTestManager.startTest("TC_1_Ezugi", "TC_1_Ezugi");

		log.info("Ezugi - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		String mainID = casinoPage.getPageID();

		log.info("Ezugi - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("Ezugi - Step 03: Select Ezugi casino\n");
		casinoPage.selectCasinoProvider("ezugi");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("Ezugi - Step 04: Enter Ezugi lobby\n");
		casinoPage.openCasinoTab(noBanner.get(0));

		log.info("Switch to Ezugi tab\n");
		casinoPage.switchToCasinoTab(mainID);

		log.info("Ezugi - Step 05: Wait for Ezugi lobby");
		casinoPage.waitForCasinoGame(CasinoPageUI.ezugiLoadingLocator, CasinoPageUI.ezugiGamesLocator);

		log.info("Ezugi - Step 06: Return to main page\n");
		casinoPage.returnToMainTab(mainID);

		log.info("Ezugi - Step 07: Logout\n");
		casinoPage.logoutToHomePage();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
