package com.makemytrip.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class Flights_Page {
	

	@FindBy(xpath="//span[@class='commonModal__close']")
	private @Getter WebElement signInPopUpCloseButton;
	
	@FindBy(id="fromCity")
	private @Getter WebElement fromCity;
	
	@FindBy(xpath="//input[@placeholder='From']")
	private @Getter WebElement fromCityPlaceholder;
	
	@FindBy(id="toCity")
	private @Getter WebElement toCity;
	
	@FindBy(xpath="//input[@placeholder='To']")
	private @Getter WebElement toCityPlaceholder;
	
	@FindBy(xpath="//a[text()='Search']")
	private @Getter WebElement searchButton;
	
	@FindBy(xpath="//div[@class='datePickerContainer']")
	private @Getter WebElement datePicker;
	
	@FindBy(xpath="//div[@aria-label='Fri Sep 05 2025']")
	private @Getter WebElement sep5Date;
	
	
	public Flights_Page(WebDriver driver) {
	PageFactory.initElements(driver, this);
	
}
}
