Feature: Search VR
 
  @chrome
  Scenario Outline: Search VR
    Given : I am at the login home page
    When : I enter "<username>" and "<password>" to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today within <addDaysToFromToday> days to VR
    When : Find number of VR

    Examples: 
      | username                      | password  |         message                |    city         |addDaysFromToday | addDaysToFromToday |
      | testclassic1@yopmail.com| Test@1234 | Welcome to Insider Travel Club | Big Island      |     60          |                  5 |
      
