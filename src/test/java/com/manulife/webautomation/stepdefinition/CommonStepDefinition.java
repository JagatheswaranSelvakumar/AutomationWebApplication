package com.manulife.webautomation.stepdefinition;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.manulife.webautomation.framework.builder.WebCapabilitiesBuilder;
import com.manulife.webautomation.framework.common.CommonLibrary;
import com.manulife.webautomation.framework.common.CommonVariable;
import com.manulife.webautomation.framework.factory.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonStepDefinition {
    protected static WebDriver driver;
    protected DesiredCapabilities caps;
    private static Logger logger = LogManager.getLogger(CommonStepDefinition.class);
    protected static Properties prop;
    public CommonVariable commonVariables;

    @Before
    public void beforeScenario() throws Exception {
	logger.info("Running execution start method");
	caps = new WebCapabilitiesBuilder().addBrowser(CommonVariable.browser).addBrowserDriverExecutablePath(System.getProperty("user.dir") + File.separator + CommonVariable.driverPath)
		.addVersion(CommonVariable.version).addPlatform(CommonVariable.platform).build();
	driver = new WebDriverFactory().createDriver(caps);
	CommonLibrary.openUrl(CommonVariable.url);
	if (!CommonVariable.breakPoint.equalsIgnoreCase("Mobile")) {
	    driver.manage().window().maximize();
	}
	driver.manage().deleteAllCookies();
    }

    @After
    public void afterScenario(Scenario scenario) throws InterruptedException, IOException {
	if (scenario.isFailed()) {
	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(CommonVariable.currentRootDir + File.separator + "target" + File.separator + "screenshots" + File.separator + scenario.getName() + ".png"));
	    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    scenario.embed(screenshot, "image/png");
	}
	if (!CommonVariable.browser.equalsIgnoreCase("firefox")) {
	    driver.close();
	}
	driver.quit();
    }
}
