package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.KenoPageUI;
import five88.NumberPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class KenoPageObject extends AbstractPage {

    private WebDriver driver;

    public KenoPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public void loginKenoAccount() {

        login(driver, Constants.USERNAME_KENO, Constants.PASSWORD);

    }

    public void openKenoPage() {

        openSubMenu(driver, "icon-mkeno");

    }

    public void switchToKenoIframe() {

        switchToIframe(driver);

    }

    public void quitKenoIframe() {

        backToTopWindow(driver);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void waitForBetTime(String value) {

        String tmp = String.format(KenoPageUI.dynamicBetStart, value);
        scrollToElement(driver, By.xpath(tmp));
        waitForElementVisibleByLocator(driver, By.xpath(tmp));

    }

    public Integer getBetTimeCountDown(String value) {

        String tmp = String.format(KenoPageUI.dynamicBetTime, value);
        String betTime = getTextElement(driver, By.xpath(tmp));
        return NumberUtils.toInt(betTime.replace("0:", ""));

    }

    public String getBalance() {

        waitForElementVisibleByLocator(driver, KenoPageUI.balanceLocator);
        return getTextElement(driver, KenoPageUI.balanceLocator);

    }

    public List<WebElement> getBet(String value) {

        String tmp = String.format(KenoPageUI.dynamicBetPoint, value);
        return getListElements(driver, By.xpath(tmp));

    }

    public void openBetPanel(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public void selectMoneyToBet(String bet_money) {

        String tmp = String.format(KenoPageUI.dynamicBetMoney, bet_money);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public void clickBetButton() {

        waitForElementVisibleByLocator(driver, KenoPageUI.betButtonLocator);
        clickToElementByJSByLocator(driver, KenoPageUI.betButtonLocator);

    }

}



