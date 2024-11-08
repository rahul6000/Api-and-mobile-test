package API.Endpoints;

import API.Payload.Store;
import API.Config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class StoreEndPoints  {

	public static Response createStoreOrder(Store payload) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.when()
			.post(Endpoints.STORE_POST_URL);
	}

	public static Response getStoreOrder(int orderId) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("orderId", orderId)
			.when()
			.get(Endpoints.STORE_GET_URL);
	}
	public static Response deleteStoreOrder(int orderId) {
		return given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("orderId", orderId)
			.when()
			.delete(Endpoints.STORE_DELETE_URL);
	}
}
