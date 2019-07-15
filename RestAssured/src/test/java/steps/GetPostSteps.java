package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;
import static org.hamcrest.Matchers.*;

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

}