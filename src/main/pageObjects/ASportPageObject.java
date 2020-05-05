package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.ASportPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ASportPageObject extends AbstractPage {

    private WebDriver driver;

    public ASportPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public String getBalance() {

        waitForElementVisibleByLocator(driver, ASportPageUI.aSportBalanceLocator);
        return getTextElement(driver, ASportPageUI.aSportBalanceLocator);

    }

    public List<WebElement> getBets() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betASportLocator);
        return getListElements(driver, ASportPageUI.betASportLocator);

    }

    public void openBetPanel(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public String getBetDetails() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betOrderLocator);
        return getTextElement(driver, ASportPageUI.betOrderLocator);

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

    public boolean isTicketDispled() {

        waitForElementVisibleByLocator(driver, ASportPageUI.tickketOKASportLocator);
        return isControlDisplayed(driver, ASportPageUI.tickketOKASportLocator);

    }

    public String getTicketDetails() {

        waitForElementVisibleByLocator(driver, ASportPageUI.betTicketLocator);
        return getTextElement(driver, ASportPageUI.betTicketLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);

    }

    public void switchToASportIframe() {

        switchToIframe(driver);

    }

    public void quitASportIframe() {

        backToTopWindow(driver);

    }
}
