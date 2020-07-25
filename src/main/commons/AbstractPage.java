package commons;

import commons.excelConfigs.ReadExcelFile;
import commons.excelConfigs.WriteExcelFile;
import five88.AbstractPageUI;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import pageObjects.AccountPageObject;
import pageObjects.HomePageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AbstractPage {

    WebElement element;
    List<WebElement> elements;
    JavascriptExecutor js;
    WebDriverWait waitExplicit;
    Actions action;
    Instant a, b;
    LocalDateTime x;

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
        element = getElement(driver, xPathLocator);
        element.click();

    }

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();

    }

    public void clearTextElement(WebDriver driver, By xPathLocator) {
        element = getElement(driver, xPathLocator);
        element.clear();

    }

    public void sendKeyToElement(WebDriver driver, By xPathLocator, String value) {
            element = getElement(driver, xPathLocator);
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

        element = getElement(driver, xPathLocator);
        Select select = new Select(element);
        select.selectByVisibleText(value);

    }

    public String getSelectedItemInDropDown(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
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

        element = getElement(driver, xPathLocator);
        return element.getAttribute(attributeName);

    }

    public String getTextElementByLocator(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        return element.getText();

    }

    public String getTextElement(WebDriver driver, WebElement element) {

        waitForElementVisible(driver, element);
        return element.getText();

    }

    public Integer getElementAsInt(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        String value = element.getText().replace(".", "");
        return NumberUtils.toInt(value);

    }

    public Integer getSizeElements(WebDriver driver, By xPathLocator) {

        elements = driver.findElements(xPathLocator);
        return elements.size();

    }

    public List<WebElement> getListElements(WebDriver driver, By xPathLocator) {
        try {
            elements = driver.findElements(xPathLocator);
        } catch (Throwable e) {
            if (e.toString().contains("StaleElementReferenceException")) {
                elements = driver.findElements(xPathLocator);
            }
        }
        return elements;

    }

    public WebElement getElement(WebDriver driver, By xPathLocator) {
        try {
            element = getElement(driver, xPathLocator);
        } catch (Throwable e) {
            if (e.toString().contains("StaleElementReferenceException")) {
                element = getElement(driver, xPathLocator);
            }
        }
        return element;

    }

    public int countElementsNumber(WebDriver driver, By xPathLocator) {

        elements = driver.findElements(xPathLocator);
        return elements.size();

    }

    public void checkTheCheckbox(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        if (!element.isSelected()) {

            element.click();

        }

    }

    public void unCheckToCheckbox(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        if (element.isSelected()) {

            element.click();

        }

    }

    public boolean isControlDisplayed(WebDriver driver, By xPathLocator) {

        boolean status = true;
        try {
            element = getElement(driver, xPathLocator);
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

        element = getElement(driver, xPathLocator);
        return element.isSelected();

    }

    public boolean isControlEnabled(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        return element.isEnabled();

    }

    public String getPageID(WebDriver driver) {
        return driver.getWindowHandle();

    }

    public void switchToChildWindowByID(WebDriver driver, String parentID) {

        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {

            driver.switchTo().window(runWindow);
            String currentWin = getPageID(driver);

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

    public void verifyIframeLoading(WebDriver driver, By xPathLocator) {

        try {
            switchToIframe(driver);
            List<WebElement> noElement = driver.findElements(xPathLocator);
            for (int i = 0; i <= 20; i++) {
                if (noElement.size() > 0) {
                    break;
                } else {
                    Assert.assertTrue(i < 20);
                    Thread.sleep(3 * 1000);
                }
            }
        } catch (Throwable e) {
            String exceptionText = e.toString();
            if (exceptionText.contains("StaleElementReferenceException")) {
                throw new RuntimeException(Constants.elementIsRemoved);
            } else if (exceptionText.contains("iframe")) {
                throw new RuntimeException(Constants.iframeNoLoad);
            } else {
                throw new RuntimeException(Constants.iframeContentError);
            }
        }

    }

    public void switchToIframes(WebDriver driver) {

        waitForElementPresentByLocator(driver, By.tagName("iframe"));
        int size = getSizeElements(driver, By.tagName("iframe"));
        System.out.println("Total iframes --" + size);
        driver.switchTo().frame(0);
        System.out.println("Switch to outer iframe");
        size = getSizeElements(driver, By.tagName("iframe"));
        System.out.println("Total  inner iframes --" + size);
        driver.switchTo().frame("sportsFrame");
        System.out.println("Switch to asport iframe\n");

    }

    public void verifyIframesLoading(WebDriver driver, By xPathLocator) {

        try {
            switchToIframes(driver);
            waitForElementVisibleByLocator(driver, xPathLocator);
        } catch (Throwable e) {
            String exceptionText = e.toString();
            if (exceptionText.contains("StaleElementReferenceException")) {
                throw new RuntimeException(Constants.elementIsRemoved);
            } else if (exceptionText.contains("iframe")) {
                throw new RuntimeException(Constants.iframeNoLoad);
            } else {
                throw new RuntimeException(Constants.iframeContentError);
            }
        }

    }


    public void backToTopWindow(WebDriver driver) {

        driver.switchTo().defaultContent();

    }

    public void hoverMouseToElement(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        action = new Actions(driver);
        action.moveToElement(element).perform();

    }

    public void doubleClickToElementByLocator(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
        action = new Actions(driver);
        action.doubleClick(element).perform();

    }

    public void doubleClickToElement(WebDriver driver, WebElement element) {

        action = new Actions(driver);
        action.doubleClick(element).perform();

    }

    public void rightClickToElement(WebDriver driver, By xPathLocator) {

        element = getElement(driver, xPathLocator);
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

        element = getElement(driver, xPathLocator);
        action = new Actions(driver);
        action.dragAndDropBy(element, x, y).build().perform();

    }

    public void sendKeyboardToElement(WebDriver driver, By xPathLocator, Keys key) {

        element = getElement(driver, xPathLocator);
        action = new Actions(driver);
        action.sendKeys(key);

    }

    public void uploadFile(WebDriver driver, By xPathLocator, String filePath) {

        element = getElement(driver, xPathLocator);
        element.sendKeys(filePath);

    }

    public void waitForElementPresentByLocator(WebDriver driver, By xPathLocator) {

        waitExplicit = new WebDriverWait(driver, Constants.MID_TIMEOUT);
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

        waitExplicit = new WebDriverWait(driver, Constants.MID_TIMEOUT);
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

        waitExplicit = new WebDriverWait(driver, Constants.MID_TIMEOUT);
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

        waitExplicit = new WebDriverWait(driver, Constants.MID_TIMEOUT);
        waitExplicit.until(ExpectedConditions.elementToBeClickable(xPathLocator));
        highlightElementByLocator(driver, xPathLocator);

    }

    public void waitForElementInvisible(WebDriver driver, By xPathLocator) {

        waitExplicit = new WebDriverWait(driver, Constants.MID_TIMEOUT);
        overrideTimeout(driver, Constants.SHORT_TIMEOUT);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(xPathLocator));
        overrideTimeout(driver, Constants.LONG_TIMEOUT);

    }

    public void overrideTimeout(WebDriver driver, long timeout) {

        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

    }

    public void openSportPage(WebDriver driver, String url, String... values) {

        openSubMenu(driver, "Thể thao");
        String tmp = String.format(AbstractPageUI.dynamicSportButton, values);
        waitForElementVisibleByLocator(driver, By.xpath(tmp));
        clickToElementByJSByLocator(driver, By.xpath(tmp));
        if (getCurrentPageUrl(driver).equals("https://five88.biz/bao-tri.aspx")) {
            throw new RuntimeException(Constants.pageIsMaintenance);
        }

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
        element = getElement(driver, xPathLocator);
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
        elements = getListElements(driver, xPathLocator);
        if (elements.size() > 0) {
            js = (JavascriptExecutor) driver;
            return (String) js.executeScript("return arguments[0].textContent.toString()", elements.get(0));
        } else {
            return "0";
        }

    }

    public Object clickToElementByJS(WebDriver driver, WebElement element) {

        highlightElement(driver, element);
        js = (JavascriptExecutor) driver;
        return  js.executeScript("arguments[0].click();", element);

    }

    public Object clickToElementByJSByLocator(WebDriver driver, By xPathLocator) {

        js = (JavascriptExecutor) driver;
        return  js.executeScript("arguments[0].click();", getElement(driver, xPathLocator));

    }

    public void sendKeyToElementByJS(WebDriver driver, By xPathLocator, String value) {

        element = getElement(driver, xPathLocator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);

    }

    public void removeAttributeInDOM(WebDriver driver, By xPathLocator, String attribute) {

        element = getElement(driver, xPathLocator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);

    }

    public void setAttributeInDOM(WebDriver driver, By xPathLocator, String attribute, String value) {

        element = getElement(driver, xPathLocator);
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

        element = getElement(driver, xPathLocator);
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

        element = getElement(driver, xPathLocator);
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

    // Excel
    public static void readExcel(String filePath,String fileName,String sheetName) throws IOException {

        //Create an object of File class to open xlsx file

        File file =   new File(filePath+"/"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook sampleWorkbook = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

            //If it is xlsx file then create object of XSSFWorkbook class

            sampleWorkbook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of HSSFWorkbook class

            sampleWorkbook = new HSSFWorkbook(inputStream);

        }

        //Read sheet inside the workbook by its name

        Sheet guru99Sheet = sampleWorkbook.getSheet(sheetName);

        //Find number of rows in excel file

        int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();

        //Create a loop over all the rows of excel file to read it

        for (int i = 0; i < rowCount+1; i++) {

            Row row = guru99Sheet.getRow(i);

            //Create a loop to print cell values in a row

            for (int j = 0; j < row.getLastCellNum(); j++) {

                //Print Excel data in console

                System.out.print(row.getCell(j).getStringCellValue()+"|| ");

            }

            System.out.println();
        }

    }

    public static void readFromExcelFile() throws IOException{

        //Create an object of ReadGuru99ExcelFile class

        ReadExcelFile objExcelFile = new ReadExcelFile();

        //Prepare the path of excel file

        String filePath = Constants.windowsFilePath;

        //Call read file method of the class to read data

        readExcel(filePath,Constants.loadingTimeFile,"Quayso");

    }

    public static void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite) throws IOException {

        //Create an object of File class to open xlsx file

        File file = new File(filePath + "/" + fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook sampleWorkbook = null;

        //Find the file extension by splitting  file name in substring and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if (fileExtensionName.equals(".xlsx")) {

            //If it is xlsx file then create object of XSSFWorkbook class

            sampleWorkbook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if (fileExtensionName.equals(".xls")) {

            //If it is xls file then create object of XSSFWorkbook class

            sampleWorkbook = new HSSFWorkbook(inputStream);

        }

        //Read excel sheet by sheet name

        Sheet sheet = sampleWorkbook.getSheet(sheetName);

        //Get the current count of rows in excel file

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        //Get the first row from the sheet

        Row row = sheet.getRow(0);

        //Create a new row and append it at last of sheet

        Row newRow = sheet.createRow(rowCount + 1);

        //Create a loop over the cell of newly created Row

        for (int j = 0; j < row.getLastCellNum(); j++) {

            //Fill data in row

            Cell cell = newRow.createCell(j);

            cell.setCellValue(dataToWrite[j]);

        }

        //Close input stream

        inputStream.close();

        //Create an object of FileOutputStream class to create write data in excel file

        FileOutputStream outputStream = new FileOutputStream(file);

        //write data in the excel file

        sampleWorkbook.write(outputStream);

        //close output stream

        outputStream.close();

    }

    public static void writeToExcelFile(String filePath, String fileName, String sheetName, String... values) throws IOException {

        //Create an array with the data in the same order in which you expect to be filled in excel file

        String[] valueToWrite = values;

        //Create an object of current class

        WriteExcelFile objExcelFile = new WriteExcelFile();

        //Write the file using file name, sheet name and the data to be filled

        writeExcel(filePath, fileName, sheetName, valueToWrite);

    }

    // Others
    public int randomNumber(int count) {

        Random random = new Random();
        return random.nextInt(count);

    }



}
