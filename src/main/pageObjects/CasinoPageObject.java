package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.CasinoPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CasinoPageObject extends AbstractPage {

    private final WebDriver driver;

    public CasinoPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void loginCasinoAccount() {

        login(driver, Constants.USERNAME_CASINO, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
            Assert.assertTrue(isControlDisplayed(driver, AbstractPageUI.loggedInFormLocator));
        } catch (Throwable e) {
            throw new RuntimeException("Login not successful");
        }

    }

    public String getPageID() {
        return getPageID(driver);

    }

    public void openCasinoPage() {

        openSubMenu(driver, "Sòng bài");

    }

    public void selectCasinoProvider(String value) {

        waitForElementVisibleByLocator(driver, CasinoPageUI.casinoProviderLocator);
        clickToElementByJSByLocator(driver, CasinoPageUI.casinoProviderLocator);
        String tmp = String.format(CasinoPageUI.dynamicCasinoProvider, value);
        waitForElementPresentByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));
        try {
            Thread.sleep(5*1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void selectSexyCasino() {

        waitForElementPresentByLocator(driver, CasinoPageUI.sexyCasinoLocator);
        clickToElementByJSByLocator(driver, CasinoPageUI.sexyCasinoLocator);
        try {
            Thread.sleep(5*1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public List<WebElement> getGameBanners() {

        return getListElements(driver, CasinoPageUI.bannerCasinoLocator);

    }

    public void openCasinoTab(WebElement element) {
        waitForElementVisible(driver, element);
        clickToElementByJS(driver, element);
        try {
            Thread.sleep(3 * 1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void switchToCasinoTab(String pageID) {
        switchToChildWindowByID(driver, pageID);

    }

    public void returnToMainTab(String pageId) {
        closeAllWithoutParentWindows(driver, pageId);

    }

    public void waitForVivoLobbyLoadCompleted() {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            List<WebElement> icon = getListElements(driver, CasinoPageUI.vivoVideoLocator);
            int noIcon = icon.size();
            if (noIcon==0) {
                System.out.println("Now loading... " + i * 3 + "s");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
                highlightElement(driver, icon.get(0));
                System.out.println("Loading complete\n");
                break;
            }
            Assert.assertTrue(i < 20);
        }

    }

    public void waitForSexyCasino() {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            WebElement icon = getElement(driver, CasinoPageUI.logoLiveSexyCasinoLocator);
            if (verifyIconDisplayed(i, icon)) break;
        }
        waitForElementVisibleByLocator(driver, CasinoPageUI.vivoGameToolBarLocator);
        try {
            Thread.sleep(3000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void selectVivoCategory(String value) {

        String tmp = String.format(CasinoPageUI.dynamicVivoCategory, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public List<WebElement> getVivoGames(String value) {

        String tmp = String.format(CasinoPageUI.dynamicVivoGame, value);
        return getListElements(driver, By.xpath(tmp));

    }

    public void selectHoGamingCategory(String category) {

        String tmp = String.format(CasinoPageUI.dynamicHoGamingCategory, category);
        clickToElementByJSByLocator(driver, By.xpath(tmp));
        try {
            Thread.sleep(1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public List<WebElement> getHoGamingGames() {

        waitForElementVisibleByLocator(driver, CasinoPageUI.hoGamingOpeningLocator);
        return getListElements(driver, CasinoPageUI.hoGamingOpeningLocator);

    }

    public void waitForEzugiGame() {

        for (int i = 1; i <= 20; i++) {
            WebElement icon = getElement(driver, CasinoPageUI.ezugiLoadingLocator);
            if (verifyIconDisplayed(i, icon)) break;
        }
        for (int i = 1; i <= 20; i++) {
            List<WebElement> lobbies = getListElements(driver, CasinoPageUI.ezugiGamesLocator);
            if (lobbies.size() == 0) {
                System.out.println("Wait for Ezugi lobby... " + i * 3 + "s");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Ezugi lobby complete\n");
                break;
            }
            Assert.assertTrue(i < 20);
        }

    }

    private boolean verifyIconDisplayed(int i, WebElement icon) {
        if (icon.isDisplayed()) {
            System.out.println("Now loading... " + i * 3 + "s");
            try {
                highlightElement(driver, icon);
                Thread.sleep(3000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Loading complete\n");
            return true;
        }
        Assert.assertTrue(i < 20);
        return false;
    }

    public void waitForCasinoGame(By xPathLocator1, By xPathLocator2) {
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            List<WebElement> icons = getListElements(driver, xPathLocator1);
            if (icons.size() > 0) {
                System.out.println("Now loading... " + i * 3 + "s");
                try {
                    highlightElement(driver, icons.get(0));
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Loading complete\n");
                break;
            }
            Assert.assertTrue(i < 20);
        }
        for (int i = 1; i <= 20; i++) {
            List<WebElement> lobbies = getListElements(driver, xPathLocator2);
            if (lobbies.size() == 0) {
                System.out.println("Wait for casino lobby... " + i * 3 + "s");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
                highlightElement(driver, lobbies.get(0));
                System.out.println("Casino lobby completes\n");
                break;
            }
            try {
                Thread.sleep(3 * 1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            Assert.assertTrue(i < 20);
        }
    }

    public void logoutToHomePage() {
        logout(driver);

    }

}
