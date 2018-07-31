package com.manulife.webautomation.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.manulife.webautomation.framework.exception.PropertyNotFoundException;

public class LoadConfiguration {
    private static Logger logger = LogManager.getLogger(LoadConfiguration.class);
    private static Properties props = new Properties();
    private static boolean isLoaded = false;

    public static Properties initializeConfigurationFromFile(String configFileName) throws IOException, PropertyNotFoundException {
	if (!(configFileName.endsWith(".properties") || configFileName.endsWith(".PROPERTIES"))) {
	    throw new PropertyNotFoundException("Project configuration file should have '.properties' extension!");
	}
	try {
	    if (!isLoaded) {
		logger.info("Loading project configuration file '" + configFileName + "'");
		props.load(new FileInputStream(new File(configFileName)));
		isLoaded = true;
	    }
	} catch (IOException e) {
	    logger.info("Unable to load configuration file '" + configFileName + "'!");
	    throw new IOException("Unable to load configuration file '" + configFileName + "'!");
	}
	return props;
    }
}
