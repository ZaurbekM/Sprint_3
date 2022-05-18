import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderList extends RestAssuredClient{
    Response response;
    private static final String LIST = "api/v1/orders";

    public void getOrderList() {
        response =
                (Response) given()
                        .spec(getSpec())
                        .when()
                        .get(LIST)
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract();
    }
}
