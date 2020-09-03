package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.TSportPageUI;
import org.openqa.selenium.By;
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

        return getListElements(driver, TSportPageUI.betTSportLocator);

    }

//    public void openBetPanel(WebElement element) {
//        for (int i = 1; i <= 5; i++) {
//            clickToElementByJS(driver, element);
//            List<WebElement> noBetPanel = getListElements(driver, TSportPageUI.betPanelLocator);
//            if (noBetPanel.size() > 0) {
//                highlightElementByLocator(driver, TSportPageUI.betPanelLocator);
//                break;
//            } else if (i==5) {
//                throw new RuntimeException("Can not open bet panel");
//            }
//        }
//
//    }

    public String isOddCanBet(WebElement element) {
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        clickToElementByJS(driver, element);
        List<WebElement> noOddPaused = getListElements(driver, TSportPageUI.oddPausedTSportLocator);
        if (noOddPaused.size() > 0) {
            System.out.println("Odd is paused\n");
            return "Odd is paused";
        } else {
            return "Odd can be bet";
        }

    }

    public String getBetDetails() {
        waitForElementVisibleByLocator(driver, TSportPageUI.betOrderLocator);
        String betOdd = getTextElementByLocator(driver, TSportPageUI.betOrderLocator);
        waitForElementVisibleByLocator(driver, TSportPageUI.betMatchLocator);
        String betMatch = getTextElementByLocator(driver, TSportPageUI.betMatchLocator);
        return betOdd + "\n" + betMatch;

    }

    public void inputBetMoney(String value) {

        sendKeyToElement(driver, TSportPageUI.inputBetMoneyLocator, value);

    }

    public void clickToBetButton() {

        waitForElementVisibleByLocator(driver, TSportPageUI.betButtonLocator);
        clickToElementByJSByLocator(driver, TSportPageUI.betButtonLocator);

    }

    public void confirmBet(String value) {
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println("Input money to bet = " + value + "");
                inputBetMoney(value);
                System.out.println("Click bet button");
                clickToBetButton();

                List<WebElement> noConfirmButton = getListElements(driver, TSportPageUI.betConfirmTSportLocator);
                if (noConfirmButton.size() > 0) {
                    highlightElementByLocator(driver, TSportPageUI.betConfirmTSportLocator);
                    break;
                } else {
                    if (i==5) {
                        throw new RuntimeException("There is error when get odd. Please try again manually");
                    } else {
                        System.out.println(Constants.betUnsuccessful);
                    }
                }
                System.out.println("Click to confirm\n");
                clickToElementByJSByLocator(driver, TSportPageUI.betConfirmTSportLocator);
            } catch (Throwable e) {
                String error;
                if (!e.toString().contains("\n")) {
                    error = e.toString();
                } else {
                    error = e.toString().substring(0, e.toString().indexOf("\n"));
                }
                if (error.contains("NoSuchElementException")) {
                    e.printStackTrace();
                } else {
                    throw e;
                }
            }
        }
    }

//    public void confirmBet(String value) {
//        try {
//            for (int i = 1; i <= 5; i++) {
//                System.out.println("Input money to bet = " + value + "");
//                inputBetMoney(value);
//                System.out.println("Click bet button");
//                clickToBetButton();
//
//                List<WebElement> noConfirmButton = getListElements(driver, TSportPageUI.betConfirmTSportLocator);
//                if (noConfirmButton.size() > 0) {
//                    highlightElementByLocator(driver, TSportPageUI.betConfirmTSportLocator);
//                    break;
//                } else {
//                    if (i==5) {
//                        throw new RuntimeException("There is error when get odd. Please try again manually");
//                    } else {
//                        System.out.println(Constants.betUnsuccessful);
//                    }
//                }
//            }
//            System.out.println("Click to confirm\n");
//            clickToElementByJSByLocator(driver, TSportPageUI.betConfirmTSportLocator);
//            } catch (Throwable e) {
//                String error;
//                if (!e.toString().contains("\n")) {
//                    error = e.toString();
//                } else {
//                    error = e.toString().substring(0, e.toString().indexOf("\n"));
//                }
//                if (error.contains("NoSuchElementException")) {
//                    throw new RuntimeException("Bet panel is closed");
//                } else {
//                    throw e;
//                }
//            }
//
//    }

    public String ticketDisplayed() {
        overrideTimeout(driver, Constants.LONG_TIMEOUT);
        List<WebElement> noBetTicket = getListElements(driver, TSportPageUI.ticketDetailTSportLocator);
        List<WebElement> noSuccess = getListElements(driver, TSportPageUI.betSuccessfulTSportLocator);
        if (noBetTicket.size() > 0) {
            System.out.println("Ticket true\n");
            return "Bet successfully";
        } else if (noSuccess.size() > 0) {
            System.out.println("Success true\n");
            return "Bet successfully";
        } else {
            System.out.println("False. Please try again\n");
            return "False. Please try again";
        }

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
            Assert.assertTrue(isControlDisplayed(driver, AbstractPageUI.loggedInFormLocator));
        } catch (Throwable e) {
            throw new RuntimeException("Login not successful");
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

    public void verifyBalanceUpdated(String value1, String value2) {
        verifyNotEqual(value1, value2);

    }

    public void loginSportStgAccount() {

        login(driver, Constants.USERNAME_STG, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
            Assert.assertTrue(isControlDisplayed(driver, AbstractPageUI.loggedInFormLocator));
        } catch (Throwable e) {
            throw new RuntimeException("Login not successful");
        }

    }

    public void openStgUrl() {
        String prodUrl = getAttributeValue(driver, By.xpath("//iframe[@class='frm-lottery']"), "src");
        String stgUrl = prodUrl.replaceAll("sport", "sport-stg");
        openAnyUrl(driver, stgUrl);

    }

    public List<WebElement> getLiveBets() {
        return getListElements(driver, TSportPageUI.betLiveTSportLocator);

    }
}
