package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.JackpotPageUI;
import five88.NumberPageUI;
import freemarker.template.utility.NumberUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.velocity.tools.view.tools.AbstractPagerTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.table.TableRowSorter;
import java.util.ConcurrentModificationException;
import java.util.List;

public class NumberPageObject extends AbstractPage {

    private WebDriver driver;

    public NumberPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public void loginNumberAccount() {

        login(driver, Constants.USERNAME_NUMBER, Constants.PASSWORD);

    }

    public void openNumberGamePage() {

        openSubMenu(driver, "icon-mnbg");

    }

    public void switchToNumberIframe() {

        switchToIframe(driver);

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
            String betTime = getTextElement(driver, By.xpath(tmp));
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
        String betTime = getTextElement(driver, By.xpath(tmp));
        return NumberUtils.toInt(betTime.replace("s", ""));

    }

    public String getBalance() {

        waitForElementVisibleByLocator(driver, NumberPageUI.balanceLocator);
        return getTextElement(driver, NumberPageUI.balanceLocator);

    }

    public List<WebElement> getBet(String value) {

        String tmp = String.format(NumberPageUI.dynamicBetPoint, value);
        return getListElements(driver, By.xpath(tmp));

    }

    public void clickToBetPoint(WebElement element) {

        //waitForElementVisible(driver, element);
        clickToElementByJS(driver, element);

    }

    public void inputMoneyToBet(String bet_money) {

        waitForElementVisibleByLocator(driver, NumberPageUI.betInputLocator);
        sendkeyToElementByJS(driver, NumberPageUI.betInputLocator, bet_money);

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
        return getTextElement(driver, NumberPageUI.ticketContentLocator);

    }

    public String getBetDetail() {

        waitForElementVisibleByLocator(driver, NumberPageUI.betContentLocator);
        return getTextElement(driver, NumberPageUI.betContentLocator);

    }
}



