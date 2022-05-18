import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

    private final String ORDER = "/api/v1/orders";
    int order;

    public int orderCreate(String color) {
        return order = given()
                .spec(getSpec())
                .body("{"+"\"firstName\":\"Zaur\","+
                "\"lastName\":\"MZ\","+
                "\"address\":\"msk\"," +
                "\"metroStation\":\"1\"," +
                "\"phone\":\"+8 999 777 77 77\"," +
                "\"rentTime\":\"1\"," +
                "\"deliveryDate\":\"2022-05-12\"," +
                "\"comment\":\"Saske, come back to Konoha\"," +
                "\"color\":"+"["+color+"]}")
                .when()
                .post(ORDER)
                .then().extract().body().path("track");
    }

}
