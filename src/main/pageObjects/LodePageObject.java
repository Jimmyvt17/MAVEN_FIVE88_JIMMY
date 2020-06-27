package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.LodePageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LodePageObject extends AbstractPage {

    public String getBalance;
    private WebDriver driver;

    public LodePageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public String getBalance() {

        waitForElementPresentByLocator(driver, LodePageUI.lodeBalanceLocator);
        return getTextElementByLocator(driver, LodePageUI.lodeBalanceLocator);

    }

    public String getNumberSelected() {

        waitForElementPresentByLocator(driver, LodePageUI.numberSelectLocator);
        return getTextElementByLocator(driver, LodePageUI.numberSelectLocator);

    }

    public void selectNoBet(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public void clickBetButton() {

        waitForElementPresentByLocator(driver, LodePageUI.betLodeButtonLocator);
        clickToElementByJSByLocator(driver, LodePageUI.betLodeButtonLocator);

    }

    public void clickConfirmButton() {

        waitForElementPresentByLocator(driver, LodePageUI.okButtonLocator);
        clickToElementByJSByLocator(driver, LodePageUI.okButtonLocator);

    }

    public void closeConfirmDialog() {

        waitForElementPresentByLocator(driver, LodePageUI.okButtonLocator);
        clickToElementByJSByLocator(driver, LodePageUI.okButtonLocator);

    }

    public List<WebElement> getBets() {

        waitForElementPresentByLocator(driver, LodePageUI.numberListLocator);
        return getListElements(driver, LodePageUI.numberListLocator);

    }

    public void switchToLo3So() {

        waitForElementPresentByLocator(driver, LodePageUI.lo3soLocator);
        clickToElementByJSByLocator(driver, LodePageUI.lo3soLocator);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public void switchToLodeIframe(String fileName) {

        //switchToIframe(driver, Constants.windowsFilePath, Constants.loadingTimeFile, "Lode");
        getIframeLoadingTime(driver, LodePageUI.lodeBalanceLocator, fileName,"Lode");

    }

    public void quitLodeIframe() {

        backToTopWindow(driver);

    }

    public void loginLodeAccount() {

        login(driver, Constants.USERNAME_LODE, Constants.PASSWORD);
        try {
            Thread.sleep(5000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void openLodePage() {

        openSubMenu(driver, "Lô đề");

    }
}
