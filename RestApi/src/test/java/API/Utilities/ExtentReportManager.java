package API.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
	private static ExtentReports extent;
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	/**
	 * Initializes the ExtentReports with a specified file path and configuration.
	 */
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);
		sparkReporter.config().setReportName("Automation Test Report");
		sparkReporter.config().setDocumentTitle("Pet Store API");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Set system/environment details
		extent.setSystemInfo("Application", "Pet Store API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Rahul Jain");
	}

	/**
	 * Creates a new test entry at the start of each test.
	 */
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		test.set(extentTest);
	}

	/**
	 * Logs a successful test execution.
	 */
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test Passed");
	}

	/**
	 * Logs a failed test execution, along with exception details.
	 */
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "Test Failed");
		test.get().log(Status.FAIL, result.getThrowable().getMessage());

		// Optional: Add screenshot on failure
		// String screenshotPath = takeScreenshot(result.getName());
		// test.get().addScreenCaptureFromPath(screenshotPath);
	}

	/**
	 * Logs a skipped test execution.
	 */
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test Skipped");
		if (result.getThrowable() != null) {
			test.get().log(Status.SKIP, result.getThrowable().getMessage());
		}
	}

	/**
	 * Flushes the ExtentReports instance on finish.
	 */
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
}

