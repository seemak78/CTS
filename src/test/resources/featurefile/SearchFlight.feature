Feature: Search Flight
 
  @chrome
  Scenario Outline: Search Round Trip Flight
    Given : I am at the login home page
    When : I enter "<username>" and "<password>" to login
    And : I am logged in with message "<message>" succesfully
    And : I search from "<fromcity>" to "<tocity>" after <addDaysFromToday> days from today for <addDaysToFromToday> days to find flight
    When : Find number of flight records

    Examples: 
      | username                         | password             | message                        | fromcity | tocity  |addDaysFromToday | addDaysToFromToday |
      | testclassic1@yopmail.com   | Test@1234 | Welcome to Insider Travel Club | SFO      | LCY     |              60 |                  4 |
      
