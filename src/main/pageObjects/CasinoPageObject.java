package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class CasinoPageObject extends AbstractPage {

    private WebDriver driver;

    public CasinoPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

}
