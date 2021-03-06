package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.ASportPageUI;
import five88.AbstractPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ASportPageObject extends AbstractPage {

    private final WebDriver driver;

    public ASportPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public String getBalance() {

        waitForElementVisibleByLocator(driver, ASportPageUI.aSportBalanceLocator);
        return getTextElementByLocator(driver, ASportPageUI.aSportBalanceLocator);

    }

    public List<WebElement> getBets() {
        return getListElements(driver, ASportPageUI.betASportLocator);

    }

    public void openBetPanel(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public String getBetDetails() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betOrderLocator);
        return getTextElementByLocator(driver, ASportPageUI.betOrderLocator);

    }

    public void selectBetMoney(String value) {
        String tmp = String.format(ASportPageUI.dynamicBetMoney, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public void confirmBet() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betConfirmASportLocator);
        clickToElementByJSByLocator(driver, ASportPageUI.betConfirmASportLocator);

    }

    public void closeWarningDialog() {
        waitForElementVisibleByLocator(driver, ASportPageUI.betOKASportLocator);
        clickToElementByJSByLocator(driver, ASportPageUI.betOKASportLocator);

    }

    public void openBetBoard() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betBoardASportLocator);
        clickToElementByJSByLocator(driver, ASportPageUI.betBoardASportLocator);

    }

    public boolean isTicketDisplayed() {

        waitForElementVisibleByLocator(driver, ASportPageUI.ticketOKASportLocator);
        return isControlDisplayed(driver, ASportPageUI.ticketOKASportLocator);

    }

    public String getTicketDetails() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betTicketLocator);
        return getTextElementByLocator(driver, ASportPageUI.betTicketLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

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

    public void switchToASportIframe() {
        verifyIframesLoading(driver, ASportPageUI.aSportBalanceLocator);

    }

    public void quitASportIframe() {

        backToTopWindow(driver);

    }

    public void openASportPage(String... values) {

        openSportPage(driver, values);

    }

    public void verifyWarningDisplayed() {
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        List<WebElement> noWarning = getListElements(driver, ASportPageUI.warningLocator);
        if (noWarning.size() > 0) {
            System.out.println("Not enough balance\n");
            throw new RuntimeException(Constants.notEnoughBalance);
        }

    }

    public void verifyBalanceUpdated(String value1, String value2) {
        verifyNotEqual(value1, value2);

    }

}
