@SubmitClaims
Feature: As a user ,I want to Submit a cliam,so that I can get the cliam amount which submitted

  @VerifySubmitClaimButton @Smoke @Regression @P1
  Scenario: User verify the submit a claim button in Group benefits page
    Given the user is on home page
    When user selects "Group plans" option from the home page
    And user selects "Group benefits" from the Group plans
    Then user verifies "Submit a cliam" button is displayed

  @VerifySigninButton @Regression @P1 @P2
  Scenario: User is verify whether able to signin through Group benefits page
    Given the user is on home page
    When user selects "Group plans" option from the home page
    And user selects "Group benefits" from the Group plans
    And user clicks "Submit a cliam" button
    And user clicks "Sign in" button
    And user enter Signin details
      | Plan contract number | Member certificate number | Password |
      |              1234567 |                1234567890 | Password |
    And user clicks "Sign in" button in group benefits screen
    Then user veries group benefits logged in screen
