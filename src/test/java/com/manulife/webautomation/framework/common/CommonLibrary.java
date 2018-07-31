package com.manulife.webautomation.framework.common;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.manulife.webautomation.framework.exception.InvalidLocatorStrategyException;
import com.manulife.webautomation.framework.factory.WebDriverFactory;

public class CommonLibrary extends WebDriverFactory {

    private static Logger logger = LogManager.getLogger(CommonLibrary.class);

    public static WebElement findElement(String locator) {
	By by = null;
	try {
	    by = find(locator);
	} catch (Exception e) {
	    logger.error(e);
	    Assert.assertTrue(false, e.getMessage());
	}
	return driver.findElement(by);
    }

    public static List<WebElement> findElements(String locator) {
	By by = null;
	try {
	    by = find(locator);
	} catch (Exception e) {
	    logger.info(e);
	    Assert.assertTrue(false, e.getMessage());
	}
	return driver.findElements(by);
    }

    public static By find(String locator) {
	String actualLocator = null;
	By by = null;
	actualLocator = StringUtils.substringBefore(locator, "~");
	LocatorType locatorType = LocatorType.valueOf(StringUtils.substringAfter(locator, "~").toUpperCase());
	WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(CommonVariable.waitTimeInSeconds));
	try {
	    switch (locatorType) {
	    case ID:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(actualLocator)));
		by = By.id(actualLocator);
		break;
	    case XPATH:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(actualLocator)));
		by = By.xpath(actualLocator);
		break;
	    case NAME:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name(actualLocator)));
		by = By.name(actualLocator);
		break;
	    case TAG_NAME:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(actualLocator)));
		by = By.tagName(actualLocator);
		break;
	    case CSS:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(actualLocator)));
		by = By.cssSelector(actualLocator);
		break;
	    case CLASS_NAME:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(actualLocator)));
		by = By.className(actualLocator);
		break;
	    case LINK_TEXT:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(actualLocator)));
		by = By.linkText(actualLocator);
		break;
	    case PARTIAL_LINK_TEXT:
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(actualLocator)));
		by = By.partialLinkText(actualLocator);
		break;
	    default:
		throw new InvalidLocatorStrategyException("Unknown locator type '" + locatorType + "'");
	    }
	    logger.info("Locator identified successfully!");
	    highlightWebElement(driver.findElement(by));
	} catch (Exception e) {
	    logger.error(e);
	    Assert.assertTrue(false, e.getMessage());
	}
	return by;
    }

    public static void highlightWebElement(WebElement webElement) {
	try {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "color: black; border: 4px solid red;");
	} catch (Exception e) {
	    logger.error(e);
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void click(String locator) {
	By by = null;
	try {
	    by = find(locator);
	    driver.findElement(by).click();
	    logger.info("Successfully clicked on element " + locator);
	} catch (Exception e) {
	    logger.info(e);
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void type(String locator, String text) {
	By by = null;
	try {
	    by = find(locator);
	    driver.findElement(by).sendKeys(text);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void clearAndType(String locator, String text) {
	By by = null;
	try {
	    by = find(locator);
	    driver.findElement(by).clear();
	    driver.findElement(by).sendKeys(text);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void clear(String locator) {
	By by = null;
	try {
	    by = find(locator);
	    driver.findElement(by).clear();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void selectOptionByText(String locator, String optionText) {
	By by = null;
	try {
	    by = find(locator);
	    Select dropdown = new Select(driver.findElement(by));
	    dropdown.selectByVisibleText(optionText);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void selectOptionByIndex(String locator, int optionIndex) {
	By by = null;
	try {
	    by = find(locator);
	    Select dropdown = new Select(driver.findElement(by));
	    dropdown.selectByIndex(optionIndex);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static String getValue(String locator) {
	By by = null;
	String value = null;
	try {
	    by = find(locator);
	    value = driver.findElement(by).getAttribute("value");
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return value;
    }

    public static String getText(String locator) {
	By by = null;
	String text = null;
	try {
	    by = find(locator);
	    text = driver.findElement(by).getText();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return text;
    }

    public static String getAttribute(String locator, String attributeName) {
	By by = null;
	String attributeValue = null;
	try {
	    by = find(locator);
	    attributeValue = driver.findElement(by).getAttribute(attributeName);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return attributeValue;
    }

    public static String getTagName(String locator) {
	By by = null;
	String tagName = null;
	try {
	    by = find(locator);
	    tagName = driver.findElement(by).getTagName();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return tagName;
    }

    public static boolean isElementDisplayed(String locator) {
	boolean isElementDisplayed = false;
	By by = null;
	try {
	    by = find(locator);
	    isElementDisplayed = driver.findElement(by).isDisplayed();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return isElementDisplayed;
    }

    public static boolean isElementEnabled(String locator) {
	boolean isElementEnabled = false;
	By by = null;
	try {
	    by = find(locator);
	    isElementEnabled = driver.findElement(by).isEnabled();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return isElementEnabled;
    }

    public static boolean isElementSelected(String locator) {
	boolean isElementSelected = false;
	By by = null;
	try {
	    by = find(locator);
	    isElementSelected = driver.findElement(by).isSelected();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return isElementSelected;
    }

    public void refresh() {
	try {
	    driver.navigate().refresh();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public void pressBrowserBackButton() {
	try {
	    driver.navigate().back();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public void pressBrowserForwardButton() {
	try {
	    driver.navigate().forward();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public void sleep(int timeInSeconds) {
	try {
	    Thread.sleep(timeInSeconds);
	} catch (InterruptedException ex) {
	}
    }

    public void waitForElementToBePresent(String locator, int waitTimeInSeconds) {
	try {
	    By by = find(locator);
	    WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public void switchToFrameByName(String frameName) {
	try {
	    driver.switchTo().frame(frameName);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public void switchToWindow(String windowName) {
	try {
	    driver.switchTo().window(windowName);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public void switchToParentWindow() {
	try {
	    driver.switchTo().defaultContent();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public String getPageSource() {
	String pageSource = null;
	try {
	    pageSource = driver.getPageSource();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return pageSource;
    }

    public String getTitle() {
	String title = null;
	try {
	    title = driver.getTitle();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return title;
    }

    public String getCurrentUrl() {
	String url = null;
	try {
	    url = driver.getCurrentUrl();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return url;
    }

    public Set<String> getAllWindowHandles() {
	Set<String> windowHandles = null;
	try {
	    windowHandles = driver.getWindowHandles();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return windowHandles;
    }

    public String getCurrentWindowHandle() {
	String currentWindowHandle = null;
	try {
	    currentWindowHandle = driver.getWindowHandle();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return currentWindowHandle;
    }

    public static void openUrl(String url) {
	try {
	    driver.get(url);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void getWindowMaximize() {
	try {
	    driver.manage().window().maximize();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void getFullscreen() {
	try {
	    driver.manage().window().fullscreen();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void deleteCookies() {
	try {
	    driver.manage().deleteAllCookies();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void deleteCookies(String cookieName) {
	try {
	    driver.manage().deleteCookieNamed(cookieName);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void deleteCookies(Cookie cookieName) {
	try {
	    driver.manage().deleteCookie(cookieName);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static void closeBrowser(Cookie cookieName) {
	try {
	    driver.close();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
    }

    public static Point getElementLocation(String locator) {
	Point elementLocation = null;
	By by = null;
	try {
	    by = find(locator);
	    elementLocation = driver.findElement(by).getLocation();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return elementLocation;
    }

    public static int getElementXCoordinate(String locator) {
	Point elementXCoordinate = null;
	By by = null;
	try {
	    by = find(locator);
	    elementXCoordinate = driver.findElement(by).getLocation();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return elementXCoordinate.getX();
    }

    public static int getElementYCoordinate(String locator) {
	Point elementYCoordinate = null;
	By by = null;
	try {
	    by = find(locator);
	    elementYCoordinate = driver.findElement(by).getLocation();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return elementYCoordinate.getY();
    }

    public static Dimension getElementSize(String locator) {
	Dimension elementSize = null;
	By by = null;
	try {
	    by = find(locator);
	    elementSize = driver.findElement(by).getSize();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return elementSize;
    }

    public static int getElementHeight(String locator) {
	Dimension elementHeight = null;
	By by = null;
	try {
	    by = find(locator);
	    elementHeight = driver.findElement(by).getSize();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return elementHeight.getHeight();
    }

    public static int getElementWidth(String locator) {
	Dimension elementWidth = null;
	By by = null;
	try {
	    by = find(locator);
	    elementWidth = driver.findElement(by).getSize();
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	}
	return elementWidth.getWidth();
    }

    enum LocatorType {
	ID, XPATH, CSS, TAG_NAME, NAME, CLASS_NAME, LINK_TEXT, PARTIAL_LINK_TEXT
    }
}
