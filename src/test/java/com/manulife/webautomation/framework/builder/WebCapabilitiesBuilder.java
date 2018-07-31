package com.manulife.webautomation.framework.builder;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.manulife.webautomation.framework.browserProfile.BrowserProfile;
import com.manulife.webautomation.framework.common.CommonVariable;

public class WebCapabilitiesBuilder extends CapabilitiesBuilder {
    private DesiredCapabilities capabilities;

    public WebCapabilitiesBuilder() {
	capabilities = new DesiredCapabilities();
    }

    @Override
    public WebCapabilitiesBuilder addBrowser(String browser) {
	capabilities.setBrowserName(browser);
	return this;
    }

    @Override
    public WebCapabilitiesBuilder addVersion(String version) {
	capabilities.setVersion(version);
	return this;
    }

    @Override
    public WebCapabilitiesBuilder addPlatform(final String platform) {
	Platform platformName = null;

	switch (platform.toLowerCase()) {
	case "windows":
	    platformName = Platform.WINDOWS;
	    break;
	case "xp":
	    platformName = Platform.XP;
	    break;
	case "linux":
	    platformName = Platform.LINUX;
	    break;
	case "mac":
	    platformName = Platform.MAC;
	    break;
	case "android":
	    platformName = Platform.ANDROID;
	    break;
	default:
	    platformName = Platform.WINDOWS;
	    break;
	}
	capabilities.setPlatform(platformName);
	return this;
    }

    @Override
    public DesiredCapabilities build() {
	return capabilities;
    }

    @Override
    public CapabilitiesBuilder addBrowserDriverExecutablePath(String path) {
	if (null != path) {
	    if (capabilities.getBrowserName().equalsIgnoreCase("chrome")) {
		if (CommonVariable.os.equalsIgnoreCase("MAC")) {
		    System.setProperty("webdriver.chrome.driver", path + "mac/chromedriver");
		} else
		    System.setProperty("webdriver.chrome.driver", path + "windows/chromedriver.exe");
	    } else if (capabilities.getBrowserName().equalsIgnoreCase("firefox")) {
		if (CommonVariable.os.equalsIgnoreCase("MAC")) {
		    System.setProperty("webdriver.gecko.driver", path + "mac/geckodriver");
		} else
		    System.setProperty("webdriver.gecko.driver", path + "windows/geckodriver.exe");
	    }
	}
	return this;
    }

    @Override
    public CapabilitiesBuilder addBrowserProfile(BrowserProfile browserProfile) {
	return null;
    }

}
