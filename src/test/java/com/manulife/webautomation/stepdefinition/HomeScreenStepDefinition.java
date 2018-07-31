package com.manulife.webautomation.stepdefinition;

import com.manulife.webautomation.steplibrary.HomeScreenLibrary;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeScreenStepDefinition {

    @Given("^the user is on home page$")
    public void user_on_Home_Page() {
	HomeScreenLibrary.verifyHomeScreen();
    }

    @When("^user selects \"([^\"]*)\" option from the home page$")
    public void user_selects_option_from_the_home_page(String menuOption) throws Throwable {
	HomeScreenLibrary.selectMenuFromHomeScreen(menuOption);
    }

    @And("^user selects \"([^\"]*)\" from the Group plans$")
    public void user_selects_from_the_Group_plans(String menuOption) {
	HomeScreenLibrary.selectMenuFromGroupPlans(menuOption);
    }
    
    @And("^user clicks \"([^\"]*)\" button$")
    public void user_clicks_button(String menuOption) {
	HomeScreenLibrary.clicksButton(menuOption);
    }

    @Then("^user verifies \"([^\"]*)\" button is displayed$")
    public void user_verifies_button_is_displayed(String buttonName) {
	HomeScreenLibrary.verifyButton(buttonName);
    }
    
    @And("^user enter Signin details$")
    public void user_enter_Signin_details(DataTable signinDetails) {
	HomeScreenLibrary.enterSignInDetails(signinDetails);
    }
    
    @And("^user clicks \"([^\"]*)\" button in group benefits screen$")
    public void user_clicks_button_in_group_benefits_screen(String buttonName) {
	HomeScreenLibrary.clicksButtonGBScreen(buttonName);
    }
    
    @Then("^user veries group benefits logged in screen$")
    public void user_veries_group_benefits_logged_in_screen() {
	HomeScreenLibrary.verifyGBLoggedInScreen();
    }
}
