Feature: Search Activity
 
  @chrome
  Scenario Outline: Search Activities
    Given : I am at the login home page
    When : I enter username and password to login
    And : I am logged in with message "<message>" succesfully
    When : I search in "<city>" to after <addDaysFromToday> from today for <addDaysToFromToday> days to find activity
    When : Find number of activities         																											
 
    Examples: 
      |         message                |    city         |addDaysFromToday | addDaysToFromToday |
     | Welcome to Insider Travel Club | Big Island      |     60          |                  5 |
      
