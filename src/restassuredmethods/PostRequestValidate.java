package restassuredmethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
public class PostRequestValidate {

	public static void main(String[] args) {
		
		//Step 1 : Declare base URI
		String BaseURI = "https://reqres.in/";
		String requestBody = "{\r\n"
		 		+ "    \"name\": \"Neha\",\r\n"
		 		+ "    \"job\": \"leader\"\r\n"
		 		+ "}";
		
		//Step 2: See the expected results
		 LocalDateTime Date = LocalDateTime.now();
		 String expectedDate = Date.toString().substring(0,11);
		 
		 JsonPath jsprequest = new JsonPath(requestBody);
		 String req_name = jsprequest.getString("name");
		 String req_job = jsprequest.getString("job");
		 
		 //Step 3 : Declare Base URI
		RestAssured.baseURI = BaseURI;
		 
		 // Step 4 : Configure Request body
		int statusCode=given().header("Content-Type","application/json").body(requestBody).
						when().post("/api/users").
						then().extract().statusCode();
		System.out.println(statusCode);
		
       String responsebody = given().header("content-Type", "application/json").body(requestBody).
    		   				when().post("/api/users").
    		   				then().log().all().extract().response().asString();
 
        //step 3 : Parse response body
       		JsonPath jsp = new JsonPath(responsebody);
       		String res_name = jsp.getString("name");
       		String res_job = jsp.getString("job");
       		String res_createdAt = jsp.getString("createdAt");
       		String res_date = res_createdAt.substring(0, 11);
		  
       		//Step 4 : validate response body parameters
       		Assert.assertEquals(res_name, req_name);
       		Assert.assertEquals(res_job , req_job);
       		Assert.assertEquals(res_date, expectedDate);
       		Assert.assertEquals(statusCode, 201);

	}

}
