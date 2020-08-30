package commons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import commons.utility.VideoRecorder;
import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CommonsTest extends AbstractPage {
    private WebDriver driver;
    protected final Log log;

    public CommonsTest() {
        log = LogFactory.getLog(getClass());

    }

    public WebDriver getDriver() {
        return driver;

    }

    public void sendBot(String text) {
        OkHttpClient client = new OkHttpClient();

        TelegramBot bot = new TelegramBot.Builder(Constants.FIVE88BOT).okHttpClient(client).build();

        if (text.contains("TSport")) {
            long chatId = Constants.SPORTBOOK_ROOM_ID;
            SendMessage request = new SendMessage(chatId, text);
            // sync
            SendResponse sendResponse = bot.execute(request);
        } else {
            long chatId = Constants.FM88_FAIL_ROOM_ID;
            SendMessage request = new SendMessage(chatId, text);
            // sync
            SendResponse sendResponse = bot.execute(request);
        }

    }

    public void sendBotReplyToUser(String text, int userId) {
        OkHttpClient client = new OkHttpClient();

        TelegramBot bot = new TelegramBot.Builder(Constants.FIVE88BOT).okHttpClient(client).build();

        long chatId = Constants.FM88_FAIL_ROOM_ID;

        SendMessage request = new SendMessage(chatId, text).parseMode(ParseMode.Markdown).replyToMessageId(userId);
        // sync
        SendResponse sendResponse = bot.execute(request);

    }

    public void delayInHour(Integer delayTime, Integer timeInHour) {

        int delta = 0;
        log.info("delta = " + delta);

        while (delta<delayTime) {

            log.info("Delay " + timeInHour + " hour");
            try {
                Thread.sleep(1000*60*60*timeInHour);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            delta++;
            log.info("delta = " + delta);

        }

    }

    public void delayInMinute(Integer delayTime, Integer timeInMinute) {

        int delta = 0;
        log.info("delta = " + delta);

        while (delta<delayTime) {

            log.info("Delay " + timeInMinute + " minute");
            try {
                Thread.sleep(1000*60*timeInMinute);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            delta++;
            log.info("delta = " + delta);

        }

    }
    
    public void deleteAllFiles(String pathOfFiles) {

        File path = new File(pathOfFiles);
        if(path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.getName().contains(getClass().getName())) {
                    log.info("Deleted filename: " + file.getName());
                    file.delete();
                }
            }
        } else {
            log.info("Folder " + pathOfFiles + " khong ton tai");
        }

    }

//    public void convertException(Throwable e, String prefix) {
//        String error, err;
//        if (!e.toString().contains("\n")) {
//            error = e.toString();
//        } else {
//            error = e.toString().substring(0, e.toString().indexOf("\n"));
//        }
//        if (!error.contains("StaleElementReferenceException")) {
//            if (!error.contains("AssertionError")) {
//                if (!error.contains("ElementNotInteractableException")) {
//                    if (!error.contains("ERR_NAME_NOT_RESOLVED")) {
//                        err = prefix + error + "\n==============================================\n";
//                    } else {
//                        err = prefix + Constants.banningSite + "\n==============================================\n";
//                    }
//                } else {
//                    err = prefix + Constants.elementIsOverlaying + "\n==============================================\n";
//                }
//            } else {
//                err = prefix + Constants.loadingTimeTooLong + "\n==============================================\n";
//            }
//        } else {
//            err = prefix + Constants.elementIsRemoved + "\n==============================================\n";
//        }
//        sendBot(err);
//
//    }

    public void convertException(Throwable e, String prefix) {
        String error, err;
        if (!e.toString().contains("\n")) {
            error = e.toString();
        } else {
            error = e.toString().substring(0, e.toString().indexOf("\n"));
        }
        if (error.contains("AssertionError")) {
            err = prefix + Constants.loadingTimeTooLong + "\n==============================================\n";
            sendBot(err);
            throw new RuntimeException(error);
        } else if (error.contains("ElementNotInteractableException")) {
            err = prefix + Constants.elementIsOverlaying + "\n==============================================\n";
            sendBot(err);
            throw new RuntimeException(error);
        } else if (error.contains("ERR_NAME_NOT_RESOLVED")) {
            err = prefix + Constants.banningSite + "\n==============================================\n";
            sendBot(err);
            throw new RuntimeException(error);
        } else if (error.contains("StaleElementReferenceException")) {
            e.printStackTrace();
        } else {
            err = prefix + error + "\n==============================================\n";
            sendBot(err);
            throw new RuntimeException(error);
        }

    }

    protected WebDriver openMultiBrowser(String browserName, String url) {
        deleteAllFiles(Constants.videoRecordPathName);

        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("mac")) {

            if (browserName.equalsIgnoreCase("chrome")) {

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

            } else if (browserName.equalsIgnoreCase("edge")) {

                WebDriverManager.edgedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Microsoft\\ Edge.app/Contents/MacOS/Microsoft\\ Edge");
                EdgeOptions edgeOptions = new EdgeOptions().merge(options);
                driver = new EdgeDriver(edgeOptions);

            } else if (browserName.equalsIgnoreCase("firefox")){

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

            } else  if (browserName.equalsIgnoreCase("safari")) {

                driver = new SafariDriver();

            } else if (browserName.equalsIgnoreCase("coc coc")) {

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/CocCoc.app/Contents/MacOS/CocCoc");
                driver = new ChromeDriver(options);

            }

            else {

                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();

            }

        } else {

            if (browserName.equalsIgnoreCase("chrome")) {

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

            } else if (browserName.equalsIgnoreCase("edge")) {

                WebDriverManager.edgedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("\"C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe\"");
                EdgeOptions edgeOptions = new EdgeOptions().merge(options);
                driver = new EdgeDriver(edgeOptions);

            } else if (browserName.equalsIgnoreCase("firefox")){

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

            } else if (browserName.equalsIgnoreCase("ie")) {

                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();

            } else if (browserName.equalsIgnoreCase("coc coc")) {

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\Administrator\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
                driver = new ChromeDriver(options);

            }

            else {

                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();

            }

        }

        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);
        Dimension d = new Dimension(1792, 1120);
        driver.manage().window().setSize(d);
        try {
            driver.get(url);
        } catch (Throwable e) {
            log.info(Constants.banningSite);
            driver.quit();
            convertException(e, url + "\n");
        }

        try {
            VideoRecorder.startRecord(getClass().getName());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        log.info("Check for warning");
        if (checkForBCA(driver)) {
            closeBrowserAndDriver(driver);
            sendBot(url + "\n" + Constants.WarningSite + "\n==============================================");
            throw new RuntimeException(Constants.WarningSite);
        }

        log.info("Check for upgrading");
        if (checkForUpgrading(driver)) {
            closeBrowserAndDriver(driver);
            sendBot(url + "\n" + Constants.pageIsMaintained + "\n==============================================");
            throw new RuntimeException(Constants.pageIsMaintained);
        }

        return driver;

    }

    protected void closeBrowserAndDriver(WebDriver driver) {
        try {
            VideoRecorder.stopRecord();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String cmd = "";
            if (driver != null) {
                driver.quit();
            }

            if (driver.toString().toLowerCase().contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("opera")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill operadriver";
                }
                else {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("firefox")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                } else {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                }
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            log.info("---------- QUIT BROWSER SUCCESS ----------\n");
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }

    protected void switchToSpecificNetwork(String networkName, String networkPass) {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            String cmd;
            if (osName.toLowerCase().contains("mac")) {
                cmd = "networksetup -setairportnetwork en0 " + networkName + " " + networkPass;
            } else {
                cmd = "netsh wlan connect ssid=" + networkName + " name=" + networkName;
            }
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        boolean i = true;
        while (i) {
            if (isInternetReachable()) {
                log.info("Network " + networkName + " is connected");
                i = false;
            }
        }

    }

    public boolean isInternetReachable() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
            Object objData = urlConnect.getContent();

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }

    // Others
    public int randomNumber(int count) {

        Random random = new Random();
        return random.nextInt(count);

    }

    public String randomString(int count, boolean letters, boolean numbers) {

        RandomStringUtils randomString = new RandomStringUtils();
        return randomString.random(count, letters, numbers);

    }

}
