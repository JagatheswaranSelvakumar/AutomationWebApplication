package com.manulife.webautomation.steplibrary;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import com.manulife.webautomation.framework.common.CommonLibrary;
import com.manulife.webautomation.framework.common.CommonVariable;
import com.manulife.webautomation.pageobject.HomePage;
import cucumber.api.DataTable;

public class HomeScreenLibrary extends CommonLibrary {
    public static void verifyHomeScreen() {
	try {
	    if (CommonVariable.breakPoint.equalsIgnoreCase("Mobile")) {
		findElement(HomePage.signIn_Mobile_BTN);
	    } else {
		findElement(HomePage.signIn_BTN);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void selectMenuFromHomeScreen(String menuOption) {
	try {
	    switch (menuOption) {
	    case "Group plans":
		if (CommonVariable.breakPoint.equalsIgnoreCase("Mobile")) {
		    click(HomePage.menu_Mobile_BTN);
		    click(HomePage.groupPlans_Mobile_LNK);
		} else {
		    click(HomePage.groupPlans_LNK);
		}
		break;
	    default:
		break;
	    }
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	    e.printStackTrace();
	}
    }

    public static void selectMenuFromGroupPlans(String menuOption) {
	try {
	    switch (menuOption) {
	    case "Group benefits":
		if (CommonVariable.breakPoint.equalsIgnoreCase("Mobile")) {
		    click(HomePage.groupBenefits_Mobile_LNK);
		} else {
		    click(HomePage.groupBenefits_LNK);
		}
		break;
	    default:
		break;
	    }
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	    e.printStackTrace();
	}
    }

    public static void clicksButton(String menuOption) {
	try {
	    switch (menuOption) {
	    case "Submit a cliam":
		click(HomePage.submitCliam_BTN);
		break;
	    case "Sign in":
		click(HomePage.signin_BTN);
		for (String windowName : driver.getWindowHandles()) {
		    driver.switchTo().window(windowName);
		}
		break;
	    default:
		break;
	    }
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	    e.printStackTrace();
	}
    }

    public static void verifyButton(String buttonName) {
	boolean verifyButton = false;
	try {
	    switch (buttonName) {
	    case "Submit a cliam":
		verifyButton = isElementDisplayed(HomePage.submitCliam_BTN);
		break;
	    case "Sign in":
		verifyButton = isElementDisplayed(HomePage.signin_BTN);
		break;
	    default:
		break;
	    }
	    Assert.assertTrue(verifyButton);
	} catch (Exception e) {
	    Assert.assertTrue(verifyButton, e.getMessage());
	    e.printStackTrace();
	}
    }

    public static void enterSignInDetails(DataTable signinDetails) {
	List<Map<String, String>> dataTableListOfMap = signinDetails.asMaps(String.class, String.class);
	String planContractNumber = dataTableListOfMap.get(0).get("Plan contract number");
	String memberCertificateNumber = dataTableListOfMap.get(0).get("Member certificate number");
	String password = dataTableListOfMap.get(0).get("Password");
	type(HomePage.planContractNumber_TXT, planContractNumber);
	type(HomePage.memberCertificateNumber_TXT, memberCertificateNumber);
	type(HomePage.password, password);
    }

    public static void clicksButtonGBScreen(String buttonName) {
	try {
	    click(HomePage.GB_SignIn_BTN);
	} catch (Exception e) {
	    Assert.assertTrue(false, e.getMessage());
	    e.printStackTrace();
	}
    }

    public static void verifyGBLoggedInScreen() {
	boolean verifyLabel = false;
	try {
	    verifyLabel = isElementDisplayed(HomePage.submitACaim_LBL);
	    Assert.assertTrue(verifyLabel);
	} catch (Exception e) {
	    Assert.assertTrue(verifyLabel, e.getMessage());
	    e.printStackTrace();
	}
    }
}
