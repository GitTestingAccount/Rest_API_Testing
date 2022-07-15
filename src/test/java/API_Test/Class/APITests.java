package API_Test.Class;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class APITests {

    @Test
    void sampleAPITest_Get(){

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    @Test
    void sampleAPITest2_Get(){
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    void sampleAPITest3_Get(){
        given()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
        .log().all();
    }

    @Test
//    Create
    void sampleAPITest4_Post(){
        JSONObject request = new JSONObject();
        request.put("name", "TestName");
        request.put("job", "TestJob");

        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

    @Test
//    Update
    void sampleAPITest5_Put(){
        JSONObject request = new JSONObject();
        request.put("name", "TestName");
        request.put("job", "TestJob");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
//    Delete
    void sampleAPITest6_Delete(){
        when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log()
                .all();
    }

    @Test
    void sampleAPITest7_Post(){
//        Register
        JSONObject request = new JSONObject();
        request.put("email", "eve.holt@reqres.in");
        request.put("password", "pistol");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void sampleAPITest8_Post(){
//        Register fail
        JSONObject request = new JSONObject();
        request.put("email", "eve.holt@reqres.in");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400).log().all();
    }

    @Test
    void sampleAPITest9_Post(){
//        Login success
        JSONObject request = new JSONObject();
        request.put("email", "eve.holt@reqres.in");
        request.put("password", "cityslicka");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void sampleAPITest10_Post(){
//        Login failed
        JSONObject request = new JSONObject();
        request.put("email", "eve.holt@reqres.in");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400).log().all();
    }

    @Test
    void sampleAPITest11_Get(){
        given()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200).log().body();
    }
}
