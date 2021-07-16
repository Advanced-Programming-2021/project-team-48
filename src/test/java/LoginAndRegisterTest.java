import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.controller.LoginController;
import sample.controller.SignupController;
import sample.model.User;


public class LoginAndRegisterTest {
    @Test
    public void register() {
        Assertions.assertEquals("user created successfully!", SignupController.creatUser("mmd", "khoshgele", "barbari"));
        Assertions.assertEquals("user with username mmd already exists", SignupController.creatUser("mmd", "khoshgele", "barbari"));
        Assertions.assertEquals("user with nickname khoshgele already exists", SignupController.creatUser("a;i", "khoshgele", "barbari"));
    }

    @Test
    public void login(){
        new User("ali","jigar","123");
        Assertions.assertEquals("Username and password didn't match!", LoginController.login("jigar","123"));
        Assertions.assertEquals("Username and password didn't match!", LoginController.login("sdf","123"));
        Assertions.assertEquals("Username and password didn't match!", LoginController.login("ali","1234"));
        Assertions.assertEquals("OK", LoginController.login("ali","123"));
    }
}
