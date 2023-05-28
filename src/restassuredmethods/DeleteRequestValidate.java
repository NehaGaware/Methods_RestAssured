package restassuredmethods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class DeleteRequestValidate {

	public static void main(String[] args) {
		// //Step 1 : Declare base URI
		RestAssured.baseURI = "https://reqres.in/";
		
		// Step 2 : Configure Request body
		int statusCode=given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
		System.out.println(statusCode);

	}

}
