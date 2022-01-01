package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification request;
	ResponseSpecification   responseSpec ;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add place payload")
	public void add_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		request =	given().spec(requestSpecification())
			.body(data.addPlacePayload()).log().all(); 
	}
	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		
		 responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
					.expectContentType(ContentType.JSON).build();
		 
		 response = request.when()
				.post("/maps/api/place/add/json")
				.then()
				.spec(responseSpec).extract().response();
	}
	@Then("the api call got success with the status code {int}")
	public void the_api_call_got_success_with_the_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    
		assertEquals(response.getStatusCode(),200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    String resp= response.asString();
	    
	    JsonPath js = new JsonPath(resp);
	    assertEquals(js.get(keyValue).toString(),Expectedvalue);
	    
		
		
	}

}
