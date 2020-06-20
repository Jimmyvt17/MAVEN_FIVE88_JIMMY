package commons.reportConfig;

import commons.CommonsTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportNGListener extends CommonsTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("---------- " + context.getName() + " STARTED test ----------");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("---------- " + context.getName() + " FINISHED test ----------");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------- " + result.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED test ----------");
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Object testClass = result.getInstance();
        WebDriver webDriver = ((CommonsTest) testClass).getDriver();
        String screenshot = captureScreenshotBase64(webDriver);
        Reporter.getCurrentTestResult();
        Reporter.log(ExtentTestManager.getTest().addBase64ScreenShot(screenshot));
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SKIPPED test ----------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
    }

//    public String captureScreenshot(WebDriver driver, String screenshotName) {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String fileName = screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
//            String screenPath = System.getProperty("user.dir") + "/test-output/html/Screenshots/" + fileName;
//            FileUtils.copyFile(source, new File(screenPath));
//            return fileName;
//        } catch (IOException e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//            return e.getMessage();
//        }
//    }

    public String captureScreenshotBase64(WebDriver driver) {
        try {
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            return base64Screenshot;
        } catch (Throwable e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }

}
