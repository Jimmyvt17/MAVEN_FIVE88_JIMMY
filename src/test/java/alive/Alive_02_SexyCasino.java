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

public class Alive_02_SexyCasino extends CommonsTest {

	WebDriver driver;
	CasinoPageObject casinoPage;

    @Parameters(value = "browser")
	@BeforeClass
	public void preConditions(String browserName) {

		driver = openMultiBrowser(browserName, Constants.HOME_URL);

		casinoPage = PageFactoryManager.getCasinoPage(driver);

	}

	@Test
	public void TC_1_Sexy() {
    	log.info("Sexy - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		log.info("Sexy - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("Sexy - Step 03: Select Sexy casino\n");
		casinoPage.selectSexyCasino();

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("Sexy - Step 04: Enter Sexy lobby\n");
		casinoPage.openCasinoLobby(noBanner.get(0));

		log.info("Sexy - Step 05: Wait for loading complete\n");
		casinoPage.waitForSexyCasino();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}