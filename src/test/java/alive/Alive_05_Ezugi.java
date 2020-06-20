package alive;

import commons.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.CasinoPageObject;

import java.lang.reflect.Method;
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
	public void Run(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_1_Ezugi");

		log.info("Ezugi - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		log.info("Ezugi - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("Ezugi - Step 03: Select Ezugi casino\n");
		casinoPage.selectCasinoProvider("ezugi");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("Ezugi - Step 04: Enter Ezugi lobby\n");
		casinoPage.openCasinoLobby(noBanner.get(0));

		log.info("Ezugi - Step 05: Wait for Ezugi lobby\n");
		casinoPage.waitForEzugiGame();

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
