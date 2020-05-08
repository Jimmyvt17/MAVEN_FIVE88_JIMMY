package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.AccountPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPageObject extends AbstractPage {

    private WebDriver driver;

    public AccountPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public String getAccountPageUrl() {

        return getCurrentPageUrl(driver);

    }

    public String getUsername() {

        waitForElementVisibleByLocator(driver, AccountPageUI.loginNameLocator);
        return getTextElement(driver, AccountPageUI.loginNameLocator);

    }

    public void clickToDepositButton() {

        waitForElementVisibleByLocator(driver, AccountPageUI.depositButtonLocator);
        clickToElement(driver, AccountPageUI.depositButtonLocator);

    }

    public void inputToTextbox(String valueToInput, String dynamicValue) {

        String tmp = String.format(AccountPageUI.dynamicTextbox, dynamicValue);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        sendKeyToElement(driver, By.xpath(tmp), valueToInput);

    }

    public void clickToSubmitButton(String value) {

        String tmp = String.format(AccountPageUI.dynamicSubmitButton, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElement(driver, By.xpath(tmp));

    }

    public String getDepositWarning() {

        waitForElementVisibleByLocator(driver, AccountPageUI.depositInformLocator);
        return getTextElement(driver, AccountPageUI.depositInformLocator);

    }

    public String getTicketStatus() {

        waitForElementVisibleByLocator(driver, AccountPageUI.ticketStatusLocator);
        return getTextElement(driver, AccountPageUI.ticketStatusLocator);

    }

    public boolean isTicketStatusDisplayed(String... values) {

        String tmp = String.format(AccountPageUI.dynamicTicketStatus, values);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        return isControlDisplayed(driver, By.xpath(tmp));

    }

    public void selectAnOption(String... values) {

        String tmp = String.format(AccountPageUI.dynamicSelect, values);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElement(driver, By.xpath(tmp));

    }

    public void select158Promo() {

        waitForElementVisibleByLocator(driver, AccountPageUI.depPromo158Locator);
        clickToElement(driver, AccountPageUI.depPromo158Locator);

    }


    public void goToSubAccount(String value) {

        String tmp = String.format(AccountPageUI.dynamicSubAccount, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElement(driver, By.xpath(tmp));

    }

    public void closeWarningDialog() {

        waitForElementVisibleByLocator(driver, AbstractPageUI.closeButtonLocator);
        clickToElement(driver, AbstractPageUI.closeButtonLocator);

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

    public void clearTextbox(String value) {

        String tmp = String.format(AccountPageUI.dynamicTextbox, value);
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
        clickToElement(driver, AccountPageUI.cardWithdrawLocator);

    }

    public void inputToNoCardTextbox(String value) {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardNoLocator);
        sendKeyToElement(driver, AccountPageUI.cardNoLocator, value);

    }

    public void inputCardConfirmPhone(String value) {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardPhoneConfirmLocator);
        sendKeyToElement(driver, AccountPageUI.cardPhoneConfirmLocator, value);

    }

    public void clickToCardWithdrawSubmitButton() {

        waitForElementVisibleByLocator(driver, AccountPageUI.cardWitSubmitLocator);
        clickToElement(driver, AccountPageUI.cardWitSubmitLocator);

    }

    public Integer beforeWithdrawing() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);
        clickToDepositButton();
        goToSubAccount("Rút tiền");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getBalance();

    }

    public void beforeDepositing() {

        login(driver, Constants.USERNAME, Constants.PASSWORD);
        clickToDepositButton();

    }

    public void cardWithdraw() {

        clickToCardWithdraw();
        selectAnOption("to_telcom_code", "VIETTEL");
        selectAnOption("card_amount_unit", "100000");
        inputToNoCardTextbox("1");
        inputCardConfirmPhone("67890");
        clickToCardWithdrawSubmitButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void bankWithdraw() {

        inputToTextbox(Constants.PHONE, "to_bank_no");
        inputToTextbox(Constants.MONEY_WIT, "amount");
        selectAnOption("to_bank_code", "VCB");
        inputConfirmPhone("67890");
        clickToSubmitButton("frmWitdraw");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void logoutToHomePage() {

        logout(driver);

    }

}



