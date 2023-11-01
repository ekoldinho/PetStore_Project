Feature: Operations about user


  Scenario: User feature of Petstore API is under test
    When Creating list of users with given input array
    And Creating one single user
    And Calling a user by user name
    And Logging user into the system
    And Logging user out of the system
    Then Deleting the user