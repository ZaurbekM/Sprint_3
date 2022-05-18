import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class CourierLoginTest {

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
    @DisplayName("Успешная авторизация")
    public void loginCheck(){
        courierClient.create(courier);
        int number = courierClient.login(new CourierCredentials(courier.login, courier.password));
        courierId = courierClient.login(creds);
        assertEquals(number,courierId);
    }

    @Test
    @DisplayName("Авторизация без логина")
    public void courierWithoutLogin() {
        courierClient.create(courier);
        String nullLogin = "";
        courierClient.loginResponse(new CourierCredentials(nullLogin, courier.password));
        courierClient.response.then().statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void courierWithoutPassword() {
        courierClient.create(courier);
        String nullPassword = "";
        courierClient.loginResponse(new CourierCredentials(courier.login , nullPassword));
        courierClient.response.then().statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация с некорректным паролем")
    public void courierNotCorrectPassword() {
        courierClient.create(courier);
        String noPassword = "nopassword";
        courierClient.loginResponse(new CourierCredentials(courier.login, noPassword));
        courierClient.response.then().statusCode(404).assertThat().body("message", equalTo("Учетная запись не найдена"));
    }


    @Test
    @DisplayName("Авторизация с некорректным логином")
    public void courierNotCorrectLogin() {
        courierClient.create(courier);
        String noLogin = "noLogin";
        courierClient.loginResponse(new CourierCredentials(noLogin, courier.password));
        courierClient.response.then().statusCode(404).assertThat().body("message", equalTo("Учетная запись не найдена"));
    }


}
