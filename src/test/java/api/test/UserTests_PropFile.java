package api.test;


import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests_PropFile {

    public Logger logger;
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Creating Log Manager
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Debugging ....");
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("************************Creating a User *************************");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserByName() {

        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
//Update the payload with different property values
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response response = UserEndPoints.updatedUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body().statusCode(200);//Chai Assertion
        Assert.assertEquals(response.getStatusCode(), 200);//TestNG Assertion


        //Checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

        // Convert the response body to a JSONPath object
        JsonPath jsonPath = responseAfterUpdate.jsonPath();

        // Validate JSON fields using JSONPath expressions
        String firstName = jsonPath.getString("firstName");
        String lastName = jsonPath.getString("lastName");
        String email = jsonPath.getString("email");

        Assert.assertEquals(firstName, this.userPayload.getFirstName());
        Assert.assertEquals(lastName, this.userPayload.getLastName());
        Assert.assertEquals(email, this.userPayload.getEmail());

    }

    @Test(priority = 4)
    public void testDeleteUserByName() {

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
