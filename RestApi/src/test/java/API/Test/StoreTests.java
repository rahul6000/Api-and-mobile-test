package API.Test;

import API.Endpoints.StoreEndPoints;
import API.Payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class StoreTests {


	Faker faker;
	Store storePayload;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		storePayload = new Store();
		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setPetId(faker.number().numberBetween(1, 100));
		storePayload.setQuantity(faker.number().numberBetween(1, 10));
		storePayload.setStatus("placed");
		storePayload.setComplete(true);
	}

	@Test(priority = 1)
	public void testCreateStoreOrder() {
		Response response = StoreEndPoints.createStoreOrder(storePayload);
		response.then().log().all();
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);

		// Validate the response contains the expected ID
		int createdOrderId = response.jsonPath().getInt("id");
		Assert.assertEquals(createdOrderId, storePayload.getId());
		Response getResponse = StoreEndPoints.getStoreOrder(createdOrderId);
		getResponse.then().log().all();
		Assert.assertEquals(getResponse.jsonPath().getString("status"), "placed");
	}

	@Test(priority = 2)
	public void testGetStoreOrder() {
		// Log the ID being fetched
		System.out.println("Fetching order with ID: " + storePayload.getId());
		Response response = StoreEndPoints.getStoreOrder(storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testDeleteStoreOrder() {
		Response response = StoreEndPoints.deleteStoreOrder(storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void testGetNonExistentStoreOrder() {
		// Use an invalid ID that is unlikely to exist
		int nonExistentId = 999999;
		Response response = StoreEndPoints.getStoreOrder(nonExistentId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404); // Expecting Not Found
	}

	@Test(priority = 5)
	public void testDeleteNonExistentStoreOrder() {
		int nonExistentId = 999999; // Use an invalid ID that is unlikely to exist
		Response response = StoreEndPoints.deleteStoreOrder(nonExistentId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404); // Expecting Not Found
	}


}
