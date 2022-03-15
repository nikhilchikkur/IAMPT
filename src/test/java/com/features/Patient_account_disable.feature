Feature: Patient_Account_Disable 

  @Regression
  Scenario: Verify Patien Disable

    Given Login as admin
    Then disable the patient
    Then Login as patient