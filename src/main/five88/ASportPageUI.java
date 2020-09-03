package five88;

import org.openqa.selenium.By;

public class ASportPageUI {

    public static By aSportBalanceLocator = By.xpath("//div[@class='widgetPanel personalAccount ']//div[@class='creditArea']//div[@class='infoItem']");
    public static By betASportLocator = By.xpath("//div[@class='betArea']//div[@style='cursor: pointer;']//span");
    public static By betConfirmASportLocator = By.xpath("//div[@class='quickBetPanel']//div[@id='singleQuickBetConfirm']//div[text()='Có']");
    public static By betOKASportLocator = By.xpath("//div[@id='popupPanel']//div[@title='OK']");
    public static By warningLocator = By.xpath("//div[@class='quickBetPanel']//span[text()='Rất tiếc, giá trị đặt cược của bạn lớn hơn Quỹ Tiền Hiện Có']");
    public static By betBoardASportLocator = By.xpath("//span[text()='Đang diễn ra']");
    public static By ticketOKASportLocator = By.xpath("//div[text()='Đang diễn ra']");
    public static By betOrderLocator = By.xpath("//div[@class='quickBetPanel']//div[@class='betDetial']");
    public static By betTicketLocator = By.xpath("//div[@class='betlist__content']//div[@class='betDetial']");

    public static String dynamicBetMoney = "//div[@class='quickBetPanel']//div[@title='%s']";

}
