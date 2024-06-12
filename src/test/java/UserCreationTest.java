import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserCreationTest {
    @Mock
    UserService userService;

    @BeforeEach
    void setUp(){
         userService = new UserService();
    }

    @Test
    void testCreatingUser(){
        User user = User.builder().username("username").password("1234password").build();
        userService.create(user);

        assertTrue(userService.checkIfExists(user.getId()));

    }
}
