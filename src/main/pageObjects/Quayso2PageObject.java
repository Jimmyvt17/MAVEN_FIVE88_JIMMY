package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.Quayso2PageUI;
import org.apache.commons.lang.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class Quayso2PageObject extends AbstractPage {

    private final WebDriver driver;

    public Quayso2PageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public List<WebElement> getBets(String value) {

        String tmp = String.format(Quayso2PageUI.dynamicGameOdd, value);
        waitForElementPresentByLocator(driver, By.xpath(tmp));
        return getListElements(driver, By.xpath(tmp));

    }

    public boolean isBetStartPresent(String value) {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        String tmp1 = String.format(Quayso2PageUI.dynamicBetStart, value);
        List<WebElement> startList = getListElements(driver, By.xpath(tmp1));
        if (startList.size() > 0) {
            scrollToQuaysoGame(value);
            int remainTime = getBetTimeCountDown(value);
            if (remainTime < 0) {
                System.out.println("Check for game id = " + value + "\n");
                System.out.println("Remaining time = " + remainTime + "\n");
                throw new RuntimeException("game id = " + value + " has negative countdown time");
            } else if (remainTime >= 18) {
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

        String tmp = String.format(Quayso2PageUI.dynamicQuayso2Game, value);
        scrollToElementByLocator(driver, By.xpath(tmp));

    }

    public String getBalance() {

        waitForElementPresentByLocator(driver, Quayso2PageUI.quayso2BalanceLocator);
        return getTextElementByLocator(driver, Quayso2PageUI.quayso2BalanceLocator);

    }


    public Integer getBetTimeCountDown(String value) {
        String tmp = String.format(Quayso2PageUI.dynamicBetTime, value);
        highlightElementByLocator(driver, By.xpath(tmp));
        String number = (String) showTextByJS(driver, By.xpath(tmp));
        return NumberUtils.toInt(number.replace(" ", ""));

    }

    public void selectOddBet(WebElement element) {
        clickToElementByJS(driver, element);

    }

    public void selectMoneyToBet(Integer value) {

        String tmp = String.format(Quayso2PageUI.dynamicBetMoney, value);
        waitForElementPresentByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public void clickBetButton() {

        waitForElementPresentByLocator(driver, Quayso2PageUI.betQuayso2ButtonLocator);
        clickToElementByJSByLocator(driver, Quayso2PageUI.betQuayso2ButtonLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginQuaysoAccount() {

        login(driver, Constants.USERNAME_QUAYSO, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void switchToLottery2Iframe() {

        verifyIframeLoading(driver, Quayso2PageUI.quayso2BalanceLocator);

    }

    public void quitLottery2Iframe() {

        backToTopWindow(driver);

    }

    public void openQuayso2Page() {
        openAnyUrl(driver, Constants.QUAYSO2_URL);

    }

    public void verifyBalanceUpdated(String value1, String value2) {
        Assert.assertNotEquals(value1, value2);

    }
}
