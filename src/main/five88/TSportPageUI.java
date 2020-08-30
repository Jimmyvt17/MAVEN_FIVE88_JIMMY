package five88;

import org.openqa.selenium.By;

public class TSportPageUI {

    public static By tSportBalanceLocator = By.xpath("//div[text()='Số tiền khả dụng']/following-sibling::div");
    public static By betTSportLocator = By.xpath("//div[@class='odd-item ']//div");
    public static By betLiveTSportLocator = By.xpath("//div[@id='stg-live']//div[@class='odd-item ']//div");
    public static By minBetTSportLocator = By.xpath("//div[text()='Mức cược tối thiểu']");
    public static By inputBetMoneyLocator = By.xpath("//bet-slip-component[@data-radium='true']//input[@type='tel']");
    public static By betButtonLocator = By.xpath("//bet-slip-component[@data-radium='true']//div[text()='Đặt cược ngay']");
    public static By betConfirmTSportLocator = By.xpath("//div[@id='yes-betting-btn']");
    public static By ticketDetailTSportLocator = By.xpath("//bet-item-component//div[@style='border-left: 3px solid rgb(58, 128, 181); padding-left: 8px;']");
    public static By betSuccessfulTSportLocator = By.xpath("//div[text()='Cược đặt thành công']");
    public static By betOrderLocator = By.xpath("//quick-bet-component[@data-radium='true']//div[@style='border-left: 3px solid rgb(235, 99, 7); padding-left: 8px;']");
    public static By betPanelLocator = By.xpath("//quick-bet-component");
    public static By betTicketLocator = By.xpath("//div[@id='bet-slip']//div[@style='border-left: 3px solid rgb(235, 99, 7); padding-left: 8px;']");

}
