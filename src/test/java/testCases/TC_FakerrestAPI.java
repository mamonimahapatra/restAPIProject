package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

public class TC_FakerrestAPI {

	@Test(priority=1)
	public void getTest()
	{
		
		given()
		
		.when()
		   .get("https://fakerestapi.azurewebsites.net/api/v1/Users")
		   
		.then()
		   .statusCode(200)
		   .log().all();
		 
	}
	@Test(priority=2)
	public void postTest()
	{
		Map data=new HashMap();
		data.put("id","0");
		data.put("userName","string");
		data.put("password","string");
				
		Response response=given()
				         .header("Content-Type","application/json")
				         .body(data)
				         .contentType(ContentType.JSON)
				         
				         .when()
				         .post("https://fakerestapi.azurewebsites.net/api/v1/Users");
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8; v=1.0");
		Assert.assertEquals(response.getBody().path("userName"), "string");
		  
	}
	@Test(priority=3)
	public void putTest()
	{
		Map data1=new HashMap();
		data1.put("id","2");
		data1.put("userName","Mamoni");
		data1.put("password","Ma2345");
		
		Response response=given()
		                  .header("Content-Type","application/json")
		                  .body(data1)
		                  .pathParam("id", 20)
		                  .contentType(ContentType.JSON)
		                 .when()
		                  .put("https://fakerestapi.azurewebsites.net/api/v1/Users/{id}");
		 Assert.assertEquals(response.getBody().path("userName"), "Mamoni");
		 Assert.assertEquals(response.getStatusCode(),200);
		 int statusCode=response.getStatusCode();
		 System.out.println(statusCode);
		 
		 		                 
		 String name=response.getBody().path("userName");  
		 System.out.println(name);
		 
		 String password=response.getBody().path("password");  
		 System.out.println(password);
		 
		 int id=response.getBody().path("id");  
		 System.out.println(id);
		 
		 System.out.println(response.getHeader("Content-Type"));
		 
		 //String json=response.asString();
		 //Assert.assertEquals(json.contains("Record updated successfully"),true);
	}
	@Test(priority=4)
	public void deleteTest()
	{
		Response response= given()
		
		.when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/Users/20")
		
		.then()
		.statusCode(200)
		.extract().response();
		 
		/* String json=response.asString();
		 Assert.assertEquals(json.contains("Record deleted successfully"),true);*/
		int statusCode=response.getStatusCode();
		System.out.println(response.getStatusCode());
		
	}
}


