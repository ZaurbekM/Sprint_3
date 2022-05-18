
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class CourierTest {


    Courier courier = Courier.getRandom();
    CourierCredentials creds = new CourierCredentials(courier.login, courier.password);
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierId = courierClient.login(creds);
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Создание курьера")
    public void courierCreate() {
        courierClient.create(courier).then().assertThat().statusCode(201);
        boolean create = courierClient.response.body().path("ok");
        assertTrue(create);
    }

    @Test
    @DisplayName("Проверка при дубле логина")
    public void courierLoginDouble() {
        courierClient.create(courier).then().assertThat().statusCode(201);
        courierClient.create(courier).then().assertThat().statusCode(409).body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }


}
