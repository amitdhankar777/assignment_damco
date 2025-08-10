package UI_TestScripts;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MMT_UI_Tests {
    private static final Logger logger = Logger.getLogger(MMT_UI_Tests.class.getName());

    @Test
    public void testFlightSearchFunctionality() {
        // Set up the WebDriver
    	WebDriverManager.chromedriver().setup(); // Automatically manages ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.addArguments("--Headless");
        ChromeDriver driver = new ChromeDriver(options);

        try {
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get("https://www.makemytrip.com/flights/");
            logger.info("Opened MMT website.");

            // Insert code to handle pop-ups if necessary

            // Perform search and sort as required
            performSearch(driver);

            // Retrieve flight details
            List<WebElement> prices = driver.findElements(By.xpath("//span[contains(@class,'clusterViewPrice')]"));
            List<WebElement> airlines = driver.findElements(By.xpath("//p[@data-test='component-airlineHeading']"));

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
                        logger.info("Second Lowest Priced Flight - Airline: " + airlineName + ", Price: ₹ " + secondLowestPrice);
                        break;
                    }
                }
            } else {
                logger.warning("Not enough flights found to compare prices.");
            }
        } finally {
            // Close the browser
            driver.quit();
            logger.info("Closed the browser.");
        }
    }

    private static void performSearch(WebDriver driver) {
    driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
    	
        // Enter departure city
        WebElement fromInput = driver.findElement(By.id("fromCity"));
        fromInput.click();
        fromInput.sendKeys("Delhi");
        fromInput.sendKeys(Keys.ENTER);

        // Enter arrival city
        WebElement toInput = driver.findElement(By.id("toCity"));
        toInput.sendKeys("Mumbai");
        toInput.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
        
        driver.findElement(By.xpath("//div[@aria-label='Fri Sep 05 2025']")).click();

        // Click search button
        WebElement searchButton = driver.findElement(By.cssSelector("a.widgetSearchBtn"));
        searchButton.click();

        // Sort results by Early Departure Time
        WebElement sortButton = driver.findElement(By.xpath("//a[contains(text(),'DEPARTURE')]"));
        sortButton.click();
    }
}
