package bet;

//public class Bet_02_Lode extends BaseTest {
//
//	WebDriver driver;
//	LodePageObject lodePage;
//
//    @Parameters(value = "browser")
//	@BeforeClass
//	public void preConditions(String browserName) {
//
//		driver = openMultiBrowser(browserName, Constants.HOME_URL);
//
//		lodePage = PageFactoryManager.getLodePage(driver);
//
//	}
//
//	@Override
//	public void Run() {
//		ExtentTestManager.startTest("TC_1_Lode", "TC_1_Lode");
//
//		log.info("Lode - Step 01: Login with valid account\n");
//		lodePage.loginLodeAccount();
//
//		log.info("Lode - Step 02: Switch to Lode page\n");
//		lodePage.openLodePage();
//
//		log.info("Lode - Step 03: Switch to iframe to play");
//		lodePage.switchToLodeIframe();
//
//		log.info("Lode - Step 04: Play Lode 2 so");
//		betLode();
//
//		log.info("Lode - Step 05: Play Lode 3 so");
//		lodePage.switchToLo3So();
//		betLode();
//
//		log.info("Lode - Step 06: Exit iframe\n");
//		lodePage.quitLodeIframe();
//
//		log.info("Lode - Step 07: Logout\n");
//		lodePage.logoutToHomePage();
//
//	}
//
//	protected void betLode() {
//		String beforeBet = lodePage.getBalance();
//		log.info("Before balance is " + beforeBet + "\n");
//
//		List<WebElement> betNo = lodePage.getBets();
//		log.info("There are " + betNo.size() + " odds" + "\n");
//
//		int number = randomNumber(betNo.size());
//		lodePage.selectNoBet(betNo.get(number));
//		log.info("Select number " + betNo.get(number).getText() + "\n");
//
//		String selectNo = lodePage.getNumberSelected();
//		log.info(selectNo);
//
//		verifyEquals(betNo.get(number).getText(), selectNo);
//
//		log.info("Press bet button\n");
//		lodePage.clickBetButton();
//
//		log.info("Confirm betting\n");
//		lodePage.clickConfirmButton();
//
//		log.info("Close warning dialog\n");
//		lodePage.closeConfirmDialog();
//
//		String afterBet = lodePage.getBalance;
//		log.info("After balance is " + afterBet + "\n");
//		lodePage.verifyBalanceUpdated(beforeBet, afterBet);
//
//	}
//
//	@AfterClass(alwaysRun=true)
//	public void afterClass() {
//
//		closeBrowserAndDriver(driver);
//
//	}
//
//}
