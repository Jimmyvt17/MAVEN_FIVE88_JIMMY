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
        //clickToElementByLocator(driver, TSportPageUI.betButtonLocator);
        clickToElementByJSByLocator(driver, TSportPageUI.betButtonLocator);

    }

    public void confirmBet() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betConfirmTSportLocator);
        //clickToElementByLocator(driver, TSportPageUI.betConfirmTSportLocator);
        clickToElementByJSByLocator(driver, TSportPageUI.betConfirmTSportLocator);

    }

    public void openBetBoard() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betBoardTSportLocator);
        clickToElementByLocator(driver, TSportPageUI.betBoardTSportLocator);
        waitForElementVisibleByLocator(driver, TSportPageUI.inProcessTicketTSportLocator);
        clickToElementByLocator(driver, TSportPageUI.inProcessTicketTSportLocator);

    }

    public boolean isTicketDisplayed() {

        List<WebElement> noBetTicket = driver.findElements(TSportPageUI.ticketOKTSportLocator);
        return noBetTicket.size() > 0;

    }

    public String getTicketDetails() {

        return getTextElementByLocator(driver, TSportPageUI.ticketOKTSportLocator);

    }


    public String getBetTicketDetails() {

        return getTextElementByLocator(driver, TSportPageUI.betTicketLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);

    }

    public void switchToTSportIframe(String fileName) {

        //switchToIframe(driver, Constants.windowsFilePath, Constants.loadingTimeFile, "TSport");
        getIframeLoadingTime(driver, TSportPageUI.tSportBalanceLocator, fileName, "TSport");

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
