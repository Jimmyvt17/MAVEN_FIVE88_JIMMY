package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.TSportPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TSportPageObject extends AbstractPage {

    private WebDriver driver;

    public TSportPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public String getBalance() {

        waitForElementVisibleByLocator(driver, TSportPageUI.tSportBalanceLocator);
        return getTextElementByLocator(driver, TSportPageUI.tSportBalanceLocator);

    }

    public List<WebElement> getBets() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betTSportLocator);
        return getListElements(driver, TSportPageUI.betTSportLocator);

    }

    public void openBetPanel(WebElement element) {

        //clickToElement(driver, element);
        clickToElementByJS(driver, element);

    }

    public String getBetDetails() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betOrderLocator);
        return getTextElementByLocator(driver, TSportPageUI.betOrderLocator);

    }

    public void inputBetMoney(String value) {

        sendKeyToElement(driver, TSportPageUI.inputBetMoneyLocator, value);

    }

    public void clickToBetButton() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betButtonLocator);
        clickToElementByJSByLocator(driver, TSportPageUI.betButtonLocator);

    }

    public void confirmBet(String value) {

        for (int i = 1; i <= 10; i++) {
            System.out.println("Input money to bet = " + value + "\n");
            inputBetMoney(value);
            System.out.println("Click bet button\n");
            clickToBetButton();
            List<WebElement> noConfirmButton = driver.findElements(TSportPageUI.betConfirmTSportLocator);
            if (noConfirmButton.size() > 0) {
                highlightElementByLocator(driver, TSportPageUI.betConfirmTSportLocator);
                break;
            } else {
                Assert.assertFalse(i==10);
                System.out.println("Not success, please try again\n");
            }
        }
        System.out.println("Confirm betting\n");
        clickToElementByJSByLocator(driver, TSportPageUI.betConfirmTSportLocator);

    }

    public void openBetBoard() {

        waitForElementVisibleByLocator(driver, TSportPageUI.inProcessTicketTSportLocator);
        clickToElementByLocator(driver, TSportPageUI.inProcessTicketTSportLocator);

    }

    public boolean isTicketDisplayed() {

        overrideTimeout(driver, Constants.MID_TIMEOUT);
        List<WebElement> noBetTicket = driver.findElements(TSportPageUI.ticketOKTSportLocator);
        return noBetTicket.size() > 0;

    }

    public String getTicketDetails() {

        return getTextElementByLocator(driver, TSportPageUI.ticketDetailTSportLocator);

    }


    public String getBetTicketDetails() {

        return getTextElementByLocator(driver, TSportPageUI.betTicketLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void switchToTSportIframe() {

        verifyIframeLoading(driver, TSportPageUI.tSportBalanceLocator);

    }

    public void quitTSportIframe() {

        backToTopWindow(driver);

    }

    public void verifyMinBet() {

        Assert.assertTrue(isControlDisplayed(driver, TSportPageUI.minBetTSportLocator)==true);
        System.out.println("-------------------------- PASSED --------------------------");

    }

    public void openTSportPage(String... values) {

        openSportPage(driver, Constants.TTHETHAO_URL, values);

    }

}
