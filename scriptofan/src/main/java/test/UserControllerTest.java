package test;

import com.springserver.api.controller.UserController;
import com.springserver.api.model.User;
import com.springserver.api.repository.UserRepository;
import com.springserver.api.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserController userController;

    private User testUser;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setUserName("testuser");
        testUser.setUserEmail("testuser@test.com");
        testUser.setUserPassword("password");
        testUser.setId("1");
    }

    @Test
    public void testAddNewUser() {
        when(userService.createUser(anyString(), anyString(), anyString())).thenReturn(testUser);
        User result = userController.addNewUser("testuser", "testuser@test.com", "password");
        assertNotNull(result);
        assertEquals(testUser.getUserName(), result.getUserName());
        assertEquals(testUser.getUserEmail(), result.getUserEmail());
        assertEquals(testUser.getUserPassword(), result.getUserPassword());
    }


    @Test
    public void testUpdateUser() {

        //NEEDS TO BE FIXED this test does not pass


        User userRequest = new User();
        userRequest.setUserEmail("updateduser@test.com");
        userRequest.setUserPassword("newpassword");
        userRequest.setUserPhonenumber("1234567890");

        User existingUser = new User();
        existingUser.setId("1");
        existingUser.setUserEmail("olduser@test.com");
        existingUser.setUserPassword("oldpassword");
        existingUser.setUserPhonenumber("9876543210");

        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));
        when(userService.update(existingUser, userRequest, "testuser")).thenReturn(existingUser);

        User result = userController.updateUser(authentication, "1", userRequest);

        assertNotNull(result);
        assertEquals("updateduser@test.com", result.getUserEmail());
        assertTrue(encoder.matches("newpassword", result.getUserPassword()));
        assertEquals("1234567890", result.getUserPhonenumber());

        verify(userRepository, times(1)).findById("1");
        verify(userService, times(1)).update(existingUser, userRequest, "testuser");
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(testUser));
        User result = userController.findUserById("1");
        assertNotNull(result);
        assertEquals(testUser.getUserName(), result.getUserName());
        assertEquals(testUser.getUserEmail(), result.getUserEmail());
        assertEquals(testUser.getUserPassword(), result.getUserPassword());
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(testUser));
        ResponseEntity result = userController.deleteUser(authentication, "1");
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}

