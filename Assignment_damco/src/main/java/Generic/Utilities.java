package Generic;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	public static WebDriver driver;
	public static String mainWindow;

//	public static void doClick(WebDriver driver, Duration time, WebElement element) {
//	    try {
//	        WebDriverWait wait = new WebDriverWait(driver, time);
//	        wait.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(element));
//	        element.click();
//	        System.out.println("Successfully clicked on the element: " + element);
//	    } catch (Exception e) {
//	        System.err.println("Failed to click on the element: " + element);
//	        e.printStackTrace();
//	        throw e;
//	    }
//	}

	public static void doClick(WebDriver driver, Duration timeout, WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, timeout);
	        wait.pollingEvery(Duration.ofMillis(300)); // faster checks
	        wait.ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class);
	        
	        WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(element));
	        
	        try {
	            clickable.click();
	        } catch (ElementClickInterceptedException e) {
	            // Fallback to JS click if intercepted
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickable);
	        }
	        
	        System.out.println("Successfully clicked on the element: " + element);
	        
	    } catch (Exception e) {
	        System.err.println("Failed to click on the element: " + element);
	        throw e;
	    }
	}
	
	
	public static void doSendKeys(WebDriver driver, Duration time, WebElement element, String text) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, time);
	        wait.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(element)); 
	        element.clear();
	        element.sendKeys(text);
	        System.out.println("Successfully sent keys to the element: " + element);
	    } catch (Exception e) {
	        System.err.println("Failed to send keys to the element: " + element);
	        e.printStackTrace();
	        throw e;
	    }
	}

	public static void doSendKeys(WebDriver driver, Duration time, WebElement element, Keys key) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(key);
			System.out.println("Successfully sent keys to the element: " + element);
		} catch (Exception e) {
			System.err.println("Failed to send keys to the element: " + element);
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public static void doSendKeys(WebDriver driver, Duration time, WebElement element, Keys key1, Keys key2) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(key1, key2);
			System.out.println("Successfully sent keys to the element: " + element);
		} catch (Exception e) {
			System.err.println("Failed to send keys to the element: " + element);
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public static void jsClick(WebDriver driver, WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}
	
	public static void doActionsClick(WebDriver driver, Duration time, WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, time);
	        wait.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(element));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(element).click().perform();
	        System.out.println("Successfully clicked on the element using Actions: " + element);
	    } catch (Exception e) {
	        System.err.println("Failed to click on the element using Actions: " + element);
	        e.printStackTrace();
	        throw e;
	    }
	}
		
	public static int randomNumberGenerator(int size) {
		int count = 0;
		Random rand = new Random();
		count = rand.nextInt(size - 1) + 1;
		return count;
	}

	public static String randomStringGenerator(String aToZ) {
		Random rand = new Random();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int randIndex = rand.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		return res.toString();
	}

	public static String stringGenerator(int size) {
		return RandomStringUtils.randomAlphabetic(size).toUpperCase();
	}

	public static void manageSessions() {
		driver = BaseLib_UI.driver;
		mainWindow = driver.getWindowHandle(); // do not forget to return to main window after calling method
		Set<String> sessionID = driver.getWindowHandles();
		Iterator<String> itr = sessionID.iterator();
		while (itr.hasNext()) {
			String childWindow = (String) itr.next();
			if (!(mainWindow.equals(childWindow))) {
				driver.switchTo().window(childWindow);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
	}

	public static void switchToNextTab() {
		driver = BaseLib_UI.driver;
		mainWindow = driver.getWindowHandle();
		Set<String> sessionID = driver.getWindowHandles();
		Iterator<String> itr = sessionID.iterator();
		while (itr.hasNext()) {
			String childWindow = (String) itr.next();
			if (!(mainWindow.equals(childWindow))) {
				driver.switchTo().window(childWindow);
			}
		}
	}

	public static void openLinkInNewTab(WebElement element) {
		String selectLinkOpenInNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		element.sendKeys(selectLinkOpenInNewTab);
		switchToNextTab();
	}

	public static void dropDownSelect(WebElement element) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		  if (options.isEmpty()) {
	            throw new IllegalStateException("The dropdown has no options to select.");
	        }
		int size = options.size();
		s.selectByIndex(Utilities.randomNumberGenerator(size));
	}

	public static void scrollUp() {
		driver = BaseLib_UI.driver;
		Dimension dim = driver.findElement(By.tagName("body")).getSize();
		int pageHeight = dim.getHeight();
		System.out.println("Page Height is :"+ pageHeight);
		String jscode = "window.scrollBy(0,0)";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jscode);
	}

	public static void scrollToElement(WebElement element) {
		driver = BaseLib_UI.driver;
		String jscode = "arguments[0].scrollIntoView(true)";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jscode, element);
	}

	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public static String getTwoFactorCode() {
		//Replace with your security key copied from step 12
		Totp totp = new Totp("CPBQ2XFCGA36QPRBHNLZKGGD6HTQHGYC"); // 2FA secret key
		String twoFactorCode = totp.now(); // Generated 2FA code here
		return twoFactorCode;
	}
	
	public static void closeCurrentTab() {
		driver.close();
	}
	
	public static  String formatDate() {
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String formattedDate = sdf.format(date);
		
		return formattedDate;
		
	}
}
