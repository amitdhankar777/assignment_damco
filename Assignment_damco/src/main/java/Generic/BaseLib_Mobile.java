package Generic;
import java.io.File;
import java.net.URL;
import java.util.Arrays;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseLib_Mobile extends BaseLib_Master {
	
	public static AppiumDriver<MobileElement> driver;
	String app  = "src/test/resources/Apps/selendroid-test-app.apk";
	File file = new File(app);
	
	@BeforeMethod
	public void setUp() {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "HNP03L6R");
			capabilities.setCapability("appPackage", "com.android.calculator2");
	        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
	        //capabilities.setCapability("app", file.getAbsolutePath());
	        capabilities.setCapability("autoGrantPermissions", true);
	        capabilities.setCapability("noReset", true);
	        capabilities.setCapability("fullReset", false);
			
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<MobileElement>(url, capabilities);
			System.out.println("App started");
			
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.closeApp();
		
	}
	
	@BeforeMethod
	public void brforeMethod(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		String logText = "<b> Test Case " + result.getMethod().getMethodName() + " Started </b> ";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		test.log(Status.INFO, m);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		String methodName = result.getMethod().getMethodName();

		if (result.getStatus() == ITestResult.FAILURE) {
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			test.fail("<details><summary><b><font color=red>" + "Exception Occured, click to see details:"
					+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
			String logText = "<b>Test Case " + methodName + " Failed</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			test.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			String logText = "<b>Test Case " + methodName + " Passed Successfully</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			String logText = "<b>Test Case " + methodName + " Skipped</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
			test.log(Status.SKIP, m);
		}
	}
}
