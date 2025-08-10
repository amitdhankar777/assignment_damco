
package API_TestScripts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Files.Payload;
import Generic.BaseLib_API;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestGoRest_API extends BaseLib_API {

    private static final String BASE_URI = "https://gorest.co.in/public/v2";
    private static final String TOKEN = "c06d359cafc615142a757276c96ada69bf1aa6f3d3b8b635dae5699c2f975ed1";
    private int userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig
                .httpClientConfig()
                .setParam("http.connection.timeout", 10000)
                .setParam("http.socket.timeout", 10000));
    }

    @Test(priority = 1)
    public void test_GET() {
        test.log(Status.INFO, "Test: GET Users API - log active and count inactive users");
        Response response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .get("/users")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> users = response.jsonPath().getList("");
        test.log(Status.INFO, "Total Number of Users: " + users.size());

        int activeCount = 0;
        int inactiveCount = 0;

        // Iterate over each user to log details and count active/inactive
        for (Map<String, Object> user : users) {
            String status = (String) user.get("status");
            if ("active".equalsIgnoreCase(status)) {
                activeCount++;
                test.log(Status.INFO, "Active User Details: id=" + user.get("id") + ", name=" + user.get("name") + ", email=" + user.get("email"));
            } else if ("inactive".equalsIgnoreCase(status)) {
                inactiveCount++;
                //test.log(Status.INFO, "Inactive User Details: id=" + user.get("id") + ", name=" + user.get("name") + ", email=" + user.get("email"));
            }
        }
        test.log(Status.INFO, "Total Active Users: " + activeCount);
        test.log(Status.INFO, "Total Inactive Users: " + inactiveCount);
    }

    @Test(priority = 2)
    public void test_POST() {
        test.log(Status.INFO, "Test: POST Users API - verify user details");
        String payload = Payload.addUser(1234567, "Test User", "test111@test.com", "male", "active");

        Response response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/users")
                .then()
                .assertThat()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Test User"))
                .body("email", equalTo("test111@test.com"))
                .body("gender", equalTo("male"))
                .body("status", equalTo("active"))
                .extract().response();

        String responseString = response.asString();
        JsonPath jp = new JsonPath(responseString);
        userId = jp.getInt("id");
        test.log(Status.INFO, "POST Response: User a added successfully with details: " + responseString);
    }

    @Test(priority = 3)
    public void test_DeleteUser() {
        test.log(Status.INFO, "Test: DELETE Users API - verify deletion of user with id " + userId);
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .delete("/users/" + userId)
                .then()
                .assertThat()
                .statusCode(204);

        test.log(Status.INFO, "DELETE Response: user id " + userId + " deleted");

        // Verify the user is deleted by GETting the user
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .get("/users/" + userId)
                .then()
                .assertThat()
                .statusCode(404);

        test.log(Status.PASS, "User with ID " + userId + " deleted successfully.");
    }
}
