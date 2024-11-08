package API.Endpoints;

import API.Payload.User;
import API.Config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

	public static Response createUser(User payload) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.when()
			.post(Endpoints.POST_USER_URL);
	}


    	public static Response getUser(String username) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.when()
			.get(Endpoints.GET_USER_URL);
	}

	public static Response updateUser(String username, User payload) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
			.when()
			.put(Endpoints.UPDATE_USER_URL);
	}

	public static Response deleteUser(String username) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.when()
			.delete(Endpoints.DELETE_USER_URL);
	}
}
