Feature: Adding mutiple addresses

  This feature file to do address adding

  Scenario: Add Addresses
    Given I have logged into Automation Practice
    Then I click My Account
    And I click on "My addresses"
    Then I add all addresses from test data
    And I log out Automation Practice