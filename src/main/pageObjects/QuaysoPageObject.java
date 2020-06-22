package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.QuaysoPageUI;
import org.apache.commons.lang.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class QuaysoPageObject extends AbstractPage {

    private WebDriver driver;

    public QuaysoPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public List<WebElement> getBets(String value) {

        String tmp = String.format(QuaysoPageUI.dynamicGameOdd, value);
        waitForElementPresentByLocator(driver, By.xpath(tmp));
        return getListElements(driver, By.xpath(tmp));

    }

    public boolean isBetStartPresent(String value) {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        String tmp1 = String.format(QuaysoPageUI.dynamicBetStart, value);
        List<WebElement> startList = getListElements(driver, By.xpath(tmp1));
        if (startList.size() > 0) {
            scrollToQuaysoGame(value);
            Integer remainTime = getBetTimeCountDown(value);
             if (remainTime >= 10) {
                 System.out.println("Check for game id = " + value + "\n");
                 System.out.println("Remaining time = " + remainTime + "\n");
                 return true;
             } else {
                 System.out.println("Check for game id = " + value + "\n");
                 System.out.println("Remaining time = " + remainTime + "\n");
                 return false;
             }
        } else {
            System.out.println("Check for game id = " + value + "\n");
            return false;
        }

    }

    public void scrollToQuaysoGame(String value) {

        String tmp = String.format(QuaysoPageUI.dynamicQuaysoGame, value);
        scrollToElementByLocator(driver, By.xpath(tmp));

    }

    public String getBalance() {

        waitForElementPresentByLocator(driver, QuaysoPageUI.quaysoBalanceLocator);
        return getTextElementByLocator(driver, QuaysoPageUI.quaysoBalanceLocator);

    }


    public Integer getBetTimeCountDown(String value) {

        String tmp = String.format(QuaysoPageUI.dynamicBetTime, value);
        String number = (String) showTextByJS(driver, By.xpath(tmp));
        return NumberUtils.toInt(number);

    }

    public void openBetPanel(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public void selectMoneyToBet(Integer value) {

        String tmp = String.format(QuaysoPageUI.dynamicBetMoney, value);
        waitForElementPresentByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public void clickBetButton() {

        waitForElementPresentByLocator(driver, QuaysoPageUI.betQuaysoButtonLocator);
        clickToElementByJSByLocator(driver, QuaysoPageUI.betQuaysoButtonLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginQuaysoAccount() {

        login(driver, Constants.USERNAME_QUAYSO, Constants.PASSWORD);

    }

    public void switchToLotteryIframe() {

        switchToIframe(driver);

    }

    public void quitLotteryIframe() {

        backToTopWindow(driver);

    }

    public void openQuaysoPage() {

        openSubMenu(driver, "Lottery");

    }

}
