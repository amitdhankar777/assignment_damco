package Generic;

import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class BaseLib_API extends BaseLib_Master {
	
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
