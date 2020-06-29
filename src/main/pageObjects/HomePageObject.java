package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends AbstractPage {

    private final WebDriver driver;

    public HomePageObject(WebDriver mappingDriver) {

        driver = mappingDriver;

    }

    public String getLoginErrorText() {

        waitForElementVisibleByLocator(driver, HomePageUI.loginErrorLocator);
        return getTextElementByLocator(driver, HomePageUI.loginErrorLocator);

    }

    public void refreshHomePage() {

        refreshCurrentPage(driver);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void loginAccount() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);

    }

}
