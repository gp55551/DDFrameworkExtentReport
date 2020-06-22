package Utilities;

import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasicExtentReport {

	// builds a new report using the html template
	public ExtentHtmlReporter htmlReporter;

	public ExtentReports extent;
	// helps to generate the logs in test report.
	public ExtentTest test;

	public void startReport(String OS, String browser) {
		// initialize the HtmlReporter
		Date objDate = new Date(); // Current System Date and time is assigned to objDate
		System.out.println(objDate);
		String strDateFormat = "yyyy.MM.dd_HH:mm:ss_zzz"; // Date format is Specified
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // Date format string is passed as an argument to
		// the Date format object
		String NewDate = objSDF.format(objDate);
		String newdate1 = "/ExtentReport/ExeuctionReport.html";
		System.out.println(newdate1);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + newdate1);
		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Browser", browser);

		// configuration items to change the look and feel
		// add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	public void startTest(String TCname, String desc) {
		test = extent.createTest(TCname, desc);

	}

	public void tearDown() {
		// to write or update test information to reporter
		extent.flush();
	}
}