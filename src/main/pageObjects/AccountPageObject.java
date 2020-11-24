package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AccountPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountPageObject extends AbstractPage {

    private final WebDriver driver;

    public AccountPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public String getAccountPageUrl() {

        return getCurrentPageUrl(driver);

    }

    public String getUsername() {

        waitForElementVisibleByLocator(driver, AccountPageUI.loginNameLocator);
        return getTextElementByLocator(driver, AccountPageUI.loginNameLocator);

    }

    public void clickToDepositButton() {

        waitForElementVisibleByLocator(driver, AccountPageUI.depositButtonLocator);
        clickToElementByJSByLocator(driver, AccountPageUI.depositButtonLocator);
        waitForElementVisibleByLocator(driver, AccountPageUI.chuyenkhoanLocator);
        clickToElementByJSByLocator(driver, AccountPageUI.chuyenkhoanLocator);

    }

    public void inputToTextBox(String valueToInput, String dynamicValue) {

        String tmp = String.format(AccountPageUI.dynamicTextBox, dynamicValue);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        sendKeyToElement(driver, By.xpath(tmp), valueToInput);

    }

    public void clickToSubmitButton(String value) {

        String tmp = String.format(AccountPageUI.dynamicSubmitButton, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public String getDepositWarning() {

        waitForElementVisibleByLocator(driver, AccountPageUI.depositInformLocator);
        return getTextElementByLocator(driver, AccountPageUI.depositInformLocator);

    }

    public String getTicketStatus() {

        waitForElementVisibleByLocator(driver, AccountPageUI.ticketStatusLocator);
        return getTextElementByLocator(driver, AccountPageUI.ticketStatusLocator);

    }

    public void selectAnOption(String... values) {

        String tmp = String.format(AccountPageUI.dynamicSelectDropBox, values);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByLocator(driver, By.xpath(tmp));

    }

    public void select158Promo() {

        waitForElementVisibleByLocator(driver, AccountPageUI.depPromo158Locator);
        clickToElementByLocator(driver, AccountPageUI.depPromo158Locator);

    }


    public void goToSubAccount(String value) {

        String tmp = String.format(AccountPageUI.dynamicSubAccount, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));
        waitForElementVisibleByLocator(driver, AccountPageUI.chuyenkhoanLocator);
        clickToElementByJSByLocator(driver, AccountPageUI.chuyenkhoanLocator);

    }

    public void refreshAccountPage() {

        refreshCurrentPage(driver);

    }

    public boolean isBankErrorDisplayed() {

        String tmp = String.format(AccountPageUI.dynamicDepositError, "bank_code_option-error");
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        return isControlDisplayed(driver, By.xpath(tmp));

    }

    public boolean isMoneyErrorDisplayed() {

        String tmp = String.format(AccountPageUI.dynamicDepositError, "amount-money-error");
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        return isControlDisplayed(driver, By.xpath(tmp));

    }

    public boolean isCodeErrorDisplayed() {

        String tmp = String.format(AccountPageUI.dynamicDepositError, "bank_trancode-error");
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        return isControlDisplayed(driver, By.xpath(tmp));

    }

    public void clearTextBox(String value) {

        String tmp = String.format(AccountPageUI.dynamicTextBox, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clearTextElement(driver, By.xpath(tmp));

    }

    public Integer getBalance() {

        waitForElementVisibleByLocator(driver, AccountPageUI.userBalanceLocator);
        return getElementAsInt(driver, AccountPageUI.userBalanceLocator);

    }

    public void inputConfirmPhone(String value) {

        waitForElementVisibleByLocator(driver, AccountPageUI.phoneConfirmWitLocator);
        sendKeyToElement(driver, AccountPageUI.phoneConfirmWitLocator, value);

    }

    public void clickToCardWithdraw() {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardWithdrawLocator);
        clickToElementByJSByLocator(driver, AccountPageUI.cardWithdrawLocator);

    }

    public void inputToNoCardTextBox(String value) {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardNoLocator);
        sendKeyToElement(driver, AccountPageUI.cardNoLocator, value);

    }

    public void inputCardConfirmPhone(String value) {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardPhoneConfirmLocator);
        sendKeyToElement(driver, AccountPageUI.cardPhoneConfirmLocator, value);

    }

    public void clickToCardWithdrawSubmitButton() {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardWitSubmitLocator);
        clickToElementByJSByLocator(driver, AccountPageUI.cardWitSubmitLocator);

    }

    public Integer beforeWithdrawing() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);
        clickToDepositButton();
        goToSubAccount("Rút tiền");
        try {
            Thread.sleep(3000);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return getBalance();

    }

    public void beforeDepositing(String username, String password) {

        login(driver, username, password);
        clickToDepositButton();

    }

    public void cardWithdraw() {

        clickToCardWithdraw();
        selectAnOption("to_telcom_code", "MOBIFONE");
        selectAnOption("card_amount_unit", "100000");
        inputToNoCardTextBox("1");
        inputCardConfirmPhone("67890");
        clickToCardWithdrawSubmitButton();
        try {
            Thread.sleep(5000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void bankWithdraw() {

        inputToTextBox(Constants.PHONE, "to_bank_no");
        inputToTextBox(Constants.MONEY_WIT, "amount");
        selectAnOption("to_bank_code", "BIDV");
        inputConfirmPhone("67890");
        clickToSubmitButton("frmWitdraw");
        try {
            Thread.sleep(5000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    public void logoutToHomePage() {

        logout(driver);

    }

    public void verifyAfterRegister(String value1, String value2) {
        try {
            Assert.assertEquals(value1, value2);
        } catch (Throwable e) {
            throw new RuntimeException("Register unsuccessful");
        }
    }
}



