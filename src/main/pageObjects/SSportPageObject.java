package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.SSportPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class SSportPageObject extends AbstractPage {

    private final WebDriver driver;

    public SSportPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public String getUIVersion() {

        return getTextElementByLocator(driver, SSportPageUI.modeUILocator);

    }

    public List<WebElement> getListBets(By xPathLocator) {
        return getListElements(driver, xPathLocator);

    }

    public String getAsiaOddBet(WebElement element) {

        return getTextElement(driver, element);

    }

    public void createBetOder(WebElement element) {

        scrollToElement(driver, element);
        clickToElementByJS(driver, element);

    }

    public boolean isTicketCreated() {
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        List<WebElement> noBetSelect = getListElements(driver, SSportPageUI.oddSelectedLocator);
        return noBetSelect.size() > 0;

    }

    public String getOddSelect() {
        waitForElementVisibleByLocator(driver, SSportPageUI.oddSelectedLocator);
        return getTextElementByLocator(driver, SSportPageUI.oddSelectedLocator);

    }

    public void inputBetMoney(String value) {
        waitForElementVisibleByLocator(driver, SSportPageUI.inputBetSSportMoney);
        sendKeyToElement(driver, SSportPageUI.inputBetSSportMoney, value);

    }

    public String getReturn() {
        return getTextElementByLocator(driver, SSportPageUI.totalReturnLocator);

    }


    public void verifyReturn(String value1, String value2, String number) {

        int tmp1 = NumberUtils.toInt(value1);
        int tmp2 = Math.abs(NumberUtils.toInt(value2) + NumberUtils.toInt(value2) * NumberUtils.toInt(number));
        try {
            Assert.assertEquals(tmp2, tmp1);
            System.out.println("-------------------------- PASSED --------------------------");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            Reporter.getCurrentTestResult().setThrowable(e);
        }

    }

    public void confirmBet() {
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        waitForElementVisibleByLocator(driver, SSportPageUI.betConfirmSSportLocator);
        clickToElementByJSByLocator(driver, SSportPageUI.betConfirmSSportLocator);

    }


    public void acceptConfirmAlert() {

        acceptAlert(driver);

    }

    public boolean isAsiaBetSuccess(String value) {
        for (int i = 0; i < 5; i++) {
            List<WebElement> noBetInput = getListElements(driver, SSportPageUI.inputBetSSportMoney);
            if (noBetInput.size() > 0) {
                System.out.println("Bet money = " + value + "\n");
                inputBetMoney(value);
                System.out.println("Confirm betting\n");
                confirmBet();
                acceptConfirmAlert();
                try {
                    Thread.sleep(10 * 1000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                System.out.println("Verify betting successfully\n");
                List<WebElement> noSuccessText = getListElements(driver, SSportPageUI.ticketOKSSportLocator);
                List<WebElement> noEnoughText = getListElements(driver, SSportPageUI.notEnoughLocator);
                if (noSuccessText.size() > 0) {
                    System.out.println("Bet successfully\n");
                    return true;
                } else if (noEnoughText.size() > 0) {
                    throw new RuntimeException(Constants.notEnoughBalance);
                } else  {
                    System.out.println("Bet unsuccessfully. Try again\n");
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
            Assert.assertTrue(isControlDisplayed(driver, AbstractPageUI.loggedInFormLocator));
        } catch (Throwable e) {
            throw new RuntimeException("Login not successful");
        }

    }

    public void switchToSSportIframe() {

        switchToIframe(driver);

    }

    public String getEuroOddBet(WebElement element) {

        return getTextElement(driver, element);

    }

    public String getContentBet(WebElement element) {

        return getTextElement(driver, element);

    }

    public String getContentSelect() {

        return getTextElementByLocator(driver, SSportPageUI.contentOrderLocator);

    }

    public void openSSportPage(String... values) {

        openSportPage(driver, values);

    }

    public void changeToEUView() {
        waitForElementVisibleByLocator(driver, SSportPageUI.modeUILocator);
        clickToElementByJSByLocator(driver, SSportPageUI.modeUILocator);
        try {
            Thread.sleep(2 * 1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public boolean isStreamingVideoDisplayed() {
        List<WebElement> streaming = getListElements(driver, SSportPageUI.streamingVideoLocator);
        return streaming.size() > 0;

    }

    public void quitSSportIframe() {
        backToTopWindow(driver);

    }

    public void logoutToHomePage() {
        logout(driver);

    }

    public void changeToLiveBet() {
        waitForElementVisibleByLocator(driver, SSportPageUI.betAsiaLiveMatchLocator);
        clickToElementByJSByLocator(driver, SSportPageUI.betAsiaLiveMatchLocator);

    }
}
