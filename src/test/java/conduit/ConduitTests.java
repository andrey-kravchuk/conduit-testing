package conduit;

import conduit.pages.HomePage;
import conduit.pages.LoginPage;
import conduit.pages.NewArticlePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ConduitTests extends StartTest{
    RequestSpecification request;
    String baseUrl;
    String email;
    String password;
    String token;
    String favoriteTag;
    String favoriteTag2;
    String articleName;
    String driverUrl;

    @Test(groups = {"article"})
    public void createArticleWithTags() throws InterruptedException {
        driverUrl = "https://demo.realworld.io/#/login";
        driver.get(driverUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .enterEmail(email)
                .enterPassword(password)
                .clickSubmitButton();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        HomePage homePage = new HomePage(driver);
        homePage.clickNewArticleLink();

        NewArticlePage newArticlePage = new NewArticlePage(driver);
        newArticlePage
                .enterArticleTitle(articleName)
                .enterArticleDescription("Some description")
                .enterArticleBody("This article created for tag testing")
                .enterTag(favoriteTag)
                .enterTag("tag1")
                .enterTag("tag2")
                .checkEnteredTag(favoriteTag, 1)
                .checkEnteredTag("tag1", 2)
                .checkEnteredTag("tag2", 3)
                .publishArticle();
        newArticlePage.clickHome();
        homePage.clickGlobalFeed()
                .clickOnPopularTag(favoriteTag)
                .checkArticleRowPresent(articleName)
                .clickOnPopularTag(favoriteTag2)
                .checkArticleRowIsNotPresent(articleName);
    }


    @BeforeClass
    public void beforeClass() throws InterruptedException {
        email = "kravchukodessa@gmail.com";
        password = "Qwerty00";
        baseUrl = "https://conduit.productionready.io/api";
        articleName = "My article";
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request
                .body("{\"user\":{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}}")
                .post("/users/login");
        Assert.assertEquals(response.getStatusCode(), 200);
        if (response.getStatusCode() == 200) {
            String jsonString = response.asString();
            Assert.assertTrue(jsonString.contains("token"));
            token = JsonPath.from(jsonString).get("user.token");
        }

        request.header("Authorization", "Token " + token);

        Response response2 = request.get("/tags");
        String jsonString2 = response2.asString();

        ArrayList<String> tags =  JsonPath.from(jsonString2).get("tags");
        favoriteTag = tags.get(tags.size() - 5);
        favoriteTag2 = tags.get(tags.size() - 3);
    }
}
