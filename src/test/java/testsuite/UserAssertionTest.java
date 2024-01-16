package testsuite;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeTest
    public void setup() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page", "1");
        qParam.put("per_page", "20");
        response = given()
                .queryParams(qParam)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Test
    // 1. Verify the if the total record is 20
    public void test001() {
        response.body("total.size", equalTo(20));
    }

    @Test
    //2. Verify the if the name of id = 5914197 is equal to ””Bhilangana Dhawan””
    public void test002() {

        response.body("[0].name", equalTo("Bhilangana Dhawan"));
    }

    @Test
    //3.Check the single ‘Name’ in the Array list ((Dev Bhattacharya))
    public void test003() {
        response.body("name", hasItem("(Dev Bhattacharya)"));
    }

    @Test
    //4.Check the multiple ‘Names’ in the ArrayList (Akshita Mishra, Chetanaanand Reddy)
    public void test004() {
        response.body("name", hasItems("Akshita Mishra", "Chetanaanand Reddy"));
    }

    @Test
    //5.Verify the email of userid = 5914185 is equal "“tandon_iv_aanandinii@prosacco.example”"
    public void test005() {
        response.body("find{it.id == 5914185}.email", equalTo("“tandon_iv_aanandinii@prosacco.example”"));
    }

    @Test
    //6.Verify the status is “Active” of user name is ““Amaresh Rana””
    public void test006() {
        response.body("find{it.name == '“Amaresh Rana”'}.status", equalTo("active"));
    }

    @Test
    //7.Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
    public void test007() {
        response.body("find{it.name == '“Dhanalakshmi Pothuvaal”'}.gender", equalTo("male"));
    }
}
