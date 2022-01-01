Feature: validating place API's

Scenario: verify if place is being sucussfully added using AddPlaceAPI
	Given Add place payload
	When user calls "AddPlaceAPI" with Post http request
	Then the api call got success with the status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"