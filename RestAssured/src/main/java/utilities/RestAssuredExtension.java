package utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {

	public static RequestSpecification Request;

	public RestAssuredExtension() {
		// Arrange
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri("http://localhost:3000");
		builder.setContentType(ContentType.JSON);
		var requestSpec = builder.build();
		Request = RestAssured.given().spec(requestSpec);
	}

	public static void GetOpsWithPathParameter(String url, Map<String, String> pathParams) {
		// Act
		Request.pathParams(pathParams);
		try {
			Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static ResponseOptions<Response> GetOps(String url) {
		// Act
		try {
			return Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResponseOptions<Response> GetOpsQureyParams(String url, String queryParams) {
		// Act
		try {
			Request.queryParam(queryParams);
			return Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResponseOptions<Response> PostOpsWithBodyAndPathParams(String url, Map<String, String> pathparams,
			Map<String, String> body) {
		Request.pathParams(pathparams);
		Request.body(body);
		return Request.post(url);
	}

	public static ResponseOptions<Response> PostOpsWithBody(String url, Map<String, String> body) {
		Request.body(body);
		return Request.post(url);
	}

	public static ResponseOptions<Response> DeleteOpsWithPathParams(String url, Map<String, String> pathParams) {
		Request.pathParams(pathParams);
		return Request.delete(url);
	}

	public static ResponseOptions<Response> GetWithPathParams(String url, Map<String, String> pathParams) {
		Request.pathParams(pathParams);
		return Request.get(url);
	}
}