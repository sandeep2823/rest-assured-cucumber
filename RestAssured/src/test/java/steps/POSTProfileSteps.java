package steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsNot;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

public class POSTProfileSteps {

	private static ResponseOptions<Response> response;

	@Given("^I Perform POST operation for \"([^\"]*)\" with body$")
	public void i_Perform_POST_operation_for_with_body(String url, DataTable table) throws Throwable {
		var data = table.raw();
		// Set the body

		HashMap<String, String> body = new HashMap<>();
		body.put("name", data.get(1).get(0));

		// path params
		HashMap<String, String> pathParams = new HashMap<>();
		pathParams.put("profileNo", data.get(1).get(1));

		// perform POST operation
		response = RestAssuredExtension.PostOpsWithBodyAndPathParams(url, pathParams, body);
	}

	@Then("^I should see the body has name as \"([^\"]*)\"$")
	public void i_should_see_the_body_has_name_as(String name) throws Throwable {
		assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
	}

	@Given("^I ensure to perform POST operation for \"([^\"]*)\" with body as$")
	public void i_ensure_to_perform_POST_operation_for_with_body_as(String url, DataTable table) throws Throwable {
		var data = table.raw();
		Map<String, String> body = new HashMap<>();
		body.put("id", data.get(1).get(0));
		body.put("title", data.get(1).get(1));
		body.put("author", data.get(1).get(2));

		// Perform POST Operation

		RestAssuredExtension.PostOpsWithBody(url, body);
	}

	@Given("^I perform DELETE operation for \"([^\"]*)\"$")
	public void i_perform_DELETE_operation_for(String url, DataTable table) throws Throwable {

		var data = table.raw();
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("postid", data.get(1).get(0));

		// Perform DELETE operation
		RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
	}

	@Given("^I perform the GET operation with path parameter for \"([^\"]*)\"$")
	public void i_perform_the_GET_operation_with_path_parameter_for(String url, DataTable table) throws Throwable {
		var data = table.raw();
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("postid", data.get(1).get(0));

		response = RestAssuredExtension.GetWithPathParams(url, pathParams);
	}

	@Then("^I should not be able to see the body with title as \"([^\"]*)\"$")
	public void i_should_not_be_able_to_see_the_body_with_title_as(String title) throws Throwable {
		assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
	}
}
