package Mobile.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class DriverManager {
	private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/";
	private static final String APP = "/Users/rahuljain/RestApi/src/test/resources/APK/com.monefy.app.lite_2021-09-27.apk";

	@Getter
	public static AppiumDriver driver;

	@BeforeClass
	public static void setUp() {
		if (driver == null) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium:platformName", "Android");
			capabilities.setCapability("appium:platformVersion", "15");
			capabilities.setCapability("appium:deviceName", "TestDevice01");
			capabilities.setCapability("appium:app", APP);
			capabilities.setCapability("appium:automationName", "UiAutomator2");
			capabilities.setCapability("appium:udid", "emulator-5554");
			capabilities.setCapability("appium:fullReset", "true");
			capabilities.setCapability("appWaitActivity", "*");
			capabilities.setCapability("ensureWebviewsHavePages", true);
			capabilities.setCapability("appium:nativeWebScreenshot", true);
			capabilities.setCapability("newCommandTimeout", 3600);
			capabilities.setCapability("connectHardwareKeyboard", true);
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("autoGrantPermissions", true);

			try {
				driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), capabilities);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@AfterClass
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null; // reset to ensure new driver initialization for next test run
		}
	}

}




