package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.JackpotPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JackpotPageObject extends AbstractPage {

    private final WebDriver driver;

    public JackpotPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void loginTestSlot() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);
        try {
            Thread.sleep(1000*5);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void openJackpotPage() {

        openSubMenu(driver, "Nổ hũ ");

    }

    public void openJackpotGame() {
        List<WebElement> noGame = getListElements(driver, JackpotPageUI.jackpotGame);
        clickToElementByJS(driver, noGame.get(randomNumber(noGame.size())));

    }

}



