package alive;

import commons.utility.BaseTest;
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

public class Alive_01_Vivo extends BaseTest {

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
		ExtentTestManager.startTest(method.getName(), "TC_1_Vivo");

    	log.info("Vivo - Step 01: Login valid account\n");
    	casinoPage.loginCasinoAccount();

		log.info("Vivo - Step 02: Switch to Casino page\n");
		casinoPage.openCasinoPage();

		log.info("Vivo - Step 03: Select Vivo casino\n");
		casinoPage.selectCasinoProvider("vivo");

		List<WebElement> noBanner = casinoPage.getGameBanners();
		log.info("There are " + noBanner.size() + " game banners\n");

		log.info("Vivo - Step 04: Enter Vivo lobby\n");
		casinoPage.openCasinoLobby(noBanner.get(4));

		log.info("Vivo - Step 05: Wait for Vivo lobby\n");
		casinoPage.waitForVivoLobbyLoadCompleted();

		log.info("Vivo - Step 06: Verify Baccarat games alive\n");
		verifyVivoGameAlive("menu-nav-baccarat", "col-4 table-view baccarat");

		log.info("Vivo - Step 07: Verify Roulette games alive\n");
		verifyVivoGameAlive("menu-nav-roulette", "col-4 table-view roulette");

		log.info("Vivo - Step 08: Verify Blackjack games alive\n");
		verifyVivoGameAlive("menu-nav-blackjack", "col-4 table-view blackjack");

		log.info("Vivo - Step 08: Verify Hold'em games alive\n");
		verifyVivoGameAlive("menu-nav-holdem", "col-4 table-view casinoholdem");

	}

	private void verifyVivoGameAlive(String category, String game) {
		log.info("Select a category\n");
		casinoPage.selectVivoCategory(category);

		List<WebElement> gameNo = casinoPage.getVivoGames(game);
		log.info("There are " + gameNo.size() + " opening rooms\n================================================================================\n");

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		
		closeBrowserAndDriver(driver);
		
	}

}
