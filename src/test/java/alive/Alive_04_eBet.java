package alive;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CasinoPageObject;

import java.util.List;

public class Alive_04_eBet extends CommonsTest {

	WebDriver driver;
	CasinoPageObject casinoPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		casinoPage = PageFactoryManager.getCasinoPage(driver);

	}

	@Test
	public void TC_1_eBet() {
    	log.info("eBet - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		log.info("eBet - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("eBet - Step 03: Select eBet casino\n");
		casinoPage.selectCasinoProvider("ebet");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("eBet - Step 04: Enter eBet lobby\n");
		casinoPage.openCasinoLobby(noBanner.get(0));

		log.info("eBet - Step 05: Wait for eBet lobby\n");
		casinoPage.waitForEBetLobby();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
