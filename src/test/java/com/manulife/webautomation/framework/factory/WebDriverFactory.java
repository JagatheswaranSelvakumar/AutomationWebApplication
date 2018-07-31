package com.manulife.webautomation.framework.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import com.manulife.webautomation.framework.browserOption.ChromeBrowserOption;
import com.manulife.webautomation.framework.browserOption.FirefoxBrowserOption;
import com.manulife.webautomation.framework.common.CommonVariable;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebDriverFactory implements DriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);
    protected static WebDriver driver;

    public WebDriver createDriver(DesiredCapabilities caps) {
	String browser = caps.getBrowserName();
	String breakPoint = CommonVariable.breakPoint;
	if (browser.equalsIgnoreCase("firefox")) {
	    FirefoxBrowserOption firefoxBrowserOption = new FirefoxBrowserOption();
	    firefoxBrowserOption.setHeadless(Boolean.parseBoolean(CommonVariable.headlessBrowser));
	    driver = new FirefoxDriver(firefoxBrowserOption.build());
	} else if (browser.equalsIgnoreCase("ie")) {
	    driver = new InternetExplorerDriver();
	} else if (browser.equalsIgnoreCase("safari")) {
	    driver = new SafariDriver();
	} else if (browser.equalsIgnoreCase("chrome")) {
	    if (breakPoint.equalsIgnoreCase("Desktop")) {
		ChromeBrowserOption chromeBrowserOption = new ChromeBrowserOption();
		chromeBrowserOption.setHeadless(Boolean.parseBoolean(CommonVariable.headlessBrowser));
		driver = new ChromeDriver(chromeBrowserOption.build());
	    } else if (breakPoint.equalsIgnoreCase("Mobile") || breakPoint.equalsIgnoreCase("Tablet")) {
		try {
		    initializeAndroidMobileBrowserCapabilities(caps);
		    driver = new AndroidDriver<AndroidElement>(new URL(CommonVariable.appiumServerUrl), caps);
		    driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
		} catch (MalformedURLException e) {
		    logger.error(e.getMessage());
		}
	    } else {
		logger.error("Breakpoint which you metioned in application.properties something wrong...Please check it!!!!!!");
		try {
		    throw new Exception("Breakpoint which you metioned in application.properties something wrong...Please check it!!!!!!");
		} catch (Exception e) {
		}
	    }
	} else {
	    try {
		logger.error("Browser which you metioned in application.properties something wrong...Please check it!!!!!!");
		throw new Exception("Browser which you metioned in application.properties something wrong...Please check it!!!!!!");
	    } catch (Exception e) {
	    }
	}
	return driver;
    }

    public WebDriver getDriver() {
	return driver;
    }

    public DesiredCapabilities initializeAndroidMobileBrowserCapabilities(DesiredCapabilities caps) {
	caps.setCapability(MobileCapabilityType.DEVICE_NAME, CommonVariable.deviceName);
	caps.setCapability(MobileCapabilityType.PLATFORM_NAME, caps.getPlatform());
	caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, caps.getVersion());
	caps.setCapability(MobileCapabilityType.UDID, CommonVariable.udid);
	caps.setCapability(MobileCapabilityType.BROWSER_NAME, caps.getBrowserName());
	caps.setCapability(MobileCapabilityType.NO_RESET, false);
	caps.setCapability("chromedriverExecutable",  System.setProperty("webdriver.chrome.driver", CommonVariable.driverPath + "mac/chromedriver"));
	caps.setCapability("--session-override", true);
	caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
	return caps;
    }
}
