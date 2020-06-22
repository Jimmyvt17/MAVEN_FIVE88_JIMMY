package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.CasinoPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            List<WebElement> icon = driver.findElements(CasinoPageUI.vivoVideoLocator);
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
            WebElement icon = driver.findElement(CasinoPageUI.logoLiveSexyCasinoLocator);
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
                break;
            }
            Assert.assertTrue(i < 20);
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

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            List<WebElement> icon = driver.findElements(CasinoPageUI.hoGamingLoadingLocator);
            int noIcon = icon.size();
            if (noIcon>0) {
                System.out.println("Now loading... " + i * 3 + "s");
                try {
                    highlightElement(driver, icon.get(0));
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
        waitForElementVisibleByLocator(driver, CasinoPageUI.hoGamingLobbyMenuLocator);

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

    public void waitForEBetLobby() {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            List<WebElement> icon = driver.findElements(CasinoPageUI.eBetLoadingPageLocator);
            if (icon.size() > 0) {
                System.out.println("Now loading... " + i * 3 + "s");
                try {
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
            List<WebElement> lobbies = driver.findElements(CasinoPageUI.eBetLobbyLocator);
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

    public void waitForEzugiGame() {

        for (int i = 1; i <= 20; i++) {
            WebElement icon = driver.findElement(CasinoPageUI.ezugiLoadingLocator);
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
                break;
            }
            Assert.assertTrue(i < 20);
        }
        for (int i = 1; i <= 20; i++) {
            List<WebElement> lobbies = driver.findElements(CasinoPageUI.ezugiHeaderLocator);
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

    public void waitForCasinoGame(By xPathLocator1, By xPathLocator2) {
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        for (int i = 1; i <= 20; i++) {
            List<WebElement> icons = driver.findElements(xPathLocator1);
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
            List<WebElement> lobbies = driver.findElements(xPathLocator2);
            if (lobbies.size() == 0) {
                System.out.println("Wait for casino lobby... " + i * 3 + "s");
                try {
                    Thread.sleep(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
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

}
