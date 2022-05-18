import org.apache.commons.lang.RandomStringUtils;

public class Courier {

    String login;
    String password;
    String firstName;

    public Courier (String login,String password,String firstName){

        this.login = login;
        this.password = password;
        this.firstName = firstName;

    }

    public static Courier getRandom(){

        String login = RandomStringUtils.randomAlphabetic(5);
        String password = RandomStringUtils.randomNumeric(4);
        String firstName = RandomStringUtils.randomAlphabetic(6);

        return new Courier(login,password,firstName);
    }
}




