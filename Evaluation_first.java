package Assignment;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class Evaluation_first {
	
	@Test
	public void setUp() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/posts";
	}
	@Test
	public void testGet() {
		given().when().get("/posts/1").then().statusCode(200).body("id", equalTo(1));
	}
	@Test
	public void testPost() {
		String json ="{\"title\":\"foo\",\"body\": \"bar\", \"userId\": 1}";
		given().header("Content-type","application/json").
		body(json).when().post("/posts").then().statusCode(201).body("title",equalTo("foo")).body("body",equalTo("bar")).body("userId",equalTo(1));
	}
	@Test
	public void testPut() {
		String json="{\"id\":1,\"title\":\"updated title\",\"body\": \"updated body\",\"userId\": 1}";
		given().header("Content-type","application/json").
		body(json).when().put("/posts/1").then().statusCode(200).body("title",equalTo("updated title")).body("body",equalTo("updated body"));
	}
	@Test
	public void testPatch() {
		String json="{\"title\":\"patched title\"}";
		given().header("Content-type","application/json").
		body(json).when().patch("/posts/1").then().statusCode(200).body("title",equalTo("patched title"));	
	}
	
	public void TestDelete() {
		when().delete("/posts/1").then().statusCode(anyOf(equalTo(200),equalTo(204)));
	}
}
