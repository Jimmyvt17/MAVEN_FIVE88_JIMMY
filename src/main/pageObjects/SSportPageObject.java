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

        overrideTimeout(driver, Constants.MID_TIMEOUT);
        List<WebElement> noBetSelect = driver.findElements(SSportPageUI.oddSelectedLocator);
        return noBetSelect.size() > 0;

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

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        waitForElementVisibleByLocator(driver, SSportPageUI.betConfirmSSportLocator);
        clickToElementByJSByLocator(driver, SSportPageUI.betConfirmSSportLocator);

    }


    public void acceptConfirmAlert() {

        acceptAlert(driver);

    }

    public boolean isBetSuccess(String value) {

        for (int i = 0; i <= 10; i++) {
            List<WebElement> noBetInput = driver.findElements(SSportPageUI.inputBetSSportMoney);
            if (noBetInput.size() > 0) {
                System.out.println("Nhap so tien cuoc = " + value + "\n");
                inputBetMoney(value);
                System.out.println("Xac nhan cuoc\n");
                confirmBet();
                acceptConfirmAlert();
                try {
                    Thread.sleep(5 * 1000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                System.out.println("Kiem tra cuoc thanh cong\n");
                List<WebElement> noSuccessText = driver.findElements(SSportPageUI.ticketOKSSportLocator);
                if (noSuccessText.size() > 0) {
                    System.out.println("Cuoc thanh cong\n");
                    return true;
                } else {
                    System.out.println("Cuoc loi. Thu lai\n");
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public void loginSportAccount() {

        login(driver, Constants.USERNAME_THETHAO, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void switchToSSportIframe() {

        switchToIframe(driver);

    }

    public void navigateToSSportIframe() {

        String iframeLink = getAttributeValue(driver, By.id("iframe"), "src");
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

    public void openSSportPage(String... values) {

        openSportPage(driver, Constants.STHETHAO_URL, values);

    }

}
