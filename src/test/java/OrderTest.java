
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String color;

    public OrderTest(String color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[] getData() {
        return new Object[][]{
                {"\"BLACK\""},
                {"\"GREY\""},
                {"\"GREY\", \"BLACK\""},
                {""},
        };
    }
    @Test
    public void orderCreateTest(){
    OrderClient orderClient = new OrderClient();
    orderClient.orderCreate(color);
    assertThat(orderClient.order,greaterThan(0));
    }

}
