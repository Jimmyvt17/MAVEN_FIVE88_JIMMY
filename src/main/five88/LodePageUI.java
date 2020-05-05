package five88;

import org.openqa.selenium.By;

public class LodePageUI {

    public static By lodeBalanceLocator = By.xpath("//span[@class='user-money']");
    public static By numberListLocator = By.xpath("//div[@class='lo3so-table']//label[@class='input-num']//div[@class='checkmark']");
    public static By numberSelectLocator = By.xpath("//div[@class='phieudat-numbers']");
    public static By betLodeButtonLocator = By.xpath("//div[@class='phieudat-buttons']//a[@class='btn btn-datcuoc']");
    public static By okButtonLocator = By.xpath("//div[@class='modal-content']//footer[@class='modal-footer']//button[@class='btn btn-primary']");

    public static By lo3soLocator = By.xpath("//a[text()='Lô 3 số']");

}
