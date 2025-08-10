package Generic;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseLib_Master {
	public static ExtentReports report;
	public static ExtentTest test = null;

	@BeforeSuite
	public void beforeClass() {
		report = ExtentManager.createInstance();
	}

	@AfterSuite
	public void afterClass() {
		report.flush();
	}
}
