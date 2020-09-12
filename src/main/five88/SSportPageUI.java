package five88;

import org.openqa.selenium.By;

public class SSportPageUI {

    public static By modeUILocator = By.xpath("//span[@class='page-header-custom-link-text']");
    public static By betAsiaSSportLocator = By.xpath("//div[@class='rj-asian-events__odd-line']//div[@data-uat='bet-odds-value']");
    public static By contentOrderLocator = By.xpath("//strong[@data-uat='bet-slip-selection-name']");
    public static By oddSelectedLocator = By.xpath("//label[@data-uat='bet-slip-selection-odds']");
    public static By inputBetSSportMoney = By.xpath("//input[@class='stakebox singleDepositField']");
    public static By totalReturnLocator = By.xpath("//span[@data-uat='bet-slip-total-return-value']");
    public static By betConfirmSSportLocator = By.xpath("//button[@id='PlaceBetButton']");
    public static By ticketOKSSportLocator = By.xpath("//div[contains(text(),'Đặt cược được chấp nhận')]");
    public static By notEnoughLocator = By.xpath("//span[text()='Số dư không đủ']");

    public static By betEuroStreamingVideoLocator = By.xpath("//span[@class='icon i-24-16 event-icons-icon i-o-ev-streaming']");
    public static By streamingVideoLocator = By.xpath("//div[@id='iFrameContainer-live-betting']//iframe[@src]");

}
