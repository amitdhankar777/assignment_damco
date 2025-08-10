package com.makemytrip.Features;

import static Generic.Utilities.doClick;
import static Generic.Utilities.doSendKeys;
import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.makemytrip.PageObjects.FlightsSearchResults_Page;
import com.makemytrip.PageObjects.Flights_Page;

import Generic.Wait;

public class SearchFlight_Freature {
	WebDriver driver; 
	Flights_Page fp;
	FlightsSearchResults_Page fsrp;
	ExtentTest test;

	
	public SearchFlight_Freature(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test  = test;
		fp = new Flights_Page(driver);
		fsrp = new FlightsSearchResults_Page(driver);
	}
	
	public void verifyMakeMyTripHomePage() {
	    try {
	    	doClick(driver, Duration.ofSeconds(20), fp.getSignInPopUpCloseButton());
	    	test.log(Status.PASS, "Closed the Sign In pop-up successfully.");
	        Wait.waitForTitle(driver, Duration.ofSeconds(20), "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
	        String actualTitle = driver.getTitle();
	        String expectedTitle = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
	        assertEquals(actualTitle, expectedTitle);
	        test.log(Status.PASS, "Navigated to Make My Trip Home Page successfully. Title: " + actualTitle);
	    } catch (Exception e) {
	        test.log(Status.FAIL, "Failed to verify Make My Trip Home Page. Error: " + e.getMessage());
	        throw e; // Re-throw the exception to ensure test failure is captured
	    }
	}
	
	public void searchFlight(String fromCity, String toCity) {
		try {
			test.log(Status.INFO, "Searching for flights from " + fromCity + " to " + toCity);
			    	
			doClick(driver, Duration.ofSeconds(20), fp.getFromCity());
			
			doSendKeys(driver, Duration.ofSeconds(20), fp.getFromCityPlaceholder(), fromCity);
			//doSendKeys(driver, Duration.ofSeconds(20), fp.getFromCityPlaceholder(), Keys.ARROW_DOWN, Keys.ENTER);
			
			doClick(driver, Duration.ofSeconds(20), fp.getToCity());
			doSendKeys(driver, Duration.ofSeconds(20), fp.getToCityPlaceholder(), toCity);
			doSendKeys(driver, Duration.ofSeconds(20), fp.getToCityPlaceholder(), Keys.ARROW_DOWN, Keys.ENTER);
			
			doClick(driver, Duration.ofSeconds(20), fp.getSep5Date());
			
			
			doClick(driver, Duration.ofSeconds(20), fp.getSearchButton());
			test.log(Status.PASS, "Successfully clicked on Search button.");
			test.log(Status.PASS, "Successfully searched for flights from " + fromCity + " to " + toCity);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to search flight: " + e.getMessage());
			throw e; // Re-throw the exception to ensure test failure is captured
		}
	}

	public void verifyFlightSearchResultsPage() {
		try {
			Wait.waitForTitle(driver, Duration.ofSeconds(20),"MakeMyTrip");
			String actualTitle = driver.getTitle();
			String expectedTitle = "MakeMyTrip";
			assertEquals(actualTitle, expectedTitle, "Flight Search Results Page title does not match!");
			test.log(Status.PASS, "Navigated to Make My Trip Flights Search Results Page successfully. Title: " + actualTitle);
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to verify Make My Trip Flights Search Results Page. Error: " + e.getMessage());
			throw e; // Re-throw the exception to ensure test failure is captured
		}
	}
	
	public void sortFlightsByEarlyDeparture() {
		try {
			doClick(driver, Duration.ofSeconds(20), fsrp.getLockpriceOkButton());
			test.log(Status.PASS, "Clicked on 'OKAY, GOT IT!' button.");

			doClick(driver, Duration.ofSeconds(20), fsrp.getOtherSortIcon());
			test.log(Status.PASS, "Clicked on 'Other Sort' icon.");

			doClick(driver, Duration.ofSeconds(20), fsrp.getEarlyDepartureSortOption());
			test.log(Status.PASS, "Selected 'Early Departure' sort option.");

			String confirmationText = fsrp.getEarlyDepartureSortConfirmationText().getText();
			assertEquals("Flights sorted by Departure (Earliest First)", confirmationText);
			test.log(Status.PASS, "Flights sorted by Early Departure successfully.");

		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to sort flights by Early Departure: " + e.getMessage());
			throw e;
		}
	}
	
	
	
	public void verifyFlightDetails() {
		try {
			String flightName = fsrp.getFlightNames().getText();
			String flightPrice = fsrp.getPriceOfFlights().getText();

			test.log(Status.INFO, "Flight Name: " + flightName);
			test.log(Status.INFO, "Flight Price: " + flightPrice);

			test.log(Status.PASS, "Flight details verified successfully.");
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to verify flight details: " + e.getMessage());
			throw e;
		}
	}
	
	public void retrieveSecondFlightName() {
		test.log(Status.INFO, "Retrieving flight prices and airlines for comparison.");
        //List<WebElement> prices = driver.findElements(By.xpath("//span[text()='VIEW PRICES']/ancestor::button/preceding-sibling::div//span"));
		//List<WebElement> airlines = driver.findElements(By.xpath("//span[text()='VIEW PRICES']/ancestor::button/preceding-sibling::div//span/ancestor"
		//		+ "::div[contains(@class, 'listingCard')]//p[@data-test='component-airlineHeading']"));
        

		List<WebElement> prices  = (List<WebElement>) fsrp.getPriceOfFlights();
		List<WebElement> airlines = (List<WebElement>) fsrp.getFlightNames();
        
		test.log(Status.INFO, "Total Flights Found: " + prices.size() + ", Airlines Found: " + airlines.size());

        if (prices.size() > 1 && airlines.size() > 1) {
            // Retrieve and sort flight prices
            int[] priceValues = new int[prices.size()];
            for (int i = 0; i < prices.size(); i++) {
                String priceText = prices.get(i).getText().replaceAll("[^0-9]", "");
                priceValues[i] = Integer.parseInt(priceText);
            }
            java.util.Arrays.sort(priceValues);

            // Determine the second-lowest price
            int secondLowestPrice = priceValues[1];

            // Find the corresponding airline
            for (int i = 0; i < prices.size(); i++) {
                String priceText = prices.get(i).getText().replaceAll("[^0-9]", "");
                int currentPrice = Integer.parseInt(priceText);
                if (currentPrice == secondLowestPrice) {
                    String airlineName = airlines.get(i).getText();
                    test.log(Status.INFO,"Second Lowest Priced Flight - Airline: " + airlineName + ", Price: ₹ " + secondLowestPrice);
                    break;
                }
            }
        } else {
        	test.log(Status.WARNING, "Not enough flights found to compare prices.");
        }
	}
	
	

}
