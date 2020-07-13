package five88;

import org.openqa.selenium.By;

public class Quayso2PageUI {

    public static By quayso2MenuLocator = By.xpath("//ul[@class='nav navbar-nav']//ul[@class='dropdown']//a");
    public static By quayso2BalanceLocator = By.xpath("//div[@class='tool-content']//span[@class='username']/following-sibling::span");
    public static By betQuayso2ButtonLocator = By.xpath("//div[@class='btn-confirm']");

    public static String dynamicGameOdd = "//div[@style='order: %s;']//div[@class='name-bet']";
    public static String dynamicQuayso2Game = "//div[@style='order: %s;']";
    public static String dynamicBetStart = "//div[@style='order: %s;']//span[contains(text(), 'Bắt đầu cá cược')]";
    public static String dynamicBetTime = "//div[@style='order: %s;']//div[@class='time-border']//span[@class='timeout']";
    public static String dynamicBetMoney = "//div[@style='order: %s;']//div[@class='bet-chip']//span[@class='%s']";

}
