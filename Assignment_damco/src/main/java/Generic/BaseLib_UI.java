package Generic;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.common.io.Files;

public class BaseLib_UI extends BaseLib_Master {
	public static WebDriver driver;

	@BeforeMethod
	@Parameters({ "browser", "baseurl"})
	public void preCondition(String browserName, String url) {
		try {
			driver=BrowserFactory.selectBrowser(browserName);
			test.log(Status.INFO, ""+browserName+" Browser launched");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.get(url);
			Reporter.log("Navigated to URL"+ url, true);
			test.log(Status.INFO, "Navigated to URL  "+url);
			
		} catch (Exception e) {
			Reporter.log("Browser Driver Version not Updated. Please Update Your Browser Driver ", true);
			test.log(Status.INFO, "Browser Driver Version not Updated. Please Update Your Browser Driver "+url);
		}
		
	}

	@AfterMethod
	public void postCondition() {
		driver.quit();
		Reporter.log("Browser Closed", true);
		test.log(Status.INFO, "Browser Closed");
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

			String path = takeScreenShot(result.getMethod().getMethodName());
			try {
				test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				test.fail("Test Failed, cannot attach screenshot");
			}

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

	public String takeScreenShot(String methodName) {
		String fileName = methodName + ".png";
		TakesScreenshot ts = (TakesScreenshot) BaseLib_UI.driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String directory = System.getProperty("user.dir") + "/screenshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		File destFile = new File(path);

		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
