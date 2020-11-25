package payment;

import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import commons.utility.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import pageObjects.AccountPageObject;

public class Payment_01_DepositSuccessful extends BaseTest {

    WebDriver driver;
    AccountPageObject accountPage;

    @Parameters("browser")
    @BeforeClass
    public void preConditions(String browserName) {

        driver = openMultiBrowser(browserName, Constants.HOME_URL);

        accountPage = PageFactoryManager.getAccountPage(driver);

    }

    @Override
    public void Run() {
        ExtentTestManager.startTest("TC_01_DepositSuccessful", "TC_01_DepositSuccessful");

        log.info("DepositSuccessful - Step 01: Go to deposit page");
        accountPage.beforeDepositing(Constants.USERNAME, Constants.PASSWORD);

        log.info("DepositSuccessful - Step 02: Do a depositing");
        deposit(Constants.MONEY_DEP);

        log.info("DepositSuccessful - Step 03: Verify success warning text");
        verifyEquals(accountPage.getDepositWarning(), "Tạo phiếu nạp thành công");
        try {
            Thread.sleep(5 * 1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        log.info("DepositSuccessful - Step 04: Verify transhistory url");
        verifyEquals(accountPage.getAccountPageUrl(), Constants.TRANHISTORY_URL);

        log.info("DepositSuccessful - Step 05: Verify deposit ticket is created");
        verifyEquals(accountPage.getTicketStatus(), "Chờ xử lý");

        log.info("DepositSuccessful - Step 06: Return to deposit page");
        accountPage.goToSubAccount("Nạp tiền");
        accountPage.clickToDepositButton();

        log.info("DepositSuccessful - Step 07: Do a depositing more");
        deposit(Constants.MONEY_DEP);

        log.info("DepositSuccessful - Step 08: Verify warning text");
        verifyEquals(accountPage.getDepositWarning(), "Bạn đã tạo quá số phiếu quy định.");

        log.info("DepositSuccessful - Step 09: Close the warning dialog");
        accountPage.refreshAccountPage();

        log.info("DepositSuccessful - Step 10: Log out");
        accountPage.logoutToHomePage();

        log.info("Nap tien thanh cong");

    }

//    @Test(dataProvider= "network")
//    public void TC_02_DepositData(String userData) {
//        ExtentTestManager.startTest("TC_02_DepositData", "TC_02_DepositData");
//        log.info("DepositData - Step 01: Go to deposit page");
//        accountPage.beforeDepositing(userData, Constants.PASSWORD);
//
//        log.info("DepositData - Step 02: Do a depositing");
//        deposit("500");
//
//        log.info("DepositData - Step 03: Verify success warning text");
//        verifyEquals(accountPage.getDepositWarning(), "Tạo phiếu nạp thành công");
//        log.info(accountPage.getDepositWarning());
//        try {
//            Thread.sleep(5 * 1000);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//        log.info("DepositData - Step 04: Verify transhistory url");
//        verifyEquals(accountPage.getAccountPageUrl(), Constants.TRANHISTORY_URL);
//
//        log.info("DepositData - Step 05: Verify deposit ticket is created");
//        verifyEquals(accountPage.getTicketStatus(), "Chờ xử lý");
//
//        log.info("DepositData - Step 06: Log out");
//        accountPage.logoutToHomePage();
//
//    }


    @AfterClass(alwaysRun=true)
    public void afterClass() {

        closeBrowserAndDriver(driver);

    }

    private void deposit(String value) {
        verifyEquals(accountPage.getAccountPageUrl(), Constants.DEPOSIT_URL);

        accountPage.selectAnOption("bank_code_option", "VCB");

        accountPage.inputToTextBox(Constants.SENDER, "from_bank_name");

        accountPage.inputToTextBox(value, "amount-money");

        accountPage.inputToTextBox(Constants.PHONE, "bank_trancode");

        //accountPage.select158Promo();

        accountPage.clickToSubmitButton("frmDeposit");

    }

    @DataProvider(name = "network")
    public static Object[][] AccountData(){
        return new Object[][] {
                {Constants.USERNAME},
                {Constants.USERNAME_THETHAO},
                {Constants.USERNAME_LODE},
                {Constants.USERNAME_QUAYSO},
                {Constants.USERNAME_KENO},
                {Constants.USERNAME_NUMBER},
                {Constants.USERNAME_LOGIN}

        };

    }

}
