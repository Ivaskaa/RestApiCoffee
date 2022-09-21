package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.config.jwt.JwtUtils;
import com.example.RestApiCoffee.dto.requests.user.SignupRequest;
import com.example.RestApiCoffee.entities.Role;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.repository.RoleRepository;
import com.example.RestApiCoffee.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtUtils jwtUtils;
    @Test
    void register() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setName("name");
        signupRequest.setPassword("password");
        signupRequest.setPhone("phone");

        Mockito.doReturn(Optional.empty())
                .when(userRepository)
                .findByPhone(signupRequest.getPhone());

        Mockito.doReturn(new Role())
                .when(roleRepository)
                .findByName("ROLE_USER");

        Mockito.doReturn("password")
                .when(passwordEncoder)
                .encode(signupRequest.getPassword());

        User user = userService.register(signupRequest);
        Assertions.assertEquals(user.getPassword(), signupRequest.getPassword());
        Assertions.assertNotNull(user.getRoles());
        Assertions.assertNotNull(user.getRegistrationDate());
        Assertions.assertTrue(user.isActive());
        Mockito.verify(userRepository, Mockito.times(1)).findByPhone(signupRequest.getPhone());
        Mockito.verify(roleRepository, Mockito.times(1)).findByName("ROLE_USER");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void findByPhone() {
        Mockito.doReturn(Optional.of(new User()))
                .when(userRepository)
                .findByPhone("phone");

        User user = userService.findByPhone("phone");
        Assertions.assertNotNull(user);
        Mockito.verify(userRepository, Mockito.times(1)).findByPhone("phone");
    }

    @Test
    void findByPhoneWarn() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository)
                .findByPhone("phone");

        User user = userService.findByPhone("phone");
        Assertions.assertNull(user);
        Mockito.verify(userRepository, Mockito.times(1)).findByPhone("phone");
    }

    @Test
    void update() {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setPhone("phone");
        user.setPoints(1);
        user.setActive(true);

        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findById(1L);

        Mockito.doReturn("password")
                .when(passwordEncoder)
                .encode("password");

        User checkedUser = userService.update(1L, user);

        Assertions.assertEquals(checkedUser, user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("password");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void changePassword() {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setPhone("phone");
        user.setPoints(1);
        user.setActive(true);

        Mockito.doReturn("newPassword")
                .when(passwordEncoder)
                .encode("newPassword");

        userService.changePassword(user, "newPassword");
        Assertions.assertEquals(user.getPassword(), "newPassword");
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("newPassword");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void checkPhoneFalse() {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setPhone("phone");
        user.setPoints(1);
        user.setActive(true);

        Mockito.doReturn(Optional.empty())
                .when(userRepository)
                .findByPhone("newPhone");

        boolean is = userService.checkPhone(user, "newPhone");
        Assertions.assertFalse(is);
        Mockito.verify(userRepository, Mockito.times(1)).findByPhone("newPhone");
    }

    @Test
    void checkPhoneTrue() {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setPhone("phone");
        user.setPoints(1);
        user.setActive(true);

        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByPhone("newPhone");

        boolean is = userService.checkPhone(user, "newPhone");
        Assertions.assertTrue(is);
        Mockito.verify(userRepository, Mockito.times(1)).findByPhone("newPhone");
    }

    @Test
    void checkPasswordFalse() {
        User user = new User();
        user.setPassword("password");

        Mockito.doReturn(true)
                .when(passwordEncoder)
                .matches("password", user.getPassword());

        boolean is = userService.checkPassword(user, "password");
        Assertions.assertFalse(is);
        Mockito.verify(passwordEncoder, Mockito.times(1)).matches("password", user.getPassword());
    }

    @Test
    void checkPasswordTrue() {
        User user = new User();
        user.setPassword("password");

        Mockito.doReturn(false)
                .when(passwordEncoder)
                .matches("anotherPassword", user.getPassword());

        boolean is = userService.checkPassword(user, "anotherPassword");
        Assertions.assertTrue(is);
        Mockito.verify(passwordEncoder, Mockito.times(1)).matches("anotherPassword", user.getPassword());
    }

    @Test
    void createNewJwt() {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        "phone",
                        "password"));

        Mockito.doReturn(authentication)
                .when(authenticationManager)
                .authenticate(new UsernamePasswordAuthenticationToken(
                            "phone",
                            "password"));

        Mockito.doReturn("token")
                .when(jwtUtils)
                .generateJwtToken(authentication);

        String checkedToken = userService.createNewJwt("phone", "password");
        Assertions.assertEquals(checkedToken, "token");
        Mockito.verify(authenticationManager, Mockito.times(2))
                .authenticate(new UsernamePasswordAuthenticationToken(
                "phone",
                "password"));
        Mockito.verify(jwtUtils, Mockito.times(1)).generateJwtToken(authentication);
    }

    @Test
    void addPoints() {
        User user = new User();
        user.setPoints(null);

        User checkedUser = userService.addPoints(user, 100);
        Assertions.assertEquals(checkedUser.getPoints(), 10);
        Mockito.verify(userRepository, Mockito.times(1)).save(checkedUser);
    }

    @Test
    void subtractPointFalse() {
        User user = new User();
        user.setPoints(5);

        boolean is = userService.subtractPoint(user, 10d);
        Assertions.assertFalse(is);
    }

    @Test
    void subtractPointTrue() {
        User user = new User();
        user.setPoints(15);

        boolean is = userService.subtractPoint(user, 10d);
        Assertions.assertTrue(is);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}