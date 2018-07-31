package com.manulife.webautomation.runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = "com/manulife/webautomation/stepdefinition", features = "src/test/java/com/manulife/webautomation/feature", plugin = { "html:target/cucumber-htmlreport",
	"json:target/cucumber-report.json", "pretty:target/cucumber-pretty.txt", "usage:target/cucumber-usage.json"  }, tags = { "@Regression" }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    //AND tags = { "@Regression","@p1" })
    //OR tags = { "@Regression,@p1" })
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
    }

}
