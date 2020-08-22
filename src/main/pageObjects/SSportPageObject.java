package pageObjects;

import commons.AbstractPage;
import commons.Constants;
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

    public String getBalance() {

        waitForElementVisibleByLocator(driver, SSportPageUI.sSportBalanceLocator);
        return getTextElementByLocator(driver, SSportPageUI.sSportBalanceLocator);

    }

    public List<WebElement> getListBets(By xPathLocator) {

        waitForElementVisibleByLocator(driver, xPathLocator);
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

        overrideTimeout(driver, Constants.MID_TIMEOUT);
        List<WebElement> noBetSelect = driver.findElements(SSportPageUI.oddSelectedLocator);
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

        for (int i = 0; i <= 10; i++) {
            List<WebElement> noBetInput = getListElements(driver, SSportPageUI.inputBetSSportMoney);
            if (noBetInput.size() > 0) {
                System.out.println("Bet money = " + value + "\n");
                inputBetMoney(value);
                System.out.println("Confirm betting\n");
                confirmBet();
                acceptConfirmAlert();
                try {
                    Thread.sleep(5 * 1000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                System.out.println("Verify betting successfully\n");
                List<WebElement> noSuccessText = getListElements(driver, SSportPageUI.ticketOKSSportLocator);
                if (noSuccessText.size() > 0) {
                    System.out.println("Bet successfully\n");
                    return true;
                } else {
                    System.out.println("Bet unsuccessfully. Try again\n");
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean isEUBetSuccess(String value) {

        for (int i = 0; i <= 10; i++) {
            List<WebElement> noBetInput = getListElements(driver, SSportPageUI.inputBetSSportMoney);
            if (noBetInput.size() > 0) {
                System.out.println("Bet money = " + value + "\n");
                inputBetMoney(value);
                System.out.println("Confirm betting\n");
                confirmBet();
                try {
                    Thread.sleep(5 * 1000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                System.out.println("Verify betting successfully\n");
                List<WebElement> noSuccessText = getListElements(driver, SSportPageUI.ticketOKSSportLocator);
                if (noSuccessText.size() > 0) {
                    System.out.println("Bet successfully\n");
                    return true;
                } else {
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
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void switchToSSportIframe() {

        switchToIframe(driver);

    }

    public void navigateToSSportIframe() {

        String iframeLink = getAttributeValue(driver, By.id("iframe"), "src");
        openAnyUrl(driver, iframeLink);

    }

    public String getViewMode() {

        waitForElementVisibleByLocator(driver, SSportPageUI.modeUILocator);
        return getTextElementByLocator(driver, SSportPageUI.modeUILocator);

    }

//    public void switchToEUMode() {
//
//        waitForElementVisibleByLocator(driver, SSportPageUI.modeUILocator);
//        clickToElementByLocator(driver, SSportPageUI.modeUILocator);
//
//    }

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

    }

    public boolean isStreamingVideoDisplayed() {
        try {
            List<WebElement> streaming = getListElements(driver, SSportPageUI.streamingVideoLocator);
            return streaming.size() > 0;
        } catch (Throwable e) {
            throw new RuntimeException("Streaming video is not displayed");
        }

    }

    public void quitSSportIframe() {
        backToTopWindow(driver);

    }

    public void logoutToHomePage() {
        logout(driver);

    }

}
