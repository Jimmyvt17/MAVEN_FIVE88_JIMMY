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


public class Payment_04_WithdrawCard extends CommonsTest {

    WebDriver driver;
    AccountPageObject accountPage;

    @Parameters("browser")
    @BeforeClass
    public void preConditions(String browserName) {

        driver = openMultiBrowser(browserName, Constants.HOME_URL);

        accountPage = PageFactoryManager.getAccountPage(driver);

    }

    @Test
    public void TC_01_WithdrawCard() throws Exception {
        log.info("WithdrawBanking - Step 01: Get current balance");
        Integer curBalance = accountPage.beforeWithdrawing();
        log.info("Tai khoan truoc khi rut = " + curBalance);

        log.info("WithdrawBanking - Step 02: Do a card withdrawing");
        accountPage.cardWithdraw();

        log.info("WithdrawBanking - Step 03: Get after balance");
        Integer aftBalance = accountPage.getBalance();
        log.info("Tai khoan sau khi rut = " + aftBalance);

        log.info("WithdrawBanking - Step 04: Verify that withdrawing is correct");
        verifyEquals(curBalance - aftBalance, 100);

        log.info("WithdrawBanking - Step 05: Log out");
        accountPage.logoutToHomePage();

        log.info("Rut tien the cao thanh cong");

    }

    @AfterClass(alwaysRun=true)
    public void afterClass() {

        closeBrowserAndDriver(driver);

    }

}
