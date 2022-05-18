import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreatedTest {
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Ошибка при создании курьера без логина")
    public void createWithoutLogin() {
        String password = RandomStringUtils.randomNumeric(4);
        String login = "";
        Courier courier = new Courier(login, password, "MZ");
        courierClient.create(courier).then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Ошибка при создании курьера без пароля")
    public void createWithoutPassword(){
        String login = RandomStringUtils.randomNumeric(4);
        String password = "";
        Courier courier = new Courier(login, password, "MZ2");
        courierClient.create(courier).then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без имени")
    public void createWithoutFirstName(){
        String login = RandomStringUtils.randomNumeric(4);
        String firstName = "";
        Courier courier = new Courier(login, "qwerty", firstName);
        courierClient.create(courier).then().assertThat().statusCode(201);
    }
}