package bet;

//public class Bet_08_SSport extends BaseTest {
//
//	WebDriver driver;
//	SSportPageObject sSportPage;
//
//    @Parameters(value = "browser")
//	@BeforeClass
//	public void preConditions(String browserName) {
//
//		driver = openMultiBrowser(browserName, Constants.HOME_URL);
//
//		sSportPage = PageFactoryManager.getSSportPage(driver);
//
//	}
//
//	@Override
//	public void Run() {
//		ExtentTestManager.startTest("TC_1_SSport", "TC_1_SSport");
//		log.info("SSport - Step01: Login with valid account\n");
//		sSportPage.loginSportAccount();
//
//		log.info("SSport - Step02: Open SSport page\n");
//		sSportPage.openSSportPage("item-sb ssport", "icon-vi-s");
//
//		log.info("SSport - Step03: Betting");
//		betSSport();
//
//		log.info("SSport - Step04: Exit SSport iframe\n");
//		sSportPage.quitSSportIframe();
//
//		log.info("SSport - Step05: Logout\n");
//		sSportPage.logoutToHomePage();
//
//	}
//
//	private void betSSport() {
//		log.info("Switch to SSport iframe");
//		sSportPage.switchToSSportIframe();
//
//		log.info("Bet Asia version");
//		betAsiaSport();
//
//		log.info("Change to Asia live odds\n");
//		sSportPage.changeToLiveBet();
//
//		log.info("Check for Asia streaming video\n");
//		checkForStreamingVideo("Asian view", SSportPageUI.betAsiaStreamingVideoLocator);
//
//		log.info("Change to EU view\n");
//		sSportPage.changeToEUView();
//
//		log.info("Check for EU streaming video\n");
//		checkForStreamingVideo("EU view", SSportPageUI.betEuroStreamingVideoLocator);
//
//	}
//
//	private void betAsiaSport() {
//		for (int x = 0; x <= 5; x++) {
//			if (x < 5) {
//				List<WebElement> listBet = sSportPage.getListBets(SSportPageUI.betAsiaSSportLocator);
//				log.info("The number of odd is " + listBet.size() + "\n");
//
//				int betSelect = randomNumber(listBet.size());
//				log.info("Select odd at order " + betSelect + "\n");
//				try {
//					Thread.sleep(1000);
//				} catch (Throwable e) {
//					e.printStackTrace();
//				}
//
//				String oddBet = sSportPage.getAsiaOddBet(listBet.get(betSelect));
//				log.info("Selected odd is " + oddBet + "\n");
//
//				log.info("Create bet order\n");
//				sSportPage.createBetOder(listBet.get(betSelect));
//
//				log.info("Verify bet oder is created\n");
//				if (sSportPage.isTicketCreated()) {
//					String oddSelect = sSportPage.getOddSelect();
//					log.info("The odd is " + oddSelect + "\n");
//
//					log.info("Verify odd is correct\n");
//					verifyEquals(oddBet, oddSelect);
//
//					log.info("Process betting\n");
//					if (sSportPage.isAsiaBetSuccess("30")) {
//						break;
//					}
//				} else {
//					log.info(Constants.betUnsuccessful);
//				}
//			} else {
//				throw new RuntimeException(Constants.betUnsuccessful);
//			}
//		}
//	}
//
//	private void checkForStreamingVideo(String viewMode, By xPathLocator) {
//		List<WebElement> noVideo = sSportPage.getListBets(xPathLocator);
//		if (noVideo.size() > 0) {
//			log.info("There are " + noVideo.size() + " streaming videos\n");
//
//			int videoSelect = randomNumber(noVideo.size());
//			log.info("Select streaming video at order " + videoSelect + "\n");
//
//			log.info("Open a streaming video\n");
//			sSportPage.createBetOder(noVideo.get(videoSelect));
//
//			log.info("Verify video is displayed\n" + sSportPage.isStreamingVideoDisplayed() + "\n");
//			if (!sSportPage.isStreamingVideoDisplayed()) {
//				throw new RuntimeException(viewMode + "\n" + Constants.streamingVideoError);
//			}
//		} else {
//			log.info(Constants.noStreamingVideo + "\n");
//			sendBot(getClass().getName() + ":\n" + viewMode + "\n" + Constants.noStreamingVideo);
//		}
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
//
//}
