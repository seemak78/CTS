Feature: Search Hotel for every 15 minutes

  @chrome
  Scenario Outline: Login and Search Hotel
    Given : I am at the login home page
    When : I enter username and password to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today for <addDaysToFromToday> days to find hotel
    When : Find number of records and lowest priced hotel

    Examples: 
       | message                        | city                                                  | addDaysFromToday | addDaysToFromToday |
     #   | Welcome to Insider Travel Club | Los Angeles, California, United States of America    |               60 |                  4 |
         | Welcome to Insider Travel Club | Las Tunas, Las Tunas, Cuba       |               60 |                  4 |