Feature: Validate Place API
  @AddPlace @Regression

  Scenario Outline: Verify if a customer can add a place using Add Place API
    Given add place payload with "<language>", "<address>", "<name>", and "<phoneNumber>"
    When user calls "/maps/api/place/add/json" with "<method>" http request
    Then The API request is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "/maps/api/place/get/json"

    Examples:
      | language | address          | name          | phoneNumber        | method |
      | sad      | 22 smart village | ahmed zakaria | (+91) 983 893 3937 | POST   |
  #  | English-EN | 23 smart village | ahmed saad    | (+91) 983 893 3937 | POST    |
  @DeletePlace @Regression

  Scenario Outline: verify if delete place function si working
  Given Delete place load
    When user calls "/maps/api/place/delete/json" with "<method>" http request
    Then The API request is success with status code 200
    And "status" in response body is "OK"

    Examples:
      | method |
      | POST   |