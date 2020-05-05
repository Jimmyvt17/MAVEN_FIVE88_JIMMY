package five88;

import org.openqa.selenium.By;

public class AccountPageUI {

    public static By loginNameLocator = By.xpath("//div[@class='acc_meta_group']//span[@class='acc_name']");
    public static By depositButtonLocator = By.xpath("//a[@class='btn btn-default nap-tien']");
    public static By depPromo158Locator = By.xpath("//select[@name='package_id']//option[@value='1']");
    public static By depositInformLocator = By.xpath("//div[@role='dialog']//div[@id='swal2-content']");
    public static By depositCloseButtonLocator = By.xpath("//div[@class='swal2-header']//button");
    public static By ticketStatusLocator = By.xpath("//table[@class='table-history']//span[@class='st-3']");

    public static By depBankErrorLocator = By.xpath("//div[text()='Bạn chưa chọn ngân hàng']");
    public static By depMoneyErrorLocator = By.xpath("//div[text()='Số tiền nạp không hợp lệ']");
    public static By depCodeErrorLocator = By.xpath("//div[text()='Bạn chưa điền thông tin']");

    public static By userBalanceLocator = By.xpath("//div[@class='acc_meta_group']//span[@class='txt-balance-info']");
    public static By phoneConfirmWitLocator = By.xpath("//input[@name='phone']");

    public static By cardWithdrawLocator = By.xpath("//ul[@id='payment-nav-tabs']//a[@aria-controls='tab-withdraw-card']");
    public static By cardNoLocator = By.xpath("//input[@type='number']");
    public static By cardPhoneConfirmLocator = By.cssSelector("#phoneCardWD > div:nth-child(3) > div > input");
    public static By cardWitSubmitLocator = By.cssSelector("#phoneCardWD > div:nth-child(5) > div > button");

    public static String dynamicSubmitButton = "//form[@id='%s']//button[@type='submit']";
    public static String dynamicTextbox = "//input[@id='%s']";
    public static String dynamicSelect = "//select[@id='%s']//option[@value='%s']";
    public static String dynamicSubAccount = "//ul[@class='panel-tabLink']//a[text()='%s']";
    public static String ticketStatus = "//td[text()='%s']/following-sibling::td/span[text()='%s']";

}
