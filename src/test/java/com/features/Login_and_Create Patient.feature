Feature: Login and Create Patient

  @Regression
  Scenario: Verify the Patient Creation

    Given I have launched the Iampt application
    When I fill the Patient form
    When I login as Patient
    And I Fill the details