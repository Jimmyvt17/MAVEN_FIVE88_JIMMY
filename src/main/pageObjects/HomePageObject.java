package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends AbstractPage {

    private WebDriver driver;

    public HomePageObject(WebDriver mappingDriver) {

        driver = mappingDriver;

    }

    public String getLoginErrorText() {

        waitForElementVisibleByLocator(driver, HomePageUI.loginErrorLocator);
        return getTextElement(driver, HomePageUI.loginErrorLocator);

    }

    public boolean isLoginFormDisplayed() {

        waitForElementVisibleByLocator(driver, AbstractPageUI.loginFormLocator);
        return isControlDisplayed(driver, AbstractPageUI.loginFormLocator);

    }

    public void closeWarningDialog() {

        waitForElementVisibleByLocator(driver, HomePageUI.closeLoginWaringLocator);
        clickToElement(driver, HomePageUI.closeLoginWaringLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginAccount() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);

    }

}
