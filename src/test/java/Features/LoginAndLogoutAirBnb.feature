Feature: Testing login to logout function

  Scenario: User login and logout his account
    Given user launched the url
    When user login using his credentials
    Then taking screenshot of his account
    And logged his account