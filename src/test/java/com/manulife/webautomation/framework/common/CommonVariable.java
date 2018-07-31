package com.manulife.webautomation.framework.common;

import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonVariable {
    private static Logger logger = LogManager.getLogger(CommonVariable.class);
    public static String url;
    public static String browser;
    public static String version;
    public static String os;
    public static String platform;
    public static String driverPath;
    public static String waitTimeInSeconds;
    public static String headlessBrowser;
    public static String breakPoint;
    public static String cucumberJsonReportPath;
    public static String cucumberUsageJsonReportPath;
    public static String udid;
    public static String deviceName;
    public static String appiumServerUrl;
    public static String currentRootDir = System.getProperty("user.dir");

    public CommonVariable() {
    }

    public void init(Properties prop) {
	logger.info("Started to initialize the properties value");
	url = readProperty(prop, "url");
	browser = readProperty(prop, "browser");
	version = readProperty(prop, "version");
	platform = readProperty(prop, "platform");
	driverPath = readProperty(prop, "driverPath");
	waitTimeInSeconds = readProperty(prop, "waitTimeInSeconds");
	headlessBrowser = readProperty(prop, "headlessBrowser");
	breakPoint = readProperty(prop, "breakPoint");
	cucumberJsonReportPath = readProperty(prop, "cucumberJsonReportPath");
	cucumberUsageJsonReportPath = readProperty(prop, "cucumberUsageJsonReportPath");
	udid = readProperty(prop, "udid");
	os = readProperty(prop, "os");
	deviceName = readProperty(prop, "deviceName");
	appiumServerUrl = readProperty(prop, "appiumServerUrl");
	logger.info("All the properties values are initialized");
    }

    public String readProperty(Properties prop, String key) {
	return prop.getProperty(key);
    }

}
