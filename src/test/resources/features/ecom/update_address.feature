Feature: Update an address

  This feature file to do address updating

  Scenario: Update Address
    Given I have logged into Automation Practice
    Then I click My Account
    And I click on "My addresses"
    Then  I add an address from test data
    Then I update address "Random Name"
    And I log out Automation Practice