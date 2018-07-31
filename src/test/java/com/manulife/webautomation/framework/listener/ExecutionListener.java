package com.manulife.webautomation.framework.listener;

import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;
import com.manulife.webautomation.framework.common.CommonVariable;
import com.manulife.webautomation.framework.exception.PropertyNotFoundException;
import com.manulife.webautomation.framework.report.GenerateTestReport;
import com.manulife.webautomation.framework.utils.LoadConfiguration;

public class ExecutionListener implements IExecutionListener {
    private static Logger logger = LogManager.getLogger(ExecutionListener.class);
    protected static Properties prop;

    @Override
    public void onExecutionStart() {
	logger.info("Loading and initializing properties file ....");
	try {
	    prop = LoadConfiguration.initializeConfigurationFromFile(System.getProperty("user.dir") + "/src/test/java/resources/application.properties");
	} catch (IOException | PropertyNotFoundException e) {
	    logger.info("Error in Loading properties file....." + System.getProperty("user.dir") + "/src/test/java/resources/application.properties");
	    logger.error(e.getMessage());
	}
	new CommonVariable().init(prop);
	logger.info("Execution start method is completed");
    }

    @Override
    public void onExecutionFinish() {
	logger.info("Start creating all cucumber reports");
	try {
	    GenerateTestReport.generateCucumberCoverageOverviewReport();
	    GenerateTestReport.generateCucumberUsageReport();
	    GenerateTestReport.generateCucumberChartReport();
	    GenerateTestReport.generateCucumberResultsOverviewReport();
	    GenerateTestReport.generateCucumberDetailedResultsReport();
	    GenerateTestReport.generateCucumberFeatureOverviewReport();
	    GenerateTestReport.generateCucumberJVMReport();
	    GenerateTestReport.openCucumberHtmlReport();
	    logger.info("All the cucumber report creation is completed");
	} catch (Exception e) {
	    logger.info("Errors in creating cucucmber reports");
	    logger.error(e.getMessage());
	}
    }
}
