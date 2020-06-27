package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.KenoPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class KenoPageObject extends AbstractPage {

    private WebDriver driver;

    public KenoPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }


    public void loginKenoAccount() {

        login(driver, Constants.USERNAME_KENO, Constants.PASSWORD);
        try {
            Thread.sleep(5*1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void openKenoPage() {

        openSubMenu(driver, "Keno");

    }

    public void switchToKenoIframe(String fileName) {

        //switchToIframe(driver, Constants.windowsFilePath, Constants.loadingTimeFile, "Keno");
        getIframeLoadingTime(driver, KenoPageUI.balanceLocator, fileName, "Keno");

    }

    public void quitKenoIframe() {

        backToTopWindow(driver);

    }

    public void logoutToHomePage() {

        logout(driver);

    }

    public boolean isBetTimePresent(String value) {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        String tmp = String.format(KenoPageUI.dynamicBetTime, value);
//        List<WebElement> startList = getListElements(driver, By.xpath(tmp));
//        System.out.println("The number of " + value + " = " + startList.size());
//        System.out.println(tmp);
//        if (startList.size() > 0) {
//            scrollToKenoGame(value);
//            Integer remainTime = getBetTimeCountDown(value);
//            if (remainTime >= 10) {
//                System.out.println("Check for game id = " + value + "\n");
//                System.out.println("Remaining time = " + remainTime + "\n");
//                return true;
//            } else {
//                System.out.println("Check for game id = " + value + "\n");
//                System.out.println("Remaining time = " + remainTime + "\n");
//                return false;
//            }
//        } else {
//            System.out.println("Check for game id = " + value + "\n");
//            return false;
//        }
        String betTime = getTextElementByLocator(driver, By.xpath(tmp));
        Integer remainTime = NumberUtils.toInt(betTime.replace("0:", ""));
        if (remainTime >= 10) {
            scrollToKenoGame(value);
            System.out.println("Check for game id = " + value + "\n");
            System.out.println("Remaining time = " + remainTime + "\n");
            return true;
        } else {
            scrollToKenoGame(value);
            System.out.println("Check for game id = " + value + "\n");
            System.out.println("Remaining time = " + remainTime + "\n");
            return false;
        }

    }

    public void scrollToKenoGame(String value) {

        String tmp = String.format(KenoPageUI.dynamicBetTime, value);
        scrollToElementByLocator(driver, By.xpath(tmp));

    }

    public Integer getBetTimeCountDown(String value) {

        String tmp = String.format(KenoPageUI.dynamicBetTime, value);
        String betTime = getTextElementByLocator(driver, By.xpath(tmp));
        return NumberUtils.toInt(betTime.replace("0:", ""));

    }

    public String getBalance() {

        waitForElementVisibleByLocator(driver, KenoPageUI.balanceLocator);
        return getTextElementByLocator(driver, KenoPageUI.balanceLocator);

    }

    public List<WebElement> getBet(String value) {

        String tmp = String.format(KenoPageUI.dynamicBetPoint, value);
        return getListElements(driver, By.xpath(tmp));

    }

    public void openBetPanel(WebElement element) {

        clickToElementByJS(driver, element);

    }

    public void selectMoneyToBet(String bet_money) {

        String tmp = String.format(KenoPageUI.dynamicBetMoney, bet_money);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    public void clickBetButton() {

        waitForElementVisibleByLocator(driver, KenoPageUI.betButtonLocator);
        clickToElementByJSByLocator(driver, KenoPageUI.betButtonLocator);

    }

}



