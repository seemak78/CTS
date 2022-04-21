Feature: Search Activity
 
  @chrome
  Scenario Outline: Search Activities
    Given : I am at the login home page
    When : I enter "<username>" and "<password>" to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today for <addDaysToFromToday> days to find activity
    When : Find number of activities         																											
 
    Examples: 
      | username                      | password  |         message                |    city         |addDaysFromToday | addDaysToFromToday |
      | testclassic1@yopmail.com| Test@1234 | Welcome to Insider Travel Club | Big Island      |     60          |                  5 |
      
