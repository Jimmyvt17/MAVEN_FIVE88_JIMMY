package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AccountPageUI;
import five88.JackpotPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JackpotPageObject extends AbstractPage {

    private WebDriver driver;

    public JackpotPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void loginTestSlot(String username) {

        login(driver, username, "@16WINner");

    }

    public void openJackpotPage() {

        openSubMenu(driver, "icon_slots_game");

    }

    public void openJackpotGame(String value) {

        String tmp = String.format(JackpotPageUI.dynamicJackpotGame, value);
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

}



