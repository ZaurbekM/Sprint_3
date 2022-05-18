import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private final String ROOT = "api/v1/courier/";
    Response response;


    public Response create(Courier courier) {
        return response = given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(ROOT);

    }

    public int login(CourierCredentials creds) {
        return given()
                .spec(getSpec())
                .body(creds)
                .when()
                .post(ROOT + "login/")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    public boolean delete(int courierId) {
        return given()
                .spec(getSpec())
                .when()
                .delete(ROOT + courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }

    public Response loginResponse(CourierCredentials creds) {
        return response =
                given()
                .spec(getSpec())
                .body(creds)
                .when()
                .post(ROOT + "login/");

    }
}
