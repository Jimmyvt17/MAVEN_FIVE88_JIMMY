package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.NumberPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NumberPageObject extends AbstractPage {

    private final WebDriver driver;

    public NumberPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public void loginNumberAccount() {

        login(driver, Constants.USERNAME_NUMBER, Constants.PASSWORD);
        try {
            Thread.sleep(1000*5);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void openNumberGamePage() {

        openSubMenu(driver, "Number game");

    }

    public void switchToNumberIframe() {

        verifyIframeLoading(driver, NumberPageUI.balanceLocator);

    }

    public void quitNumberIframe() {

        backToTopWindow(driver);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void waitForBetTime(String value) {

        String tmp = String.format(NumberPageUI.dynamicBetTime, value);
        boolean i = true;
        while (i) {
            String betTime = getTextElementByLocator(driver, By.xpath(tmp));
            if (betTime.equals("No more bets")) {
                System.out.println("Can not bet now");
                try {
                    Thread.sleep(9*1000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                i = true;
            } else {
                System.out.println("Bet now\n");
                i = false;
            }
        }

    }

    public Integer getBetTimeCountDown(String value) {

        String tmp = String.format(NumberPageUI.dynamicBetTime, value);
        String betTime = getTextElementByLocator(driver, By.xpath(tmp));
        return NumberUtils.toInt(betTime.replace("s", ""));

    }

    public String getBalance() {

        waitForElementVisibleByLocator(driver, NumberPageUI.balanceLocator);
        return getTextElementByLocator(driver, NumberPageUI.balanceLocator);

    }

    public List<WebElement> getBet(String value) {

        String tmp = String.format(NumberPageUI.dynamicBetPoint, value);
        return getListElements(driver, By.xpath(tmp));

    }

    public void clickToBetPoint(WebElement element) {
        clickToElementByJS(driver, element);

    }

    public void inputMoneyToBet(String bet_money) {

        waitForElementVisibleByLocator(driver, NumberPageUI.betInputLocator);
        sendKeyToElementByJS(driver, NumberPageUI.betInputLocator, bet_money);

    }

    public void clickBetButton() {

        waitForElementVisibleByLocator(driver, NumberPageUI.betSubmitLocator);
        clickToElementByJSByLocator(driver, NumberPageUI.betSubmitLocator);

    }

    public void closeWarning() {

        waitForElementVisibleByLocator(driver, NumberPageUI.warningCloseLocator);
        clickToElementByJSByLocator(driver, NumberPageUI.warningCloseLocator);

    }

    public String getTicketDetail() {

        waitForElementVisibleByLocator(driver, NumberPageUI.ticketContentLocator);
        return getTextElementByLocator(driver, NumberPageUI.ticketContentLocator);

    }

    public String getBetDetail() {

        waitForElementVisibleByLocator(driver, NumberPageUI.betContentLocator);
        return getTextElementByLocator(driver, NumberPageUI.betContentLocator);

    }

}



