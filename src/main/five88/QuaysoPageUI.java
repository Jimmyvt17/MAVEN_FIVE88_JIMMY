package five88;

import org.openqa.selenium.By;

public class QuaysoPageUI {

    public static By quaysoMenuLocator = By.xpath("//ul[@class='nav navbar-nav']//ul[@class='dropdown']//a[text()='Quay Số 1']");
    public static By quaysoBalanceLocator = By.xpath("//div[@class='appHeader-user']//div[@class='appHeader-userInfo']//span[@class='roundCornerBox roundCornerBox-appHeader-web ah-balanceIndicator']");
    public static By betQuaysoButtonLocator = By.xpath("//div[@class='place-ticket-container']//button[@class='betButton']");

    public static String dynamicGameOdd = "//div[@style='order: %s;']//div[@class='playbox-option']";
    public static String dynamicQuaysoGame = "//div[@style='order: %s;']";
    public static String dynamicBetStart = "//div[@style='order: %s;']//div[@class='productHeader']//div[@class='productHeader-rightarea']//div[contains(text(), 'Bắt đầu cá cược')]";
    public static String dynamicBetTime = "//div[@style='order: %s;']//div[@class='productHeader']//div[@class='productHeader-rightarea']//div[@class='productHeader-space']";
    public static String dynamicBetMoney = "//div[@class='place-ticket-container']//button[@value='%s']";

}
