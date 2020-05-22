package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.CasinoPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CasinoPageObject extends AbstractPage {

    private WebDriver driver;

    public CasinoPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void loginCasinoAccount() {

        login(driver, Constants.USERNAME_CASINO, Constants.PASSWORD);
        try {
            Thread.sleep(5*1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void openCasinoPage() {

        openSubMenu(driver, "icon-mcasino");

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

    public void openSexyCasino() {

        waitForElementVisibleByLocator(driver, CasinoPageUI.sexyCasinoLocator);
        clickToElementByJSByLocator(driver, CasinoPageUI.sexyCasinoLocator);

    }

    public List<WebElement> getGameBanners() {

        return getListElements(driver, CasinoPageUI.bannerCasinoLocator);

    }

    public void openCasinoLobby(WebElement element) {

        waitForElementVisible(driver, element);
        String casinoUrl = element.getAttribute("href");
        openAnyUrl(driver, casinoUrl);

    }

    public void waitForVivoLobbyLoadCompleted() {

        boolean i = true;
        while (i) {
            List<WebElement> icon = driver.findElements(CasinoPageUI.vivoVideoLocator);
            int noIcon = icon.size();
            if (noIcon==0) {
                System.out.println("Now loading...");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                i = true;

            } else {
                highlightElement(driver, icon.get(0));
                System.out.println("Loading complete\n");
                i = false;

            }

        }

    }

    public void waitForSexyCasino() {

        boolean i = true;
        while (i) {
            WebElement icon = driver.findElement(CasinoPageUI.logoLiveSexyCasinoLocator);
            if (icon.isDisplayed()) {
                highlightElement(driver, icon);
                System.out.println("Now loading...");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                i = true;

            } else {
                System.out.println("Loading complete");
                i = false;

            }

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


    public void waitForHGLobbyLoadCompleted() {

        boolean i = true;
        while (i) {
            List<WebElement> icon = driver.findElements(CasinoPageUI.hoGamingLoadingLocator);
            int noIcon = icon.size();
            if (noIcon>0) {
                highlightElement(driver, icon.get(0));
                System.out.println("Now loading...");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                i = true;

            } else {
                System.out.println("Loading complete");
                i = false;

            }

        }
        waitForElementVisibleByLocator(driver, CasinoPageUI.hoGamingLobbyMenuLocator);

    }


    public void selectHoGamingCategory(String category) {

        String tmp = String.format(CasinoPageUI.dynamicHoGamingCategory, category);
        doubleClickToElementByLocator(driver, By.xpath(tmp));
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

    public void waitForEBetLobby() {

        boolean i = true;
        while (i) {
            List<WebElement> icon = driver.findElements(CasinoPageUI.eBetLoadingPageLocator);
            int noIcon = icon.size();
            if (noIcon>0) {
                highlightElement(driver, icon.get(0));
                System.out.println("Now loading...");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                i = true;

            } else {
                System.out.println("Loading complete");
                i = false;

            }

        }
        waitForElementVisibleByLocator(driver, CasinoPageUI.eBetLobbyLocator);

    }

    public void waitForEzugiGame() {

        boolean i = true;
        while (i) {
            WebElement icon = driver.findElement(CasinoPageUI.ezugiLoadingLocator);
            if (icon.isDisplayed()) {
                highlightElement(driver, icon);
                System.out.println("Now loading...");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                i = true;

            } else {
                System.out.println("Loading complete");
                i = false;

            }

        }
        waitForElementVisibleByLocator(driver, CasinoPageUI.ezugiHeaderLocator);
        try {
            Thread.sleep(3000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
