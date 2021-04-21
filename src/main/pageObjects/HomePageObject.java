package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryManager;
import five88.HomePageUI;
import five88.Quayso2PageUI;
import five88.QuaysoPageUI;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomePageObject extends AbstractPage {

    private final WebDriver driver;

    public HomePageObject(WebDriver mappingDriver) {

        driver = mappingDriver;

    }

    public String getLoginErrorText() {

        waitForElementVisibleByLocator(driver, HomePageUI.loginErrorLocator);
        return getTextElementByLocator(driver, HomePageUI.loginErrorLocator);

    }

    public void closeWarningDialog() {
        clickToElementByJSByLocator(driver, HomePageUI.closeDialogButtonLocator);

    }

    public void logout() {

        logout(driver);

    }

    public void loginAccount() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);

    }

    public void loginKM158Account() {

        login(driver, Constants.USERNAME_158, Constants.PASSWORD);

    }

    public void loginKM100Account() {

        login(driver, Constants.USERNAME_100, Constants.PASSWORD);

    }

    public void loginAccounts() {
        try {
            List<String> usernames = getListFromExcel(Constants.iOSFilePath, Constants.userDataFile, "username");
            for (String username : usernames) {
                System.out.println("\nLogin with " + username);
                login(driver, username, Constants.PASSWORD);
                System.out.println("Logout " + username);
                logout(driver);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public ASportPageObject openASportPage(String... values) {
        openSportPage(driver, values);
        return PageFactoryManager.getASportPage(driver);

    }

    public SSportPageObject openSSportPage(String... values) {
        openSportPage(driver, values);
        return PageFactoryManager.getSSportPage(driver);

    }

    public TSportPageObject openTSportPage(String... values) {
        openSportPage(driver, values);
        return PageFactoryManager.getTSportPage(driver);

    }

    public QuaysoPageObject openQuaysoPage() {
        clickToElementByJSByLocator(driver, QuaysoPageUI.quaysoMenuLocator);
        return PageFactoryManager.getQuaysoPage(driver);

    }

    public Quayso2PageObject openQuayso2Page() {
        clickToElementByJSByLocator(driver, Quayso2PageUI.quayso2MenuLocator);
        return PageFactoryManager.getQuayso2Page(driver);

    }

    public NumberPageObject openNumberGamePage() {
        openSubMenu(driver, "Number game");
        return PageFactoryManager.getNumberPage(driver);

    }

    public LodePageObject openLodePage() {
        openSubMenu(driver, "Lô đề  ");
        return PageFactoryManager.getLodePage(driver);

    }

}
