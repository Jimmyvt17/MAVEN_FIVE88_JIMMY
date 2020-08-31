package pageObjects;

import commons.AbstractPage;
import commons.Constants;
import five88.AbstractPageUI;
import five88.JackpotPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class JackpotPageObject extends AbstractPage {

    private final WebDriver driver;

    public JackpotPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

    public void loginTestSlot() {

        login(driver, Constants.USERNAME_LOGIN, Constants.PASSWORD);
        try {
            Thread.sleep(5 * 1000);
            Assert.assertTrue(isControlDisplayed(driver, AbstractPageUI.loggedInFormLocator));
        } catch (Throwable e) {
            throw new RuntimeException("Login not successful");
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



