@wip
Feature: Logging in

  Scenario Outline: Failed Login: <Reason>
    Given that I visit the home page
    When I log in as user "<Username>" with password "<Password>"
    Then I am not logged in
    And there is a login error of "<Error>"

    Examples:
      | Username               | Password      | Error                                | Reason                 |
      | graham@grahamcox.co.uk | wrongpassword | The password entered was invalid     | Wrong Password entered |
      |                        | wrongpassword | Please enter a username and password | No Username entered    |
      | graham@grahamcox.co.uk |               | Please enter a username and password | No Password entered    |
      |                        |               | Please enter a username and password | No details entered     |
