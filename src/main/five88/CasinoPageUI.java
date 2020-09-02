package five88;

import org.openqa.selenium.By;

public class CasinoPageUI {

    public static By casinoProviderLocator = By.xpath("//div[@class='custom-select__trigger']");
    public static By sexyCasinoLocator = By.xpath("//a[@id='urlSexyCasino']");
    public static By bannerCasinoLocator = By.xpath("//li[@class='item']//div[@class='item_game-bottom']//a");
    public static By logoLiveSexyCasinoLocator = By.xpath("//div[@id='loader']//div[@class='foreground']//div[@class='icon']");
    public static By vivoVideoLocator = By.xpath("//div[@id='container']//div");
    public static By vivoGameToolBarLocator = By.xpath("//div[@id='main']//div[@id='toolbar']");

    public static By hoGamingLoadingLocator = By.xpath("//div[@class='loading v4OperatorColor ng-star-inserted']");
    public static By hoGamingLobbyMenuLocator = By.xpath("//div[@class='ffooter_menu_nav_lobby']");
    public static By hoGamingOpeningLocator = By.xpath("//div[@class='button enabled']//span[text()='CHƠI']");

    public static By eBetLoadingPageLocator = By.xpath("//div[@id='loadingUi']");
    public static By eBetLobbyLocator = By.xpath("//div[@id='gameMainDiv']//canvas");

    public static By ezugiLoadingLocator = By.xpath("//div[@class='preloader__preloader_top___2oRZV']");
    public static By ezugiGamesLocator = By.xpath("//div[@class='category_page__wrapper___cimJA']");

    public static By evoLoadingFinishLocator = By.xpath("//div[@class='loading-screen-desktop finish-progress']");

    public static String dynamicCasinoProvider = "//div[@class='custom-options']//span[@data-filtergame='%s']";
    public static String dynamicVivoCategory = "//li[@id='%s']//span";
    public static String dynamicVivoGame = "//div[@class='%s']//video";
    public static String dynamicHoGamingCategory = "//div[@class='LobbynavBar ps']//span[text()='%s']";

}
