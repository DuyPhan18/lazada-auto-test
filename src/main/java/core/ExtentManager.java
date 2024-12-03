package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extentReports;

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            createInstance("reports/extentReport/extentReport.html");
        }
        return extentReports;
    }

    public static ExtentReports createInstance(String fileName){
        extentReports = new ExtentReports();

        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreport/extentreport.html");
        reporter.config().setReportName("Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Final Assignment");
        extentReports.setSystemInfo("Author", "Duy");
        return extentReports;

    }
}
