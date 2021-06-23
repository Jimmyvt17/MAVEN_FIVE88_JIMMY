package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryManager;
import five88.RegisterPageUI;
import org.openqa.selenium.WebDriver;

import java.util.List;

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

    public void registers() {
        try {
            List<String> usernames = getListFromExcel(Constants.iOSFilePath, Constants.userDataFile, "username");
            for (String username : usernames) {
                openRegisterPage();
                inputToUsernameTextBox(username);
                inputToPasswordTextBox(Constants.PASSWORD);
                inputToPhoneTextBox(Constants.PHONE);
                clickToSubmitButton();
                Thread.sleep(3000);
                logoutToHomePage();

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }



    }

}
