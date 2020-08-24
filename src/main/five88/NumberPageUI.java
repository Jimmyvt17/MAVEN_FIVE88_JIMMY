package five88;

import org.openqa.selenium.By;

public class NumberPageUI {

    public static By balanceLocator = By.xpath("//span[@class='number balance']");
    public static By betInputLocator = By.xpath("//div[@id='betProcessContainer']//input[@id='betslipStake']");
    public static By betContentLocator = By.xpath("//div[@id='betProcessContainer']//div[@class='betDetail']");
    public static By betSubmitLocator = By.xpath("//div[@id='betProcessContainer']//button[@type='submit']");
    public static By warningCloseLocator = By.xpath("//div[@class='modal-content']//button");
    public static By ticketContentLocator = By.xpath("//div[@class='betListContainer']//div[@class='scrollRunning']//div[@class='betDetail']");

    public static String dynamicBetTime = "//div[@id='%s']//span[@class='bet-time accent']";
    public static String dynamicBetPoint = "//div[@id='%s']//div[@class='betArea']//div[@class='numberBall-primary']";

}
