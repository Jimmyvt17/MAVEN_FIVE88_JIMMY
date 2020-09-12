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
    public static By oddPausedTSportLocator = By.xpath("//div[text()='Tỷ lệ cược tạm dừng. Vui lòng chọn tỷ lệ cược khác']");
    public static By warningTextLocator = By.xpath("//bet-slip-component[@data-radium='true']//div[@style='font-size: 14px; border: 1px solid rgb(247, 88, 49); border-radius: 3px; color: rgb(247, 88, 49); margin: 8px; padding: 10px; background: rgb(243, 211, 203); font-weight: bold; box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 3px 0px, rgba(0, 0, 0, 0.14) 0px 0px 1px 0px, rgba(0, 0, 0, 0.12) 0px 2px 1px -1px;']");
    public static By betOrderLocator = By.xpath("//bet-slip-component[@data-radium='true']//div[@style='background: rgb(239, 224, 213); padding: 8px; border-radius: 3px; margin-bottom: 8px;']");
    public static By betMatchLocator = By.xpath("//bet-slip-component[@data-radium='true']//div[@style='text-align: left; padding-left: 8px; margin-bottom: 8px;']");
    public static By betTicketLocator = By.xpath("//div[@id='bet-slip']//div[@style='border-left: 3px solid rgb(235, 99, 7); padding-left: 8px;']");

}
