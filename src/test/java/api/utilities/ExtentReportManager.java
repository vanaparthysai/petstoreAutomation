package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkreporter;
    public ExtentReports extent;
    public ExtentTest test;
    String reName;

    @Override
    public void onStart(ITestContext testcontext) {
        // Generate a unique report name using a timestamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reName = "Test-Report-" + timeStamp + ".html";

        // Initialize the ExtentSparkReporter
        String reportPath = System.getProperty("user.dir") + "/Reports/" + reName;
        sparkreporter = new ExtentSparkReporter(reportPath);

        // Configure the reporter
        sparkreporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkreporter.config().setReportName("Pet Store User API");
        sparkreporter.config().setTheme(Theme.DARK);

        // Initialize the ExtentReports object and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkreporter);

        // Add system information to the report
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Sai");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test failed: " + result.getName());
        if (result.getThrowable() != null) {
            test.log(Status.FAIL, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext testcontext) {
        // Flush the report to ensure all information is written
        if (extent != null) {
            extent.flush();
        }
    }
}
