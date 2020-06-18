package payment;

import commons.CommonsTest;
import commons.Constants;
import commons.PageFactoryManager;
import commons.reportConfig.ExtentTestManager;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountPageObject;

import java.lang.reflect.Method;

public class Payment_03_WithdrawBank extends CommonsTest {

    WebDriver driver;
    AccountPageObject accountPage;

    @Parameters("browser")
    @BeforeClass
    public void preConditions(String browserName) {

        driver = openMultiBrowser(browserName, Constants.HOME_URL);

        accountPage = PageFactoryManager.getAccountPage(driver);

    }

    @Test
    public void TC_01_WithdrawBanking(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_WithdrawBanking");

        log.info("WithdrawBanking - Step 01: Get current balance");
        Integer curBalance = accountPage.beforeWithdrawing();
        log.info("Tai khoan truoc khi rut = " + curBalance);

        log.info("WithdrawBanking - Step 02: Do a bank withdrawing");
        accountPage.bankWithdraw();

        log.info("WithdrawBanking - Step 03: Get after balance");
        Integer aftBalance = accountPage.getBalance();
        log.info("Tai khoan sau khi rut = " + aftBalance);

        log.info("WithdrawBanking - Step 04: Verify that withdrawing is correct");
        verifyEquals(curBalance - aftBalance, NumberUtils.toInt(Constants.MONEY_WIT));

        log.info("WithdrawBanking - Step 05: Log out");
        accountPage.logoutToHomePage();

        log.info("Rut tien ngan hang thanh cong");

    }

    @AfterClass(alwaysRun=true)
    public void afterClass() {

        closeBrowserAndDriver(driver);

    }

}
