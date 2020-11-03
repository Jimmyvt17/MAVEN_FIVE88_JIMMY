package bet;

//public class Bet_09_Quayso2 extends BaseTest {
//
//	WebDriver driver;
//	Quayso2PageObject quayso2Page;
//
//	@Parameters(value = "browser")
//	@BeforeClass
//	public void preConditions(String browserName) {
//		driver = openMultiBrowser(browserName, Constants.HOME_URL);
//		quayso2Page = PageFactoryManager.getQuayso2Page(driver);
//
//	}
//
//	@Override
//	public void Run() {
//		ExtentTestManager.startTest("TC_1_Quayso2", "TC_1_Quayso2");
//
//		log.info("Quayso2 - Step 01: Login with valid account\n");
//		quayso2Page.loginQuaysoAccount();
//
//		log.info("Quayso2 - Step 02: Switch to Quayso page\n");
//		quayso2Page.openQuayso2Page();
//
//		log.info("Quayso2 - Step 03: Switch to iframe to play");
//		quayso2Page.switchToLottery2Iframe();
//
//		log.info("Quayso2 - Step 04: Play lottery");
//		betLotteryGame();
//
//		log.info("Quayso2 - Step 05: Exit iframe\n");
//		quayso2Page.quitLottery2Iframe();
//
//		log.info("Quayso2 - Step 06: Logout\n");
//		quayso2Page.logoutToHomePage();
//
//	}
//
//	private void betLotteryGame() {
//		List<String> listA = new ArrayList<>();
//		listA.add("97");
//		listA.add("98");
//		listA.add("99");
//		listA.add("100");
//		log.info("First game list = " + listA + "\n");
//
//		while (listA.size() > 0) {
//			for (int count = 1; count <= 20; count++) {
//				log.info("count = " + count + "\n========================================\n");
//				for (int i = 0; i < listA.size(); i++) {
//					Assert.assertTrue(count < 20);
//					String x = listA.get(i);
//					if (!quayso2Page.isBetStartPresent(x)) {
//						log.info(x + " can not bet now\n====================\n");
//					} else {
//						betALotteryGame(listA, x);
//					}
//				}
//			}
//		}
//
//	}
//
//	private void betALotteryGame(List<String> gameList, String gameId) {
//		log.info(gameId + " can bet now\n");
//		String beforeBet = quayso2Page.getBalance();
//		log.info("Before balance = " + beforeBet + "\n");
//
//		List<WebElement> noBet = quayso2Page.getBets(gameId);
//		log.info("There are " + noBet.size() + " bet points " + "\n");
//
//		int  number = randomNumber(noBet.size() - 1);
//
//		log.info("Select bet point at order " + number + 1 + "\n");
//		quayso2Page.selectOddBet(noBet.get(number));
//
//		log.info("Confirm betting\n");
//		quayso2Page.clickBetButton();
//		try {
//			Thread.sleep(1000);
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//
//		String afterBet = quayso2Page.getBalance();
//		log.info("After balance = " + afterBet + "\n");
//
//		log.info("Verify that balance is updated correctly\n");
//		quayso2Page.verifyBalanceUpdated(beforeBet, afterBet);
//
//		log.info("Remove " + gameId + " from game list\n");
//		gameList.remove(gameId);
//		log.info("Then game list = " + gameList + "\n====================\n");
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
