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
    When : I enter "<username>" and "<password>" to login
    And : I am logged in with message "<message>" succesfully
    And : I search in "<city>" to after <addDaysFromToday> from today for <addDaysToFromToday> days to find hotel
    When : Find number of records and lowest priced hotel

    Examples: 
      | username                       | password             | message                        | city                                                  | addDaysFromToday | addDaysToFromToday |
      | testclassic1@yopmail.com | Test@1234 | Welcome to Insider Travel Club | Los Angeles, California, United States of America     |               60 |                  4 |
    # | testclassic1@yopmail.com | Test@1234 | Welcome to Insider Travel Club | Miami And Vicinity, Florida, United States of America |               60 |                  4 |
    # | testclassic1@yopmail.com | Test@1234 | Welcome to Insider Travel Club | Taj Mahal, Agra, Uttar Pradesh, India                 |               60 |                  4 |
    # | testclassic1@yopmail.com | Test@1234 | Welcome to Insider Travel Club | Jammu, Jammu and Kashmir, India											 |               60 |                  4 |
