package restassuredmethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.testng.Assert;

public class PatchRequestValidate {

	public static void main(String[] args) {
	
		// Step 1 :Declare base URI
				String BaseURI = "https://reqres.in/";
				String requestBody = "{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}";
				// See the expected result
				LocalDateTime Date = LocalDateTime.now();
				 String expectedDate = Date.toString().substring(0,10);
				 
				 JsonPath jsprequest = new JsonPath(requestBody);
				 String req_name = jsprequest.getString("name");
				 String req_job = jsprequest.getString("job");
				 
				 //Declare base URI
				 RestAssured.baseURI = BaseURI;
				
				//Step 2 : Configure request body
				int statusCode = given().header("content-Type", "application/json").body(requestBody).when().put("/api/users/2").then().extract().statusCode();
				System.out.println(statusCode);
				String responsebody = given().header("content-Type", "application/json").body(requestBody).when().patch("/api/users/2").then().log().all().extract().response().asString();
				System.out.println(responsebody);
				
				//Step 3 : Parse response body
				JsonPath jsp = new JsonPath(responsebody);
				String res_name = jsp.getString("name");
				String res_job = jsp.getString("job");
				String res_updatedAt = jsp.getString("updatedAt");
				String res_date = res_updatedAt.substring(0, 10);
				
				
				// Step 4 : Validate response body
				Assert.assertEquals(statusCode, 200);
				Assert.assertEquals(res_name, req_name);
				Assert.assertEquals(res_job, req_job);
				Assert.assertEquals(res_date, expectedDate);

			}

		}
