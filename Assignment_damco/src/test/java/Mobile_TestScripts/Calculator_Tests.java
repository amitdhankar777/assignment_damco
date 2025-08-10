package Mobile_TestScripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Calculator.PageObjects.CalcHomeScreen_Page;
import Calculator_App.Features.HomeScreen_Features;
import Generic.BaseLib_Mobile;

public class Calculator_Tests extends BaseLib_Mobile {
	
	private CalcHomeScreen_Page calcPage;
    private HomeScreen_Features homeScreenFeatures;

    @BeforeClass
    public void setupPageObjects() {
        // Initialize mobile page objects once driver is set up
        calcPage = new CalcHomeScreen_Page(driver);
        homeScreenFeatures = new HomeScreen_Features(driver, test);
    }

    @Test(priority = 1)
    public void verifyAllElementsOnScreen() {
        test.log(Status.INFO, "Test: Verify title and elements on the app home screen");
        homeScreenFeatures.verifyAllHomeScreenElements();
    }

    @Test(priority = 2)
    public void verifyCalculatorAdditionFunctionality() {
        test.log(Status.INFO, "Test: Verify addition functionality in Calculator");
        homeScreenFeatures.performAddition("12", "8");
        String result = calcPage.getTxtResult().getText();
        Assert.assertEquals(result, "20");
        test.log(Status.PASS, "Addition operation passed successfully with result: " + result);
    }

    @Test(priority = 3)
    public void verifyCalculatorSubtractionFunctionality() {
        test.log(Status.INFO, "Test: Verify subtraction functionality in Calculator");
        homeScreenFeatures.performSubtraction("50", "20");
        String result = calcPage.getTxtResult().getText();
        Assert.assertEquals(result, "30");
        test.log(Status.PASS, "Subtraction operation passed successfully with result: " + result);
    }

    @Test(priority = 4)
    public void verifyCalculatorMultiplicationFunctionality() {
        test.log(Status.INFO, "Test: Verify multiplication functionality in Calculator");
        homeScreenFeatures.performMultiplication("7", "6");
        String result = calcPage.getTxtResult().getText();
        Assert.assertEquals(result, "42");
        test.log(Status.PASS, "Multiplication operation passed successfully with result: " + result);
    }

    @Test(priority = 5)
    public void verifyCalculatorDivisionFunctionality() {
        test.log(Status.INFO, "Test: Verify division functionality in Calculator");
        homeScreenFeatures.performDivision("36", "6");
        String result = calcPage.getTxtResult().getText();
        Assert.assertEquals(result, "6");
        test.log(Status.PASS, "Division operation passed successfully with result: " + result);
    }
}
