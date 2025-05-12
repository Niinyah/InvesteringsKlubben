package Service;

import Repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    static IUserService userService;

    @BeforeAll
    public static void setup() {
        IUserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    void userIDIsEmptyWhenNameIsIncorrect() {
        String id = userService.getUserID("foo");
        assertTrue(id.isEmpty());
    }

    @Test
    void userIDIsNotEmptyWhenNameIsCorrect() {
        String id = userService.getUserID("Ida Høeg");
        assertFalse(id.isEmpty());
    }
}