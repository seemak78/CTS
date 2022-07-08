Feature: Search Flight
 
  @chrome
  Scenario Outline: Search Round Trip Flight
    Given : I am at the login home page
    When : I enter username and password to login
    And : I am logged in with message "<message>" succesfully
    And : I search from "<fromcity>" to "<tocity>" after <addDaysFromToday> days from today for <addDaysToFromToday> days to find flight
    When : Find number of flight records
    And : I am clicking on Book 

    Examples: 
     | message                        | fromcity | tocity  |addDaysFromToday | addDaysToFromToday |
      | Welcome to Insider Travel Club | ORD      | LAS     |              60 |                  4 |
    # | Welcome to Insider Travel Club | SFO      | LCY     |              60 |                  4 |
    #  | Welcome to Insider Travel Club | BOM      | DEL     |              60 |                  4 |
    # | Welcome to Insider Travel Club | SYD      | MEL     |              60 |                  4 |
    # | Welcome to Insider Travel Club | LHR      | JFK     |              60 |                  4 |
    # | Welcome to Insider Travel Club | LHR      | DXB     |              60 |                  4 |
    # | Welcome to Insider Travel Club | AUH      | BOM     |              60 |                  4 |
    # | Welcome to Insider Travel Club | SIN      | LON     |              60 |                  4 |
    # | Welcome to Insider Travel Club | DFW      | CLL     |              60 |                  4 |
    # | Welcome to Insider Travel Club | CGK      | DPS     |              60 |                  4 |
    # | Welcome to Insider Travel Club | CPT      | JNB     |              60 |                  4 |
    # | Welcome to Insider Travel Club | JFK      | LAX     |              60 |                  4 |
    # | Welcome to Insider Travel Club | LOS      | SFO     |              60 |                  4 |
    # | Welcome to Insider Travel Club | BLR      | DEL     |              60 |                  4 |
    # | Welcome to Insider Travel Club | LGA      | HND     |              60 |                  4 |
    # | Welcome to Insider Travel Club | MAD      | BCN     |              60 |                  4 |
    # | Welcome to Insider Travel Club | HKG      | SIN     |              60 |                  4 |
    # | Welcome to Insider Travel Club | GRU      | GIG     |              60 |                  4 |
    # | Welcome to Insider Travel Club | SYD      | DXB     |              60 |                  4 |
    # | Welcome to Insider Travel Club | YYZ      | LHR     |              60 |                  4 |
      # 0 search location  | Welcome to Insider Travel Club |KLH	      | USA    |              60 |                  4 |