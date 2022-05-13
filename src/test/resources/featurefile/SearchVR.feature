Feature: Search VR
 
  @chrome
  Scenario Outline: Search VR
    Given : I am at the login home page
    When : I enter username and password to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today within <addDaysToFromToday> days to VR
    When : Find number of VR

    Examples: 
     |         message                |    city         |addDaysFromToday | addDaysToFromToday |
     | Welcome to Insider Travel Club | ALASKA  |     60          |                  30|
      
