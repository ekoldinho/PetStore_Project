Feature: Petstore API functions are being tested


  Scenario: Store feature of Petstore API is under test
    When A new order for a pet has been placed
    And Purchase order has been found by ID
    And Pet inventories are returned
    Then Purchase order has been deleted by ID
