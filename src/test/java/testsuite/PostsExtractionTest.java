package testsuite;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeTest
    public void setup() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page", "1");
        qParam.put("per_page", "25");
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
    //1. Extract the title
    public void test001() {
        List<?> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of title : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //2. Extract the total number of record
    public void test002() {
        int title = response.extract().path("total.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The total number of record : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //3. Extract the body of 15th record
    public void test003() {
        String allData = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record : " + allData);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    //4. Extract the user_id of all the records
    public void test004() {
        List<?> allUserID = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All user id : " + allUserID);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    //5. Extract the title of all the records
    public void test005() {
        List<?> allUserTitle = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All user title" + allUserTitle);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    //6. Extract the title of all records whose user_id = 5914200
    public void test006() {
        List<?> title = response.extract().path("findAll{it.user_id==5914200}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of title" + title);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //7. Extract the body of all records whose id = 93957
    public void test007() {
        List<?> AllBody = response.extract().path("findAll{it.id==93957}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of title" + AllBody);
        System.out.println("------------------End of Test---------------------------");
    }

}
