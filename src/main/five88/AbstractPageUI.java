package five88;

import org.openqa.selenium.By;

public class AbstractPageUI {

    public static By loginFormLocator = By.xpath("//form[@name='form_login_header']");
    public static By usernameLoginLocator = By.xpath("//input[@id='login-username']");
    public static By passwordLoginLocator = By.xpath("//input[@id='login-password']");
    public static By loginButtonLocator = By.xpath("//form[@name='form_login_header']//button[@type='submit']");
    public static By logoutButtonLocator = By.xpath("//a[text()='THOÁT']");

    public static String dynamicSubMenu = "//ul[@class='nav navbar-nav']//a[text()='%s']";
    public static String dynamicSportButton = "//div[@class='%s']//div[@class='%s']/following-sibling::a";

}
