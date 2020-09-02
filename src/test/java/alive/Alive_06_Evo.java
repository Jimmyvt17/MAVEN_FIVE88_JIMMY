package alive;

import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import commons.utility.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.CasinoPageObject;

import java.util.List;

public class Alive_06_Evo extends BaseTest {

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
		ExtentTestManager.startTest("TC_1_Evo", "TC_1_Evo");

		log.info("Evo - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		String mainID = casinoPage.getPageID();

		log.info("Evo - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("Evo - Step 03: Select Evo casino\n");
		casinoPage.selectCasinoProvider("evo");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("Evo - Step 04: Enter Evo lobby\n");
		casinoPage.openCasinoTab(noBanner.get(0));

		log.info("Switch to Evo tab\n");
		casinoPage.switchToCasinoTab(mainID);

		log.info("Evo - Step 05: Wait for Evo lobby");
		casinoPage.waitForEvoCasino();

		log.info("Evo - Step 06: Return to main page\n");
		casinoPage.returnToMainTab(mainID);

		log.info("Evo - Step 07: Logout\n");
		casinoPage.logoutToHomePage();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
