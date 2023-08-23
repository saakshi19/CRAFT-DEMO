package userTest;

import org.example.Main;
import org.example.dao.UserDao;
import org.example.models.Address;
import org.example.models.UserProfile;
import org.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
public class UserServiceTest {
    @Test
    void contextLoads() {
    }

    private UserDao userDao = new UserDao();

    private UserService userService = new UserService(userDao);

    @Test
    public void testAddUser() {
        List<Address> savedAddresses = List.of(new Address(1, "560102", "Karnataka", "Bengaluru", "Nexus Mall", "Kormangla"));
        userService.addUser(new UserProfile(
                "test@gmail.com",
                "ABC",
                savedAddresses,
                "INDIA"
        ));
        UserProfile userProfile = userService.getUser("test@gmail.com");
        assertEquals("test@gmail.com", userProfile.getEmailId());
        assertEquals("ABC", userProfile.getName());
        assertEquals("INDIA", userProfile.getRegion());
    }

    @Test
    public void testGetUser() {
        List<Address> savedAddresses = List.of(new Address(1, "560102", "Karnataka", "Bengaluru", "Nexus Mall", "Kormangla"));
        userService.addUser(new UserProfile(
                "test@gmail.com",
                "ABC",
                savedAddresses,
                "INDIA"
        ));
        userService.addUser(new UserProfile(
                "test124@gmail.com",
                "XYZ",
                savedAddresses,
                "INDIANA"
        ));
        UserProfile userProfile = userService.getUser("test@gmail.com");
        assertEquals("ABC", userProfile.getName());
        assertEquals("INDIA", userProfile.getRegion());

        UserProfile userProfile2 = userService.getUser("test124@gmail.com");
        assertEquals("XYZ", userProfile2.getName());
        assertEquals("INDIANA", userProfile2.getRegion());
    }
}
