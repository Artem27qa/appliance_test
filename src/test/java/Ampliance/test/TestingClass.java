package Ampliance.test;


import Ampliance.app.Application;
import Ampliance.app.ProfilePage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.testng.Assert;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.*;

import java.io.IOException;


public class TestingClass {
    private String userID;
    protected static Application application;
    protected static ProfilePage profilePage;

    @BeforeSuite
    public void setUp() {
        application = new Application();
        application.start();
        profilePage = new ProfilePage(application);
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        application.stop();
    }

    @DataProvider
    public Object[][] userID() {
        return new Object[][]{
                {"6wl"}
        };
    }

    @Test(description = "checking the API response for the user", dataProvider = "userID")
    public void checkingJsonResponse(String userID) {
        RestAssured.baseURI = "https://api.github.com/";
        when()
                .get("/users/" + userID)
                .then()
                .body("name", equalTo("Gregory Loscombe"))
                .body("id", equalTo("51361635"))
                .body("company", equalTo("Ampliance"))
                .body("location", equalTo("Manchester"))
                .body("public_repos", equalTo("3"))
                .body("public_gists", equalTo("2"))
                .body("followers", equalTo("0"))
                .body("following", equalTo("3"))
                .assertThat().statusCode(200);

    }

    @Test(description = "checking the HTML presentation of the response", dataProvider = "userID")
    public void checkingHTMLResponse(String userID) {
        String response = null;
        try {
            response = application.get(profilePage.getInfo(userID));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            profilePage.navigateTo("https://github.com/" + userID);
            Assert.assertEquals(profilePage.checkName(),jsonObject.get("name").toString());
            Assert.assertEquals(profilePage.checkCompany(),jsonObject.get("company").toString());
            Assert.assertEquals(profilePage.checkLocation(),jsonObject.get("location").toString());
            Assert.assertEquals(profilePage.checkRepos(),jsonObject.get("public_repos").toString());
            Assert.assertEquals(profilePage.checkGists(userID),jsonObject.get("public_gists").toString());
            Assert.assertEquals(profilePage.checkFollowers(),jsonObject.get("followers").toString());
            Assert.assertEquals(profilePage.checkFollowing(),jsonObject.get("following").toString());
    }
    }
