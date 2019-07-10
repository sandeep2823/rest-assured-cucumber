package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.*;

@CucumberOptions(features = "./src/test/java/features", glue = { "steps" })
public class GetPostSteps {

	private static ResponseOptions<Response> response;

	@Given("^I perform GET operation for \"([^\"]*)\"$")
	public void i_perform_GET_opertion_for(String url) throws Throwable {
		response = RestAssuredExtension.GetOps(url);
	}

	@Then("^I should see the author name as \"([^\"]*)\"$")
	public void i_should_see_the_author_name_as(String authorName) throws Throwable {
		assertThat(response.getBody().jsonPath().get("author"), hasItem("Sandeep"));
	}

	@Then("^I should see the author names$")
	public void i_should_see_the_author_names() throws Throwable {
		BDDStyleMethod.PerformContainCollection();
	}

	@Then("^I should verify GET path parameter$")
	public void i_should_verify_GET_path_parameter() throws Throwable {
		BDDStyleMethod.PerformPathParameter();
	}

	@Then("^I should verify GET query parameter$")
	public void i_should_verify_GET_query_parameter() throws Throwable {
		BDDStyleMethod.PerformQueryParameter();
	}

	@Given("^I perform POST operation for \"([^\"]*)\"$")
	public void i_perform_POST_operation_for(String arg1) throws Throwable {
		BDDStyleMethod.PerformPOSTWithBodyParameter();
	}
	
	@Given("^I Perform POST operation for \"([^\"]*)\" with body$")
	public void i_Perform_POST_operation_for_with_body(String url, DataTable table) throws Throwable {
		var data = table.raw();
		//Set the body 
		
		HashMap<String, String> body = new HashMap<>();
		body.put("name", data.get(1).get(0));
		
		//path params
		HashMap<String, String> pathParams = new HashMap<>();
		pathParams.put("profileNo", data.get(1).get(1));
		
		//perform POST operation
		response = RestAssuredExtension.PostOpsWithBodyAndPathParams(url, pathParams, body);
	}

	@Then("^I should see the body has name as \"([^\"]*)\"$")
	public void i_should_see_the_body_has_name_as(String name) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
	}

}