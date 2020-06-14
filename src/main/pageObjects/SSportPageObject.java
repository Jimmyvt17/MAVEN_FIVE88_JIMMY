package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.SSportPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class SSportPageObject extends AbstractPage {

    private WebDriver driver;

    public SSportPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public String getUIVersion() {

        return getTextElementByLocator(driver, SSportPageUI.modeUILocator);

    }

    public String getBalance() {

        waitForElementVisibleByLocator(driver, SSportPageUI.sSportBalanceLocator);
        return getTextElementByLocator(driver, SSportPageUI.sSportBalanceLocator);

    }

    public List<WebElement> getListBets(By xPathLocator) {

        waitForElementVisibleByLocator(driver, xPathLocator);
        return getListElements(driver, xPathLocator);

    }

    public String getAsiaOddBet(WebElement element) {

        return getTextElement(driver, element);

    }

    public void createBetOder(WebElement element) {

        scrollToElement(driver, element);
        clickToElementByJS(driver, element);

    }

    public boolean isTicketCreated() {

        return isControlDisplayed(driver, SSportPageUI.betSelectedLocator);

    }

    public String getOddSelect() {

        waitForElementVisibleByLocator(driver, SSportPageUI.oddSelectedLocator);
        return getTextElementByLocator(driver, SSportPageUI.oddSelectedLocator);

    }

    public void inputBetMoney(String value) {

        waitForElementVisibleByLocator(driver, SSportPageUI.inputBetSSportMoney);
        sendKeyToElement(driver, SSportPageUI.inputBetSSportMoney, value);

    }

    public String getReturn() {

        return getTextElementByLocator(driver, SSportPageUI.totalReturnLocator);

    }


    public void verifyReturn(String value1, String value2, String number) {

        int tmp1 = NumberUtils.toInt(value1);
        int tmp2 = Math.abs(NumberUtils.toInt(value2) + NumberUtils.toInt(value2) * NumberUtils.toInt(number));
        try {
            Assert.assertEquals(tmp2, tmp1);
            System.out.println("-------------------------- PASSED --------------------------");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            Reporter.getCurrentTestResult().setThrowable(e);
        }

    }

    public void confirmBet() {

        waitForElementVisibleByLocator(driver, SSportPageUI.betConfirmSSportLocator);
        clickToElementByJSByLocator(driver, SSportPageUI.betConfirmSSportLocator);

    }


    public void acceptConfirmAlert() {

        acceptAlert(driver);

    }

    public void verifyBetSuccess() {

        waitForElementVisibleByLocator(driver, SSportPageUI.ticketOKSSportLocator);

    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);

    }

    public void navigateSSportIframe() {

        String iframeLink = getAttributeValue(driver, By.xpath("//iframe[@id='iframe']"), "src");
        openAnyUrl(driver, iframeLink);

    }

    public void switchToEUMode() {

        waitForElementVisibleByLocator(driver, SSportPageUI.modeUILocator);
        clickToElementByLocator(driver, SSportPageUI.modeUILocator);

    }

    public String getEuroOddBet(WebElement element) {

        return getTextElement(driver, element);

    }

    public String getContentBet(WebElement element) {

        return getTextElement(driver, element);

    }

    public String getContentSelect() {

        return getTextElementByLocator(driver, SSportPageUI.contentOrderLocator);

    }

}
