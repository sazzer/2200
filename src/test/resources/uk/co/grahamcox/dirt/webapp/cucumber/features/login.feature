@wip
Feature: Logging in

  Scenario: Wrong Password gives an error
    Given that I visit the home page
    When I log in as user "graham@grahamcox.co.uk" with password "wrongpassword"
    Then I am not logged in
