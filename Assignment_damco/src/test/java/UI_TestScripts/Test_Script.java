package UI_TestScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.makemytrip.Features.SearchFlight_Feature;

import Generic.BaseLib_UI;

public class Test_Script extends BaseLib_UI {
	
	@Test(priority=1)
	public void testFlightSearchFunctionality() {
		test.log(Status.INFO, "Test:To test the Search flight functionality");
		SearchFlight_Feature sff = new SearchFlight_Feature(driver, test);
		sff.verifyMakeMyTripHomePage();
		sff.searchFlight("Delhi", "Mumbai");
		//sff.verifyFlightSearchResultsPage();
		sff.sortFlightsByEarlyDeparture();
		sff.verifyFlightDetails();
		sff.retrieveSecondFlightName();
		test.log(Status.PASS, "Test completed successfully.");
		
	}
	
}
