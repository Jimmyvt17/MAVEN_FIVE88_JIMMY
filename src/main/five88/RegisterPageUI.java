package five88;

import org.openqa.selenium.By;

public class RegisterPageUI {

    public static By usernameResgisLocator = By.xpath("//div[@class='col-xs-6']//input[@name='username']");
    public static By usernameRegisError = By.xpath("//form[@id='frmRegister']//div[@id='username-error']");
    public static By passwordRegisLocator = By.xpath("//div[@class='col-xs-6']//input[@name='password']");
    public static By passwordRegisError = By.xpath("//form[@id='frmRegister']//div[@id='password-error']");
    public static By phoneRegisLocator = By.xpath("//div[@class='col-xs-6']//input[@name='phone']");
    public static By phoneRegisError = By.xpath("//form[@id='frmRegister']//div[@id='phone-error']");
    public static By submitButtonLocator = By.xpath("//div[@class='col-xs-6']//button[@type='submit']");

}
