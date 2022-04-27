Feature: Search Hotel

  #@chrome
  #Scenario Outline: Login with wrong credentials
   # Given : I am at the login home page
    #When : I enter "<username>" and "<password>" to login
    #Then : I am logged in with message "<message>" succesfully
    #Examples: 
     # | username                         | password  | message                        |
      #| classic1@insidertravelclub.com   | Festivalofcolors@CTS | Welcome to Insider Travel Club |

  #Invalid login credentials
 
  @chrome
  Scenario Outline: Login and Search Hotel
    Given : I am at the login home page
    When : I enter username and password to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today for <addDaysToFromToday> days to find hotel
    When : Find number of records and lowest priced hotel

    Examples: 
      | message                        | city                                                  | addDaysFromToday | addDaysToFromToday |
      | Welcome to Insider Travel Club | Los Angeles, California, United States of America     |               60 |                  4 |
    # | Welcome to Insider Travel Club | Miami And Vicinity, Florida, United States of America |               60 |                  4 |
    # | Welcome to Insider Travel Club | Taj Mahal, Agra, Uttar Pradesh, India                 |               60 |                  4 |
    # | Welcome to Insider Travel Club | Jammu, Jammu and Kashmir, India											 |               60 |                  4 |
      | Welcome to Insider Travel Club | New York, New York, United States of America          |               60 |                  4 |
      | Welcome to Insider Travel Club | Chicago, Illinois, United States of America           |               60 |                  4 |
      | Welcome to Insider Travel Club | Houston, Texas, United States of America              |               60 |                  4 |
      | Welcome to Insider Travel Club | Phoenix, Arizona, United States of America            |               60 |                  4 |
      | Welcome to Insider Travel Club | Philadelphia, Pennsylvania, United States of America  |               60 |                  4 |
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
    # | Welcome to Insider Travel Club | Boston And Vicinity, Massachusetts, United States of America|         60 |                  4 |
      | Welcome to Insider Travel Club | Southwest Portland, Oregon, United States of America  |               60 |                  4 |
    # | Welcome to Insider Travel Club | Detroit And Vicinity, Michigan, United States of America|             60 |                  4 |
    # | Welcome to Insider Travel Club | Memphis And Vicinity, Tennessee, United States of America|            60 |                  4 |
      | Welcome to Insider Travel Club | Louisville Palace, Louisville, Kentucky, United States of America|    60 |                  4 |
      | Welcome to Insider Travel Club | Baltimore, Maryland, United States of America         |               60 |                  4 |
      | Welcome to Insider Travel Club | London, England, United Kingdom                       |               60 |                  4 |
      | Welcome to Insider Travel Club | Paris, France                                         |               60 |                  4 |
      | Welcome to Insider Travel Club | Rome, Lazio, Italy                                    |               60 |                  4 |
      | Welcome to Insider Travel Club | Barcelona, Barcelona, Catalonia, Spain                |               60 |                  4 |
      | Welcome to Insider Travel Club | Amsterdam, North Holland, Netherlands                 |               60 |                  4 |
      | Welcome to Insider Travel Club | Florence, Tuscany, Italy                              |               60 |                  4 |
      | Welcome to Insider Travel Club | Dubai, Dubai, United Arab Emirates                    |               60 |                  4 |
      | Welcome to Insider Travel Club | Bali, Rajsthan, India                                 |               60 |                  4 |
      | Welcome to Insider Travel Club | Phuket, Phuket Province, Thiland                      |               60 |                  4 |
      | Welcome to Insider Travel Club | Prague, Czech Repubic                                 |               60 |                  4 |
      | Welcome to Insider Travel Club | Madrid, Community of Madrid, Spain                    |               60 |                  4 |
      | Welcome to Insider Travel Club | Budapest, Hungary                                     |               60 |                  4 |
      | Welcome to Insider Travel Club | Mumbai, Maharashtra, India                            |               60 |                  4 |
      | Welcome to Insider Travel Club | Munich, Bavaria, Germany                              |               60 |                  4 |
      | Welcome to Insider Travel Club | Istanbul, Istanbul, Turkey                            |               60 |                  4 |
      | Welcome to Insider Travel Club | Toronto, Ontario, Canada                              |               60 |                  4 |
      | Welcome to Insider Travel Club | Singapore, Singapore                                  |               60 |                  4 |
      | Welcome to Insider Travel Club | Edinburgh, Scotland, United Kingdom                   |               60 |                  4 |
      | Welcome to Insider Travel Club | Manchester, New Hampshire, United States of America   |               60 |                  4 |
      | Welcome to Insider Travel Club | Orlando, Florida, United States of America            |               60 |                  4 |
      