package payment;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountPageObject;

public class Payment_01_DepositSuccesful extends CommonsTest {

    WebDriver driver;
    AccountPageObject accountPage;

    @Parameters("browser")
    @BeforeClass
    public void preConditions(String browserName) {

        driver = openMultiBrowser(browserName, Constants.HOME_URL);

        accountPage = PageFactoryManager.getAccountPage(driver);

    }

    @Test
    public void TC_01_DepositSuccessful() {
        log.info("DepositSuccessful - Step 01: Go to deposit page");
        accountPage.beforeDepositing();

        log.info("DepositSuccessful - Step 02: Do a depositing");
        deposit();

        log.info("DepositSuccessful - Step 03: Verify success warning text");
        verifyEquals(accountPage.getDepositWarning(), "Tạo phiếu nạp thành công");
        log.info(accountPage.getDepositWarning());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("DepositSuccessful - Step 04: Verify transhistory url");
        verifyEquals(accountPage.getAccountPageUrl(), Constants.TRANHISTORY_URL);

        log.info("DepositSuccessful - Step 05: Verify deposit ticket is created");
        verifyEquals(accountPage.getTicketStatus(), "Chờ xử lý");

        log.info("DepositSuccessful - Step 06: Return to deposit page");
        accountPage.goToSubAccount("Nạp tiền");

        log.info("DepositSuccessful - Step 07: Do a depositing more");
        deposit();

        log.info("DepositSuccessful - Step 08: Verify warning text");
        verifyEquals(accountPage.getDepositWarning(), "Bạn đã tạo quá số phiếu quy định.");
        log.info(accountPage.getDepositWarning());

        log.info("DepositSuccessful - Step 09: Close the warning dialog");
        accountPage.closeWarningDialog();

        log.info("DepositSuccessful - Step 10: Log out");
        accountPage.logoutToHomePage();

        log.info("Nap tien thanh cong");

    }

    @AfterClass(alwaysRun=true)
    public void afterClass() {

        closeBrowserAndDriver(driver);

    }

    private void deposit() {
        // Kiem tra chuyen den page nap tien
        verifyEquals(accountPage.getAccountPageUrl(), Constants.DEPOSIT_URL);

        // Chon VCB
        accountPage.selectAnOption("bank_code_option", "VCB");

        // Nhap so tien nap
        accountPage.inputToTextbox(Constants.MONEY_DEP, "amount-money");

        // Nhap ma giao dich
        accountPage.inputToTextbox(Constants.PHONE, "bank_trancode");

        // Bam button nap tien
        accountPage.clickToSubmitButton("frmDeposit");

    }

}
