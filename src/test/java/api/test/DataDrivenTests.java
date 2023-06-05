package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.ExcelDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "AllData", dataProviderClass = ExcelDataProvider.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String pwd, String ph) {
        User userPayload = new User();


        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setPassword(fname);
        userPayload.setFirstName(lname);
        userPayload.setLastName(userEmail);
        userPayload.setEmail(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = ExcelDataProvider.class)
    public void testGetUserDetails(String userName) {
        Response response = UserEndPoints.readUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = ExcelDataProvider.class)
    public void testDeleteUser(String userName) {
        Response response = UserEndPoints.deleteUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
