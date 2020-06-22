package payment;

import commons.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.AccountPageObject;

import java.lang.reflect.Method;

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
    public void Run(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_DepositSuccessful");

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
//        log.info("DepositSuccessful - Step 01: Go to deposit page");
//        accountPage.beforeDepositing(userData, "testjimmy");
//
//        log.info("DepositSuccessful - Step 02: Do a depositing");
//        deposit("1000");
//
//        log.info("DepositSuccessful - Step 03: Verify success warning text");
//        verifyEquals(accountPage.getDepositWarning(), "Tạo phiếu nạp thành công");
//        log.info(accountPage.getDepositWarning());
//        try {
//            Thread.sleep(5000);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//        log.info("DepositSuccessful - Step 04: Verify transhistory url");
//        verifyEquals(accountPage.getAccountPageUrl(), Constants.TRANHISTORY_URL);
//
//        log.info("DepositSuccessful - Step 05: Verify deposit ticket is created");
//        verifyEquals(accountPage.getTicketStatus(), "Chờ xử lý");
//
//        log.info("DepositSuccessful - Step 10: Log out");
//        accountPage.logoutToHomePage();
//
//        log.info("Nap tien thanh cong");
//
//    }


    @AfterClass(alwaysRun=true)
    public void afterClass() {

        closeBrowserAndDriver(driver);

    }

    private void deposit(String value) {
        // Kiem tra chuyen den page nap tien
        verifyEquals(accountPage.getAccountPageUrl(), Constants.DEPOSIT_URL);

        // Chon VCB
        accountPage.selectAnOption("bank_code_option", "VCB");

        // Nhap so tien nap
        accountPage.inputToTextBox(value, "amount-money");

        // Nhap ma giao dich
        accountPage.inputToTextBox(Constants.PHONE, "bank_trancode");

        // Bam button nap tien
        accountPage.clickToSubmitButton("frmDeposit");

    }

//    @DataProvider(name = "network")
//    public static Object[][] AccountData(){
//        return new Object[][] {
//                {Constants.USERNAME_THETHAO},
//                {Constants.USERNAME_KENO},
//                {Constants.USERNAME_LODE},
//                {Constants.USERNAME_NUMBER},
//                {Constants.USERNAME_QUAYSO},
//
//        };
//
//    }

}
