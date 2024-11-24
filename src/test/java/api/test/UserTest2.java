package api.test;
 import org.apache.logging.log4j.Logger;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {

	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setupData() {
		
faker=new Faker();	
userpayload=new User();
userpayload.setId(faker.idNumber().hashCode());
userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
	userpayload.setLastname(faker.name().lastName());
	userpayload.setEmail(faker.internet().safeEmailAddress());
	userpayload.setPhone(faker.internet().password(5,10));
	userpayload.setPhone(faker.phoneNumber().cellPhone());
	//logs
	logger =LogManager.getLogger(this.getClass());
	}
@Test(priority = 1)
public void testpostUser() {
	logger.info("***creating user****");
	Response res=UserEndPoints2.CreateUser(userpayload);
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(), 200);
	logger.info("***user is created****");
}
@Test(priority = 2)
public void testUserByName() {
	logger.info("***reading user info****");
	Response res=UserEndPoints2.readUser(this.userpayload.getUsername());
res.then().log().all();
Assert.assertEquals(res.getStatusCode(), 200);
logger.info("***user info displayed****");
}
@Test(priority = 3)
public void testUpdateUserByName() {
	//update data using same payload
	logger.info("***updating user info****");
userpayload.setId(faker.idNumber().hashCode());
userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
	userpayload.setLastname(faker.name().lastName());
	userpayload.setEmail(faker.internet().safeEmailAddress());
	userpayload.setPhone(faker.internet().password(5,10));
	userpayload.setPhone(faker.phoneNumber().cellPhone());
	
	Response res=UserEndPoints2.UpdateUser(this.userpayload.getUsername(), userpayload);
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(), 200);	
	//checking data after update
	Response resAfterUpdate=UserEndPoints2.readUser(this.userpayload.getUsername());
	Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
	logger.info("***displayed updated user info****");
}
@Test(priority = 4)
public void testDeleteUserByName() {
	//Delete data using username
	logger.info("***delete user info****");
	Response res=UserEndPoints2.deleteUser(this.userpayload.getUsername());
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(), 200);
	logger.info("***user deleted****");
}
}
