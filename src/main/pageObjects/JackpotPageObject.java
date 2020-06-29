package pageObjects;

import commons.AbstractPage;
import five88.JackpotPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JackpotPageObject extends AbstractPage {

    private final WebDriver driver;

    public JackpotPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void loginTestSlot(String username) {

        login(driver, username, "@16WINner");
        try {
            Thread.sleep(1000*5);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void openJackpotPage() {

        openSubMenu(driver, "Nổ hũ");

    }

    public void openJackpotGame(String value) {

        String tmp = String.format(JackpotPageUI.dynamicJackpotGame, value);
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

}



