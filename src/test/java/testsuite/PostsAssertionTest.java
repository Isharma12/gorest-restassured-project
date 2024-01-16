package testsuite;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testbase.TestBase;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {

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
    // 1. Verify the if the total record is 25
    public void test001() {
        response.body("total.size", equalTo(25));
    }

    @Test
    //2. Verify the if the name of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
    //cohaero libero.”
    public void test002() {
        response.body("[0].name", equalTo("Demitto conqueror atavus argumentum corrupti\n" +
                "    //cohaero libero."));
    }

    @Test
    //3.Check the single ‘Id’ in the Array list (5914249)
    public void test003() {
        response.body("name", hasItem("5914249"));
    }

    @Test
    //4.Check the multiple ‘Ids’ in the ArrayList ((5914243, 5914202, 5914199))
    public void test004() {
        response.body("name", hasItems("(5914243, 5914202, 5914199)"));
    }


    //5.Verify the body of userid = 5914149 is equal "“Desidero vorax adsum. Non confero clarus.
    //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”"
    @Test
    public void test005() {
        response.body("find{it.id == 5914149}.email", equalTo("“Desidero vorax adsum. Non confero clarus.\n" +
                "Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus\n" +
                "vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio\n" +
                "tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus\n" +
                "acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”"));
    }

    @Test
    //6.Verify the status is “Active” of user name is “Vaijayanthi Achari”
    public void test006() {
        response.body("find{it.name == 'Vaijayanthi Achari'}.status", equalTo("active"));
    }

    @Test
    //7.Verify the Gender = male of user name is “Bhagwati Devar”
    public void test007() {
        response.body("find{it.name == 'Bhagwati Devar'}.gender", equalTo("male"));
    }

}
