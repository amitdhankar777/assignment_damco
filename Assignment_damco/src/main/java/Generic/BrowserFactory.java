package Generic;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static WebDriver selectBrowser(String browserName) {
        WebDriver driver = null;

        if (browserName.equalsIgnoreCase(BrowserType.CHROME.toString())) {
            WebDriverManager.chromedriver().setup(); // Automatically manages ChromeDriver
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            //options.addArguments("--Headless");
            driver = new ChromeDriver(options);
            Reporter.log("Chrome browser launched", true);
        } else if (browserName.equalsIgnoreCase(BrowserType.FIREFOX.toString())) {
            WebDriverManager.firefoxdriver().setup(); // Automatically manages FirefoxDriver
            driver = new FirefoxDriver();
            Reporter.log("Firefox browser Launched", true);
		} else if (browserName.equalsIgnoreCase(BrowserType.EDGE.toString())) {
        	WebDriverManager.edgedriver().driverVersion("LATEST").setup();
            driver = new EdgeDriver();
            Reporter.log("Edge browser Launched", true);
        }
        
        if(driver != null) {
            driver.manage().deleteAllCookies();
        }
        if (driver instanceof ChromeDriver) {
            ((ChromeDriver) driver).executeCdpCommand("Network.clearBrowserCache", new HashMap<>());
        }

        return driver;
	}	
	
}
