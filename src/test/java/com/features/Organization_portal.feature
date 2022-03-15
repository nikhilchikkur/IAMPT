Feature: Organization Portal

  @Regression
  Scenario: Activate and Deactivate organization

    Given Sign in to organization portal
    When Activate and Deactivate organiztaion
    When Verify state of organization
 