Feature: Search Hotel Detail

  @chrome
  Scenario Outline: Login and Search Hotel
    Given : I am at the login home page
    When : I enter username and password to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today for <addDaysToFromToday> days to find hotel
    When : Find number of records and lowest priced hotel
    And  : I am clicking on Select room
    When : I am clicking on book Now

    Examples: 
       	| message                        | city                                                  | addDaysFromToday | addDaysToFromToday |
       	| Welcome to Insider Travel Club | Los Angeles, California, United States of America     |               60 |                  4 |
       	| Welcome to Insider Travel Club | Phoenix, Arizona, United States of America            |               60 |                  4 |
				|Welcome to Insider Travel Club | Philadelphia, Pennsylvania, United States of America   |               60 |                  4 |
				| Welcome to Insider Travel Club | San Antonio, Texas, United States of America          |               60 |                  4 |
				| Welcome to Insider Travel Club | San Diego, California, United States of America       |               60 |                  4 |
				| Welcome to Insider Travel Club | Dallas, Texas, United States of America               |               60 |                  4 |
				| Welcome to Insider Travel Club | South San Jose, San Jose, California, United States of America|       60 |                  4 |
				| Welcome to Insider Travel Club | Austin, Texas, United States of America               |               60 |                  4 |
				| Welcome to Insider Travel Club | Jacksonville, Florida, United States of America       |               60 |                  4 |
				| Welcome to Insider Travel Club | Fort Worth, Texas, United States of America           |               60 |                  4 |
				| Welcome to Insider Travel Club | Columbus, Ohio, United States of America              |               60 |                  4 |
				| Welcome to Insider Travel Club | Indianapolis, Indiana, United States of America       |               60 |                  4 |
				| Welcome to Insider Travel Club | Charlotte, North Carolina, United States of America   |               60 |                  4 |
				| Welcome to Insider Travel Club | San Francisco, California, United States of America   |               60 |                  4 |
				| Welcome to Insider Travel Club | Seattle, Washington, United States of America         |               60 |                  4 |
				| Welcome to Insider Travel Club | Denver, Colorado, United States of America            |               60 |                  4 |
				| Welcome to Insider Travel Club | Washington, of Colummbia, United States of America    |               60 |                  4 |
				| Welcome to Insider Travel Club | South Nashville, Nashville, Tennessee,  United States of America|     60 |                  4 |
				| Welcome to Insider Travel Club | Oklahoma City, Oklahoma, United States of America     |               60 |                  4 |
				| Welcome to Insider Travel Club | El Paso, Texas, United States of America              |               60 |                  4 |
        # No hotels for this location | Welcome to Insider Travel Club | Las Tunas, Las Tunas, Cuba       |               60 |                  4 |