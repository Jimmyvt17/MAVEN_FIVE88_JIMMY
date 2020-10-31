package payment;

import commons.utility.BaseTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.AccountPageObject;

public class Payment_02_DepositFailed extends BaseTest {

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
        ExtentTestManager.startTest("TC_01_DepositFailed", "TC_01_DepositFailed");

        log.info("DepositFailed - Step 01: Go to deposit page");
        accountPage.beforeDepositing(Constants.USERNAME_LOGIN, Constants.PASSWORD);

        log.info("DepositFailed - Step 02: Deposit without selecting bank");
        depositWithoutBank();

        log.info("DepositFailed - Step 03: Verify bank error");
        verifyTrue(accountPage.isBankErrorDisplayed());
        log.info("Nap ko thanh cong ko chon ngan hang");

        log.info("DepositFailed - Step 04: Deposit without inputting money");
        depositWithoutMoney();

        log.info("DepositFailed - Step 05: Verify Money error");
        verifyTrue(accountPage.isMoneyErrorDisplayed());
        log.info("Nap ko thanh cong ko nhap tien");

        log.info("DepositFailed - Step 06: Deposit without inputting trancode");
        depositWithoutCode();

        log.info("DepositFailed - Step 07: Verify trancode error");
        verifyTrue(accountPage.isCodeErrorDisplayed());
        log.info("Nap ko thanh cong ko nhap ma giao dich");

        log.info("DepositFailed - Step 08: Deposit money under 50");
        depositMoneyUnder50();

        log.info("DepositFailed - Step 09: Verify warning dialog");
        verifyEquals(accountPage.getDepositWarning(), "Số tiền tối thiểu là 50 K (50.000 VNĐ)");

        log.info("DepositFailed - Step 10: Close warning dialog");
        accountPage.refreshAccountPage();

        log.info("DepositFailed - Step 11: Logout");
        accountPage.logoutToHomePage();

    }

    public void depositMoneyUnder50() {
        accountPage.selectAnOption("bank_code_option", "VCB");
        accountPage.inputToTextBox(Constants.SENDER, "from_bank_name");
        accountPage.inputToTextBox("49", "amount-money");
        accountPage.inputToTextBox(Constants.PHONE, "bank_trancode");
        accountPage.clickToSubmitButton("frmDeposit");
    }

    public void depositWithoutCode() {
        accountPage.selectAnOption("bank_code_option", "VCB");
        accountPage.inputToTextBox(Constants.SENDER, "from_bank_name");
        accountPage.inputToTextBox(Constants.MONEY_DEP, "amount-money");
        accountPage.clearTextBox("bank_trancode");
        accountPage.clickToSubmitButton("frmDeposit");
    }

    public void depositWithoutMoney() {
        accountPage.selectAnOption("bank_code_option", "VCB");
        accountPage.inputToTextBox(Constants.SENDER, "from_bank_name");
        accountPage.clearTextBox("amount-money");
        accountPage.inputToTextBox(Constants.PHONE, "bank_trancode");
        accountPage.clickToSubmitButton("frmDeposit");
    }

    public void depositWithoutBank() {
        accountPage.inputToTextBox(Constants.SENDER, "from_bank_name");
        accountPage.inputToTextBox(Constants.MONEY_DEP, "amount-money");
        accountPage.inputToTextBox(Constants.PHONE, "bank_trancode");
        accountPage.clickToSubmitButton("frmDeposit");
    }

    @AfterClass(alwaysRun=true)
    public void afterClass() {

        closeBrowserAndDriver(driver);

    }

}
