package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.TSportPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TSportPageObject extends AbstractPage {

    private final WebDriver driver;

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

        for (int i = 0; i <= 10; i++) {
            clickToElementByJS(driver, element);
            List<WebElement> noBetPanel = driver.findElements(TSportPageUI.betPanelLocator);
            if (noBetPanel.size() > 0) {
                highlightElementByLocator(driver, TSportPageUI.betPanelLocator);
                break;
            } else if (i==10) {
                throw new RuntimeException("Can not open bet panel");
            }
        }

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

    public void confirmBet(WebElement element, String value) {

        for (int i = 0; i <= 10; i++) {
            System.out.println("Click to open bet panel\n");
            openBetPanel(element);
            try {
                Thread.sleep(3 * 1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            System.out.println("Input money to bet = " + value + "\n");
            inputBetMoney(value);
            System.out.println("Click bet button\n");
            clickToBetButton();
            List<WebElement> noConfirmButton = driver.findElements(TSportPageUI.betConfirmTSportLocator);
            if (noConfirmButton.size() > 0) {
                highlightElementByLocator(driver, TSportPageUI.betConfirmTSportLocator);
                break;
            } else {
                if (i==10) {
                    throw new RuntimeException("There is error when get odd. Please try again manually");
                } else {
                    System.out.println("Not success, please try again\n");
                }
            }
        }
        System.out.println("Click to confirm\n");
        clickToElementByJSByLocator(driver, TSportPageUI.betConfirmTSportLocator);

    }

    public void openBetBoard() {

        waitForElementVisibleByLocator(driver, TSportPageUI.inProcessTicketTSportLocator);
        clickToElementByLocator(driver, TSportPageUI.inProcessTicketTSportLocator);

    }

    public boolean isTicketDisplayed() {

        overrideTimeout(driver, Constants.MID_TIMEOUT);
        List<WebElement> noBetTicket = driver.findElements(TSportPageUI.ticketDetailTSportLocator);
        if (noBetTicket.size() > 0) {
            System.out.println("Tao phieu cuoc thanh cong\n");
            return true;
        } else {
            System.out.println("Tao phieu cuoc loi. Thu lai\n");
            return false;
        }

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

        Assert.assertTrue(isControlDisplayed(driver, TSportPageUI.minBetTSportLocator));
        System.out.println("-------------------------- PASSED --------------------------");

    }

    public void openTSportPage(String... values) {

        openSportPage(driver, values);

    }

}
