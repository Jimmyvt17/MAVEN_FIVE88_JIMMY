package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.ASportPageUI;
import five88.TSportPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TSportPageObject extends AbstractPage {

    private WebDriver driver;

    public TSportPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public String getBalance() {

        waitForElementVisibleByLocator(driver, TSportPageUI.tSportBalanceLocator);
        return getTextElement(driver, TSportPageUI.tSportBalanceLocator);

    }

    public List<WebElement> getBets() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betTSportLocator);
        return getListElements(driver, TSportPageUI.betTSportLocator);

    }

    public void openBetPanel(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public String getBetDetails() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betOrderLocator);
        return getTextElement(driver, TSportPageUI.betOrderLocator);

    }

    public void selectBetMoney(String value) {

        String tmp = String.format(TSportPageUI.dynamicBetMoney, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public void confirmBet() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betConfirmTSportLocator);
        clickToElementByJSByLocator(driver, TSportPageUI.betConfirmTSportLocator);

    }

    public void openBetBoard() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betBoardTSportLocator);
        clickToElementByJSByLocator(driver, TSportPageUI.betBoardTSportLocator);

    }

    public boolean isTicketDisplayed() {

        waitForElementVisibleByLocator(driver, TSportPageUI.ticketOKTSportLocator);
        return isControlDisplayed(driver, TSportPageUI.ticketOKTSportLocator);

    }

    public String getTicketDetails() {

        return getTextElement(driver, TSportPageUI.ticketOKTSportLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);

    }

    public void switchToTSportIframe() {

        switchToIframe(driver);

    }

    public void quitTSportIframe() {

        backToTopWindow(driver);

    }

}
