package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

    // Method to retrieve URLs from the properties file
    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response CreateUser(User payload) {
        String post_URL = getURL().getString("post_url");
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
            .log().all() // Logs request details
            .when()
            .post(post_URL)
            .then()
            .log().all() // Logs response details
            .extract().response();
        return response;
    }

    public static Response readUser(String userName) {
        String get_URL = getURL().getString("get_url");
        Response response = given()
            .pathParam("username", userName)
            .log().all()
            .when()
            .get(get_URL)
            .then()
            .log().all()
            .extract().response();
        return response;
    }

    public static Response UpdateUser(String userName, User payload) {
        String update_URL = getURL().getString("update_url");
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("username", userName)
            .body(payload)
            .log().all()
            .when()
            .put(update_URL)
            .then()
            .log().all()
            .extract().response();
        return response;
    }

    public static Response deleteUser(String userName) {
        String delete_URL = getURL().getString("delete_url");
        Response response = given()
            .pathParam("username", userName)
            .log().all()
            .when()
            .delete(delete_URL)
            .then()
            .log().all()
            .extract().response();
        return response;
    }
}
