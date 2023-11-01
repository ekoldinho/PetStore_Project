Feature: Petstore API functions are to be tested


  Scenario: Creation of a pet, uploading an image, calling through get, updating and deleting
    When A new pet is to be added to the store
    And An image to the pet is to be uploaded
    And The pet is to be updated
    And Pets are to be found by status
    And A pet is to be found by ID
    And The pet is to be updated with form data
    Then The pet is to be deleted