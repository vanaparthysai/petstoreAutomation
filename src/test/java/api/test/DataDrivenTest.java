package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

    public class DataDrivenTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = Dataprovider.class)
    public void TestPostUser(String userID, String username, String fname, String lname, String useremail, String pwd, String ph) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(fname);
        userPayload.setLastname(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.CreateUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "Username", dataProviderClass = Dataprovider.class)
    public void testDeleteUserByName(String username) {
        Response response = UserEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
