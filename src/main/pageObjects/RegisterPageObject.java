package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryManager;
import five88.RegisterPageUI;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends AbstractPage {

    private final WebDriver driver;

    public RegisterPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void openRegisterPage() {

        openAnyUrl(driver, Constants.REGISTER_URL);

    }

    public void inputToUsernameTextBox(String username) {

        waitForElementVisibleByLocator(driver, RegisterPageUI.usernameResgisLocator);
        sendKeyToElement(driver, RegisterPageUI.usernameResgisLocator, username);

    }

    public void inputToPasswordTextBox(String password) {

        waitForElementVisibleByLocator(driver, RegisterPageUI.passwordRegisLocator);
        sendKeyToElement(driver, RegisterPageUI.passwordRegisLocator, password);

    }

    public void inputToPhoneTextBox(String phone) {

        waitForElementVisibleByLocator(driver, RegisterPageUI.phoneRegisLocator);
        sendKeyToElement(driver, RegisterPageUI.phoneRegisLocator, phone);

    }

    public AccountPageObject clickToSubmitButton() {

        waitForElementVisibleByLocator(driver, RegisterPageUI.submitButtonLocator);
        clickToElementByJSByLocator(driver, RegisterPageUI.submitButtonLocator);
        return PageFactoryManager.getAccountPage(driver);

    }

    public String  getUsernameError() {

        waitForElementVisibleByLocator(driver, RegisterPageUI.usernameRegisError);
        return getTextElementByLocator(driver, RegisterPageUI.usernameRegisError);

    }

    public String  getPasswordError() {

        waitForElementVisibleByLocator(driver, RegisterPageUI.passwordRegisError);
        return getTextElementByLocator(driver, RegisterPageUI.passwordRegisError);

    }

    public String  getPhoneError() {

        waitForElementVisibleByLocator(driver, RegisterPageUI.phoneRegisError);
        return getTextElementByLocator(driver, RegisterPageUI.phoneRegisError);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void saveUsername(String... values) {
        try {
            writeToExcelFile(Constants.windowsFilePath, Constants.userDataFile, "UserData", values);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
