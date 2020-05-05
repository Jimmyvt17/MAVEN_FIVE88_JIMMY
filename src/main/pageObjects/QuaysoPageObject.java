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

    public List<WebElement> getBets(String... values) {

        String tmp = String.format(QuaysoPageUI.dynamicBetGame, values);
        waitForElementPresentByLocator(driver, By.xpath(tmp));
        return getListElements(driver, By.xpath(tmp));

    }

    public void waitForBetStartPresent(String... values) {

        String tmp = String.format(QuaysoPageUI.dynamicBetStart, values);
        waitForElementPresentByLocator(driver, By.xpath(tmp));

    }

    public void scrollToQuaysoGame(String... values) {

        String tmp = String.format(QuaysoPageUI.dynamicBetStart, values);
        scrollToElement(driver, By.xpath(tmp));

    }

    public String getBalance() {

        waitForElementPresentByLocator(driver, QuaysoPageUI.quaysoBalanceLocator);
        return getTextElement(driver, QuaysoPageUI.quaysoBalanceLocator);

    }


    public Integer getBetTimeCountDown(String... values) {

        String tmp = String.format(QuaysoPageUI.dynamicBetTime, values);
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
