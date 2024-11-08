package API.Test;

import API.Endpoints.UserEndPoints;
import API.Payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTests {

	Faker faker;
	User userPayload;


	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void testCreateUser() {
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		Response response = UserEndPoints.getUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 3)
	public void testUpdateUser() {
		// Update the user's details
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().all();

		// Assert the update was successful
		Assert.assertEquals(response.getStatusCode(), 200);

		// Verify the updated details
		Response getUserResponse = UserEndPoints.getUser(userPayload.getUsername());
		getUserResponse.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());
		response.then().log().all();

		// Assert that deletion was successful
		Assert.assertEquals(response.getStatusCode(), 200);

		// Verify the user no longer exists
		Response getUserResponse = UserEndPoints.getUser(userPayload.getUsername());
		getUserResponse.then().log().all();
		Assert.assertEquals(getUserResponse.getStatusCode(), 404);
	}

	@Test(priority = 5)
	public void testUserNotFound() {
		String nonExistentUsername = "nonExistentUser12345";

		// Try to get a user that doesn't exist
		Response response = UserEndPoints.getUser(nonExistentUsername);
		response.then().log().all();

		// Assert that a 404 status code is returned
		Assert.assertEquals(response.getStatusCode(), 404);
	}


}
