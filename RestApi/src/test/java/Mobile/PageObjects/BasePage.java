package Mobile.PageObjects;

import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	protected AppiumDriver driver;
	private final long waitTime = 15;

	public BasePage(AppiumDriver driver) {
		this.driver = driver;
	}

	public void waitUntilClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilInvisible(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By) locator));
	}

	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception ignored) {

		}
	}
}
