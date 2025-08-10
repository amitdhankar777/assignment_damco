package Generic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait extends BaseLib_API {

	public static void waitFor(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (Exception e) {
			System.out.println("Exception in thread sleep");
		}
	}

	public static void waitforVisibility(WebDriver driver, Duration time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitforInvisibility(WebDriver driver, Duration time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.ignoring(WebDriverException.class).until(ExpectedConditions.invisibilityOf(element));
	}

	public static void expWaitforClickable(WebDriver driver, Duration time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitforTextToBePresent(WebDriver driver, Duration time, WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.ignoring(WebDriverException.class).until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public static void waitForTitle(WebDriver driver, Duration i, String titleText) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.ignoring(WebDriverException.class).until(ExpectedConditions.titleContains(titleText));
	}

}
