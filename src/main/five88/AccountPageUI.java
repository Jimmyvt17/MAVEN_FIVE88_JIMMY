package five88;

import org.openqa.selenium.By;

public class AccountPageUI {

    public static By loginNameLocator = By.xpath("//div[@class='acc_meta_group']//span[@class='acc_name']");
    public static By depositButtonLocator = By.xpath("//a[@class='btn btn-default nap-tien']");
    public static By depPromo158Locator = By.xpath("//select[@name='package_id']//option[@value='1']");
    public static By depositInformLocator = By.xpath("//div[@role='dialog']//div[@id='swal2-content']");
    public static By ticketStatusLocator = By.xpath("//table[@class='table-history']//span[@class='st-3']");

    public static By userBalanceLocator = By.xpath("//div[@class='acc_meta_group']//span[@class='txt-balance-info']");
    public static By phoneConfirmWitLocator = By.xpath("//input[@name='phone']");

    public static By cardWithdrawLocator = By.xpath("//ul[@id='payment-nav-tabs']//a[@aria-controls='tab-withdraw-card']//i");
    public static By cardNoLocator = By.xpath("//input[@type='number']");
    public static By cardPhoneConfirmLocator = By.cssSelector("#phoneCardWD > div:nth-child(3) > div > input");
    public static By cardWitSubmitLocator = By.cssSelector("#phoneCardWD > div:nth-child(5) > div > button");

    public static String dynamicSubmitButton = "//form[@id='%s']//button[@type='submit']";
    public static String dynamicTextBox = "//input[@id='%s']";
    public static String dynamicSelectDropBox = "//select[@id='%s']//option[@value='%s']";
    public static String dynamicSubAccount = "//ul[@class='panel-tabLink']//a[text()='%s']";
    public static String dynamicTicketStatus = "//td[text()='%s']/following-sibling::td/span[text()='%s']";
    public static String dynamicDepositError = "//div[@id='%s']";

}
