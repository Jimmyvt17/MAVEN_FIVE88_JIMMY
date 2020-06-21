package commons;

import five88.AbstractPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import pageObjects.AccountPageObject;
import pageObjects.HomePageObject;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AbstractPage {

    WebElement element;
    List<WebElement> elements;
    JavascriptExecutor js;
    WebDriverWait waitExplicit;
    Actions action;

    // WebDriver
    public void openAnyUrl(WebDriver driver, String url) {

        driver.get(url);

    }

    public String getCurrentPageUrl(WebDriver driver) {

        return driver.getCurrentUrl();

    }

    public String getPageTitle(WebDriver driver) {

        return driver.getTitle();

    }

    public String getPageSourceCode(WebDriver driver) {

        return driver.getPageSource();

    }

    public void backToPreviousPage(WebDriver driver) {

        driver.navigate().back();

    }

    public void refreshCurrentPage(WebDriver driver) {

        driver.navigate().refresh();

    }

    public void forwardToNextPage(WebDriver driver) {

        driver.navigate().forward();

    }

    public void acceptAlert(WebDriver driver) {

        driver.switchTo().alert().accept();

    }

    public void cancelAlert(WebDriver driver) {

        driver.switchTo().alert().dismiss();

    }

    public String getTextAlert(WebDriver driver) {

        return driver.switchTo().alert().getText();

    }

    public void sendKeyToAlert(WebDriver driver, String value) {

        driver.switchTo().alert().sendKeys(value);

    }

    // WebElement
    public void clickToElementByLocator(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        element.click();

    }

    public void clickToElement(WebDriver driver, WebElement element) {

        element.click();

    }

    public void clearTextElement(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        element.clear();

    }

    public void sendKeyToElement(WebDriver driver, By xPathLocator, String value) {

        element = driver.findElement(xPathLocator);
        element.clear();
        element.sendKeys(value);

    }

    public void sendKeyToElement(WebDriver driver, String xPath, String value, String... values) {

        xPath = String.format(xPath, (Object[]) values);
        element = driver.findElement(By.xpath(xPath));
        element.clear();
        element.sendKeys(value);

    }

    public void selectItemInDropDown(WebDriver driver, By xPathLocator, String value) {

        element = driver.findElement(xPathLocator);
        Select select = new Select(element);
        select.selectByVisibleText(value);

    }

    public String getSelectedItemInDropDown(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();

    }

    public void selectCustomDropdownList(WebDriver driver, By parentXpathLocator, By allItemXpathLocator, String valueExpected) throws Exception {
        WebElement parent = driver.findElement(parentXpathLocator);
        js.executeScript("arguments[0].click();", parent);

        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpathLocator));

        List<WebElement> allItems = driver.findElements(allItemXpathLocator);

        for (WebElement child : allItems) {

            if (child.getText().equals(valueExpected)) {

                js.executeScript("arguments[0].scrollIntoView(true);", child);
                Thread.sleep(1000);

                if (child.isDisplayed()) {

                    child.click();

                } else {
                    // JS click
                    js.executeScript("argument[0].click();", child);
                }
                Thread.sleep(1000);
                break;
            }

        }

    }

    public String getAttributeValue(WebDriver driver, By xPathLocator, String attributeName) {

        element = driver.findElement(xPathLocator);
        return element.getAttribute(attributeName);

    }

    public String getTextElementByLocator(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        return element.getText();

    }

    public String getTextElement(WebDriver driver, WebElement element) {

        waitForElementVisible(driver, element);
        return element.getText();

    }

    public Integer getElementAsInt(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        String value = element.getText().replace(".", "");
        return NumberUtils.toInt(value);

    }

    public Integer getSizeElements(WebDriver driver, By xPathLocator) {

        elements = driver.findElements(xPathLocator);
        return elements.size();

    }

    public List<WebElement> getListElements(WebDriver driver, By xPathLocator) {

        elements = driver.findElements(xPathLocator);
        return elements;

    }

    public int countElementsNumber(WebDriver driver, By xPathLocator) {

        elements = driver.findElements(xPathLocator);
        return elements.size();

    }

    public void checkTheCheckbox(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        if (!element.isSelected()) {

            element.click();

        }

    }

    public void unCheckToCheckbox(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        if (element.isSelected()) {

            element.click();

        }

    }

    public boolean isControlDisplayed(WebDriver driver, By xPathLocator) {

        boolean status = true;
        try {
            element = driver.findElement(xPathLocator);
            if (element.isDisplayed()) {
                return status;
            }
        } catch (Exception ex) {
            Reporter.log("====================================== Element Not Visible ======================================");
            Reporter.log(ex.getMessage());
            System.err.println("====================================== Element Not Visible ======================================");
            System.err.println(ex.getMessage() + "\n");
            status = false;
        }
        return status;

    }

    public boolean isControlUndisplayed(WebDriver driver, By xPathLocator) {

        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        List<WebElement> elements = driver.findElements(xPathLocator);

        if(elements.size() == 0) {
            overrideTimeout(driver, Constants.LONG_TIMEOUT);
            return true;
        } else if(!elements.get(0).isDisplayed()) {
            overrideTimeout(driver, Constants.LONG_TIMEOUT);
            return true;
        } else {
            overrideTimeout(driver, Constants.LONG_TIMEOUT);
            return false;
        }

    }

    public boolean isControlSelected(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        return element.isSelected();

    }

    public boolean isControlEnabled(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        return element.isEnabled();

    }

    public void switchToChildWindowByID(WebDriver driver, String parentID) {

        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {

            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();

            if (!currentWin.equals(parentID)) {

                driver.switchTo().window(runWindow);
                break;

            }

        }

    }

    public void switchToChildWindownByTitle(WebDriver driver, String expectedTitle) {

        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {

            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();

            if (currentWin.equals(expectedTitle)) {

                break;

            }

        }

    }

    public boolean closeAllWithoutParentWindows(WebDriver driver, String parentID) {

        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {

            if (!runWindow.equals(parentID)) {

                driver.switchTo().window(runWindow);
                driver.close();

            }

        }

        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;

    }

    public void switchToIframe(WebDriver driver) {

        waitForElementPresentByLocator(driver, By.tagName("iframe"));
        int size = getSizeElements(driver, By.tagName("iframe"));
        System.out.println("Total iframes --" + size);
        driver.switchTo().frame(0);
        System.out.println("Switch to outer iframe\n");

    }

    public void switchToIframes(WebDriver driver) {

        waitForElementPresentByLocator(driver, By.tagName("iframe"));
        int size = getSizeElements(driver, By.tagName("iframe"));
        System.out.println("Total iframes --" + size);
        driver.switchTo().frame(0);
        System.out.println("Switch to outer iframe");
        size = getSizeElements(driver, By.tagName("iframe"));
        System.out.println("Total  inner iframes --" + size);
        if (size > 0) {
            driver.switchTo().frame(0);
            System.out.println("Switch to inner iframe\n");

        }

    }


    public void backToTopWindow(WebDriver driver) {

        driver.switchTo().defaultContent();

    }

    public void hoverMouseToElement(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        action = new Actions(driver);
        action.moveToElement(element).perform();

    }

    public void doubleClickToElementByLocator(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        action = new Actions(driver);
        action.doubleClick(element).perform();

    }

    public void doubleClickToElement(WebDriver driver, WebElement element) {

        action = new Actions(driver);
        action.doubleClick(element).perform();

    }

    public void rightClickToElement(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        action = new Actions(driver);
        action.contextClick(element).perform();

    }

    public void dragAndDropElementToElement(WebDriver driver, By fromXpathLocator, By toXpathLocator) {

        WebElement from = driver.findElement(fromXpathLocator);
        WebElement to = driver.findElement(toXpathLocator);
        action = new Actions(driver);
        action.dragAndDrop(from, to).perform();

    }

    public void dragAndDropElement(WebDriver driver, By xPathLocator, int x, int y) {

        element = driver.findElement(xPathLocator);
        action = new Actions(driver);
        action.dragAndDropBy(element, x, y).build().perform();

    }

    public void sendKeyboardToElement(WebDriver driver, By xPathLocator, Keys key) {

        element = driver.findElement(xPathLocator);
        action = new Actions(driver);
        action.sendKeys(key);

    }

    public void uploadFile(WebDriver driver, By xPathLocator, String filePath) {

        element = driver.findElement(xPathLocator);
        element.sendKeys(filePath);

    }

    public void waitForElementPresentByLocator(WebDriver driver, By xPathLocator) {

        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        try {
            waitExplicit.until(ExpectedConditions.presenceOfElementLocated(xPathLocator));
        } catch (Exception ex) {
            Reporter.log("====================================== Wait For Element Not Visible ======================================");
            Reporter.log(ex.getMessage());
            System.err.println("====================================== Wait For Element Not Visible ======================================");
            System.err.println(ex.getMessage() + "\n");
        }
        highlightElementByLocator(driver, xPathLocator);

    }

    public void waitForElementVisibleByLocator(WebDriver driver, By xPathLocator) {

        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        try {
            waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(xPathLocator));
        } catch (Exception ex) {
            Reporter.log("====================================== Wait For Element Not Visible ======================================");
            Reporter.log(ex.getMessage());
            System.err.println("====================================== Wait For Element Not Visible ======================================");
            System.err.println(ex.getMessage() + "\n");
        }
        highlightElementByLocator(driver, xPathLocator);

    }

    public void waitForElementVisible(WebDriver driver, WebElement element) {

        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        try {
            waitExplicit.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            Reporter.log("====================================== Wait For Element Not Visible ======================================");
            Reporter.log(ex.getMessage());
            System.err.println("====================================== Wait For Element Not Visible ======================================");
            System.err.println(ex.getMessage() + "\n");
        }
        highlightElement(driver, element);

    }

    public void waitForElementClickable(WebDriver driver, By xPathLocator) {

        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        waitExplicit.until(ExpectedConditions.elementToBeClickable(xPathLocator));
        highlightElementByLocator(driver, xPathLocator);

    }

    public void waitForElementInvisible(WebDriver driver, By xPathLocator) {

        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(xPathLocator));
        overrideTimeout(driver, Constants.LONG_TIMEOUT);

    }

    public void overrideTimeout(WebDriver driver, long timeout) {

        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

    }

    public void waitForAlertPresence(WebDriver driver) {

        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.alertIsPresent());

    }

    public HomePageObject openHomePage(WebDriver driver) {

        waitForElementVisibleByLocator(driver, AbstractPageUI.logoutButtonLocator);
        clickToElementByJSByLocator(driver, AbstractPageUI.logoutButtonLocator);
        return PageFactoryManager.getHomePage(driver);

    }

    public void inputToUsernameTextBox(WebDriver driver, String username_test) {

        waitForElementVisibleByLocator(driver, AbstractPageUI.usernameLoginLocator);
        sendKeyToElement(driver, AbstractPageUI.usernameLoginLocator, username_test);

    }

    public void inputToPasswordTextBox(WebDriver driver, String password) {

        waitForElementVisibleByLocator(driver, AbstractPageUI.passwordLoginLocator);
        sendKeyToElement(driver, AbstractPageUI.passwordLoginLocator, password);

    }

    public AccountPageObject clickToLoginButton(WebDriver driver) {

        waitForElementVisibleByLocator(driver, AbstractPageUI.loginButtonLocator);
        clickToElementByJSByLocator(driver, AbstractPageUI.loginButtonLocator);
        return PageFactoryManager.getAccountPage(driver);

    }

    public void login(WebDriver driver, String username_test, String password) {

        waitForElementVisibleByLocator(driver, AbstractPageUI.usernameLoginLocator);
        sendKeyToElement(driver, AbstractPageUI.usernameLoginLocator, username_test);

        waitForElementVisibleByLocator(driver, AbstractPageUI.passwordLoginLocator);
        sendKeyToElement(driver, AbstractPageUI.passwordLoginLocator, password);

        waitForElementVisibleByLocator(driver, AbstractPageUI.loginButtonLocator);
        clickToElementByJSByLocator(driver, AbstractPageUI.loginButtonLocator);

    }

    public void logout(WebDriver driver) {

        scrollToTopPage(driver);
        waitForElementVisibleByLocator(driver, AbstractPageUI.logoutButtonLocator);
        clickToElementByJSByLocator(driver, AbstractPageUI.logoutButtonLocator);

        waitForElementVisibleByLocator(driver, AbstractPageUI.loginFormLocator);
        isControlDisplayed(driver, AbstractPageUI.loginFormLocator);

    }

    public void openSubMenu(WebDriver driver, String value) {

        String tmp = String.format(AbstractPageUI.dynamicSubMenu, value);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));

    }

    // Javascript
    public void highlightElementByLocator(WebDriver driver, By xPathLocator) {

        js = (JavascriptExecutor) driver;
        element = driver.findElement(xPathLocator);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 3px solid red; border-style: dashed;");
        try {
            Thread.sleep(500);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",originalStyle);

    }

    public void highlightElement(WebDriver driver, WebElement element) {

        js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 3px solid red; border-style: dashed;");
        try {
            Thread.sleep(500);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",originalStyle);

    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {

        js = (JavascriptExecutor) driver;
        return js.executeScript(javaScript);

    }

    public Object showTextByJS(WebDriver driver, By xPathLocator) {

        elements = driver.findElements(xPathLocator);
        if (elements.size() > 0) {
            js = (JavascriptExecutor) driver;
            return (String) js.executeScript("return arguments[0].textContent.toString()", elements.get(0));
        } else {
            return "-1";
        }

    }

    public Object clickToElementByJS(WebDriver driver, WebElement element) {

        highlightElement(driver, element);
        js = (JavascriptExecutor) driver;
        return  js.executeScript("arguments[0].click();", element);

    }

    public Object clickToElementByJSByLocator(WebDriver driver, By xPathLocator) {

        js = (JavascriptExecutor) driver;
        return  js.executeScript("arguments[0].click();", driver.findElement(xPathLocator));

    }

    public void sendKeyToElementByJS(WebDriver driver, By xPathLocator, String value) {

        element = driver.findElement(xPathLocator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);

    }

    public void removeAttributeInDOM(WebDriver driver, By xPathLocator, String attribute) {

        element = driver.findElement(xPathLocator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);

    }

    public void setAttributeInDOM(WebDriver driver, By xPathLocator, String attribute, String value) {

        element = driver.findElement(xPathLocator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "');", element);

    }

    public Object scrollToBottomPage(WebDriver driver) {

        js = (JavascriptExecutor) driver;
        return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

    }

    public Object scrollToTopPage(WebDriver driver) {

        js = (JavascriptExecutor) driver;
        return js.executeScript("window.scrollTo(0, 0)");

    }

    public void scrollToElementByLocator(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void scrollToElement(WebDriver driver, WebElement element) {

        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public Object navigateToUrlByJS(WebDriver driver, String url) {

        js = (JavascriptExecutor) driver;
        return js.executeScript("window.location = '" + url + "'");

    }

    public boolean isImageLoaded(WebDriver driver, By xPathLocator) {

        element = driver.findElement(xPathLocator);
        js = (JavascriptExecutor) driver;
        boolean status = (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
        if (status) {

            return true;

        } else {

            return false;

        }

    }

    public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {

        js = (JavascriptExecutor) driver;
        String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);

    }



}
