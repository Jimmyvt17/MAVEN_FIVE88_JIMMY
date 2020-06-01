package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.*;

public class PageFactoryManager {

    public static HomePageObject getHomePage(WebDriver driver) {

        return new HomePageObject(driver);

    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {

        return new RegisterPageObject(driver);

    }

    public static AccountPageObject getAccountPage(WebDriver driver) {

        return new AccountPageObject(driver);

    }

    public static CasinoPageObject getCasinoPage(WebDriver driver) {

        return new CasinoPageObject(driver);

    }

    public static LodePageObject getLodePage(WebDriver driver) {

        return new LodePageObject(driver);

    }

    public static QuaysoPageObject getQuaysoPage(WebDriver driver) {

        return new QuaysoPageObject(driver);

    }

    public static ASportPageObject getASportPage(WebDriver driver) {

        return new ASportPageObject(driver);

    }

    public static TSportPageObject getTSportPage(WebDriver driver) {

        return new TSportPageObject(driver);

    }

    public static JackpotPageObject getJackpotPage(WebDriver driver) {

        return new JackpotPageObject(driver);

    }

    public static NumberPageObject getNumberPage(WebDriver driver) {

        return new NumberPageObject(driver);

    }

    public static KenoPageObject getKenoPage(WebDriver driver) {

        return new KenoPageObject(driver);

    }

}
