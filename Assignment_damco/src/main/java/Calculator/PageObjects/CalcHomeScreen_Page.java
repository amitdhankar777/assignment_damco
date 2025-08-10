//Here we will keep all the elements present on the Home Screen 
//and initialize all of them with constructor having driver as an argument
package Calculator.PageObjects;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.Getter;

public class CalcHomeScreen_Page {

		@FindBy(id = "com.android.calculator:id/digit_0")
		private @Getter MobileElement btnZero;
	
	 	@FindBy(id = "com.android.calculator:id/digit_1")
	    private @Getter MobileElement btnOne;
	 
	    @FindBy(id = "com.android.calculator:id/digit_2")
	    private @Getter MobileElement btnTwo;

	    @FindBy(id = "com.android.calculator:id/digit_3")
	    private @Getter MobileElement btnThree;
	    
	    @FindBy(id = "com.android.calculator:id/digit_4")
	    private @Getter MobileElement btnFour;

	    @FindBy(id = "com.android.calculator:id/digit_5")
	    private @Getter MobileElement btnFive;
	    
	    @FindBy(id = "com.android.calculator:id/digit_6")
	    private @Getter MobileElement btnSix;

	    @FindBy(id = "com.android.calculator:id/digit_7")
	    private @Getter MobileElement btnSeven;
	    
	    @FindBy(id = "com.android.calculator:id/digit_8")
	    private @Getter MobileElement btnEight;

	    @FindBy(id = "com.android.calculator:id/digit_9")
	    private @Getter MobileElement btnNine;

	    @FindBy(id = "com.android.calculator:id/op_plus")
	    private @Getter MobileElement btnPlus;
	    
	    @FindBy(id = "com.android.calculator:id/op_minus")
	    private @Getter MobileElement btnMinus;
	    
	    @FindBy(id = "com.android.calculator:id/op_multiply")
	    private @Getter MobileElement btnMultiply;
	    
	    @FindBy(id = "com.android.calculator:id/op_divide")
	    private @Getter MobileElement btnDivide;
	    
	    @FindBy(id = "com.android.calculator:id/eq")
	    private @Getter MobileElement btnEquals;

	    @FindBy(id = "com.android.calculator:id/result")
	    private @Getter MobileElement txtResult;
	    
	    public CalcHomeScreen_Page(AppiumDriver<MobileElement> driver) {
	        PageFactory.initElements(driver, this);
	    }
	
	
}
