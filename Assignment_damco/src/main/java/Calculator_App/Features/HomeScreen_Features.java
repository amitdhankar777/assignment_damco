package Calculator_App.Features;


import static Generic.Utilities.isElementDisplayed;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Calculator.PageObjects.CalcHomeScreen_Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomeScreen_Features {
	ExtentTest test;
	CalcHomeScreen_Page hp;
	private AppiumDriver<MobileElement> driver;
	
	public HomeScreen_Features(AppiumDriver<MobileElement> driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		hp = new CalcHomeScreen_Page(driver);
	}
	
	public void verifyAllHomeScreenElements() {
		test.log(Status.INFO, "Verifying all elements on the Home screen");
		verifyZeroButtonElementIsDisplayed();
		verifyOneButtonElementIsDisplayed();
		verifyTwoButtonElementIsDisplayed();
		verifyThreeButtonElementIsDisplayed();
		verifyFourButtonElementIsDisplayed();
		verifyFiveButtonElementIsDisplayed();
		verifySixButtonElementIsDisplayed();
		verifySevenButtonElementIsDisplayed();
		verifyEightButtonElementIsDisplayed();
		verifyNineButtonElementIsDisplayed();
		verifyPlusButtonElementIsDisplayed();
		verifyMinusButtonElementIsDisplayed();
		verifyMultiplyButtonElementIsDisplayed();
		verifyDivideButtonElementIsDisplayed();
		verifyEqualButtonElementIsDisplayed();
		test.log(Status.PASS, "All elements on the Home screen verified successfully");
	}
	
	public void verifyZeroButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnZero())) {
			test.log(Status.PASS, "Zero Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Zero Button on the Home screen is not Present");
		}
		
	}
	
	public void verifyOneButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnOne())) {
			test.log(Status.PASS, "One Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "One Button on the Home screen is not Present");
		}
		
	}
	
	public void verifyTwoButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnTwo())) {
			test.log(Status.PASS, "Two Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Two Button on the Home screen is not Present");
		}
		
	}
	
	public void verifyThreeButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnThree())) {
			test.log(Status.PASS, "Three Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Three Button on the Home screen is not Present");
		}
		
	}

	
	public void verifyFourButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnFour())) {
			test.log(Status.PASS, "Four Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Four Button on the Home screen is not Present");
		}

	}
	
	public void verifyFiveButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnFive())) {
			test.log(Status.PASS, "Five Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Five Button on the Home screen is not Present");
		}

	}
	
	public void verifySixButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnSix())) {
			test.log(Status.PASS, "Six Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Six Button on the Home screen is not Present");
		}

	}
	
	public void verifySevenButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnSeven())) {
			test.log(Status.PASS, "Seven Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Seven Button on the Home screen is not Present");
		}

	}
	
	public void verifyEightButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnEight())) {
			test.log(Status.PASS, "Eight Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Eight Button on the Home screen is not Present");
		}

	}
	
	public void verifyNineButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnNine())) {
			test.log(Status.PASS, "Nine Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Nine Button on the Home screen is not Present");
		}

	}
	
	public void verifyPlusButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnPlus())) {
			test.log(Status.PASS, "Plus Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Plus Button on the Home screen is not Present");
		}

	}
	
	public void verifyMinusButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnMinus())) {
			test.log(Status.PASS, "Minus Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Minus Button on the Home screen is not Present");
		}

	}
	
	public void verifyMultiplyButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnMultiply())) {
			test.log(Status.PASS, "Multiply Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Multiply Button on the Home screen is not Present");
		}

	}
	
	public void verifyDivideButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnDivide())) {
			test.log(Status.PASS, "Divide Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Divide Button on the Home screen is not Present");
		}

	}
	
	public void verifyEqualButtonElementIsDisplayed() {
		if (isElementDisplayed(hp.getBtnEquals())) {
			test.log(Status.PASS, "Equal Button on the Home screen is Present");
		} else {
			test.log(Status.FAIL, "Equal Button on the Home screen is not Present");
		}

	}
	
	public void performAddition(String operand1, String operand2) {
        clickDigit(operand1);
        hp.getBtnPlus().click();
        clickDigit(operand2);
        hp.getBtnEquals().click();
    }
	
	
	public void performSubtraction(String operand1, String operand2) {
		clickDigit(operand1);
		hp.getBtnMinus().click();
		clickDigit(operand2);
		hp.getBtnEquals().click();
	}
	
	public void performMultiplication(String operand1, String operand2) {	
		clickDigit(operand1);
		hp.getBtnMultiply().click();
		clickDigit(operand2);
		hp.getBtnEquals().click();
	}

	public void performDivision(String operand1, String operand2) {
		clickDigit(operand1);
		hp.getBtnDivide().click();
		clickDigit(operand2);
		hp.getBtnEquals().click();
	}

    public void clickDigit(String digit) {
        for (char c : digit.toCharArray()) {
            String digitId = "com.android.calculator:id/digit_" + c;
            driver.findElementById(digitId).click();
        }
    }


}
