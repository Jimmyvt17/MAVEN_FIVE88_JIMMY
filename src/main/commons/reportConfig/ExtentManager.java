package commons.reportConfig;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir + "/ExtentReports/Fm88ExtentReportResults.html", true);
        }
        return extent;
    }
}
