package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.hamcrest.core.Is;

import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

public class BDDStyleMethod {

	public static void SimpleGETPost(String postNumber) {
		given().contentType(ContentType.JSON).when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
		// then().body("author", equalTo("Sandeep")); //Can Also be used instead of
		// Hamcrest is
				then().body("author", is("Sandeep")).statusCode(200);
	}

	public static void PerformContainCollection() {
		given().contentType(ContentType.JSON).when().get("http://localhost:3000/posts/").then()
				.body("author", containsInAnyOrder("Sandeep", "Sandeep Singh", null)).statusCode(200);
	}

	public static void PerformPathParameter() {
		given().contentType(ContentType.JSON).with().pathParam("post", 1).when()
				.get("http://localhost:3000/posts/{post}").then().body("author", containsString("Sandeep"))
				.statusCode(200);
	}

	public static void PerformQueryParameter() {
		given().contentType(ContentType.JSON).queryParam("id", 1).when().get("http://localhost:3000/posts/").then()
				.body("author", hasItem("Sandeep")).statusCode(200);
	}

	public static void PerformPOSTWithBodyParameter() {
		HashMap<String, String> postContent = new HashMap<>();
		postContent.put("id", "5");
		postContent.put("title", "Robot framework");
		postContent.put("author", "S Singh");

		given().contentType(ContentType.JSON).with().body(postContent).when().post("http://localhost:3000/posts").then()
				.body("author", Is.is("S Singh"));

	}
}
