package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//userEndPoins.java
//which is having only CRUD operations-Create,read,Update,Delete request to the user API

public class UserEndPoints {
//method created for getting URL's from properties file
	
	
	
	public static Response  CreateUser(User payload){
	
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(routes.post_url);
		
		
		return response;
	}
	public static Response readUser(String userName) {
		Response  response=given()
				.pathParam("username", userName)
				.when()
				.get(routes.get_url);
				return response;
				
	}
	public static Response  UpdateUser(String userName,User payload){
		
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(routes.update_url);
		
		
		return response;
	}	
	public static Response deleteUser(String userName) {
		
		Response response = given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        .pathParam("username", userName)  // Corrected from .param
	        .when()
	        .delete(routes.delete_url);
	        

	    return response;
	}

}
