package five88;

import org.openqa.selenium.By;

public class KenoPageUI {

    public static By balanceLocator = By.xpath("//span[@class='user-ballance']");
    public static By betPanelLocator = By.xpath("//div[@class='popper']");
    public static By betButtonLocator = By.xpath("//div[@class='popper']//div[@class='inputs']//button[@type='submit']");

    public static String dynamicBetStart = "//div[@id='%s']//div[@class='time-betting box-timer']//span[text()='Bắt đầu cá cược ']";
    public static String dynamicBetTime = "//div[@id='%s']//div[@class='time-betting box-timer']//span[text()='Bắt đầu cá cược ']/following-sibling::span[@class='countdown kn-timer']";
    public static String dynamicBetPoint = "//div[@id='%s']//div[@class='game-odds']//div[@class='div_cell']//span[@class='oddsName']";
    public static String dynamicBetMoney = "//div[@class='popper']//div[@class='div_cell cell ']//a[@title='%s']";

}
