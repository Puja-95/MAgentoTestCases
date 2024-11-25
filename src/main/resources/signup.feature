Feature: User Signup

  Scenario: Successful Signup
    Given the user is on the Signup page
    When the user enters their details
      | field     | value           |
      | Username  | johndoe         |
      | Email     | johndoe@test.com |
      | Password  | Password123     |
    And the user submits the form
    Then a confirmation message should be displayed
