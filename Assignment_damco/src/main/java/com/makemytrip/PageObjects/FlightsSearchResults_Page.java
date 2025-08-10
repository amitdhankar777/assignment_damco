package com.makemytrip.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class FlightsSearchResults_Page {
	
	@FindBy(xpath="//button[text()='OKAY, GOT IT!']")
	private @Getter WebElement lockpriceOkButton;
	
	@FindBy(id="//p[text()='Other Sort']")
	private @Getter WebElement otherSortIcon;
	
	@FindBy(xpath="//span[text()='Early Departure']")
	private @Getter WebElement earlyDepartureSortOption;
	
	@FindBy(xpath="//span[text()='Late  Departure']")
	private @Getter WebElement lateDepartureSortOption;
	
	@FindBy(xpath="//span[text()='Early Arrival']")
	private @Getter WebElement earlyArrivalSortOption;
	
	@FindBy(xpath="//span[text()='Flights sorted by Departure (Earliest First)']")
	private @Getter WebElement earlyDepartureSortConfirmationText;
	
	//@FindAll({ @FindBy(xpath = "//div[@class='priceSection priceLockPersuasionExists ']//div[@data-test='component-fare']//span") })
	@FindAll({ @FindBy(xpath = "//span[text()='VIEW PRICES']/ancestor::button/preceding-sibling::div//span") })
	private @Getter WebElement priceOfFlights;
	
	//@FindAll({ @FindBy(xpath = "//div[@class='priceSection priceLockPersuasionExists ']//div[@data-test='component-fare']//span/ancestor::div//p[@class='boldFont blackText airlineName']") })
	@FindAll({ @FindBy(xpath = "//span[text()='VIEW PRICES']/ancestor::button/preceding-sibling::div//span/ancestor::div[contains(@class, 'listingCard')]//p[@data-test='component-airlineHeading']") })
	private @Getter WebElement flightNames;
	
	
	
	
	
	


	
	
	public FlightsSearchResults_Page(WebDriver driver) {
	PageFactory.initElements(driver, this);
	
}
}
