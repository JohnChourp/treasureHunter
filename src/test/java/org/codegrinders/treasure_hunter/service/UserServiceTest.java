package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.exception.EmailIsAlreadyInUseException;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user = new User("4", "elena@elena.gr", "elena", "111", 0, LocalDateTime.now(),false);

    @Before
    public void init() {
        userRepository.insert(user);
    }

    @Test
    public void whenAddUserReturnUser() {
        when(userRepository.insert(user)).thenReturn(user);
        User created = userService.registerUser(user);
        assertEquals(created, user);
    }

    @Test
    public void FindAllUsers() {
        List<User> users=new ArrayList<>();
        users.add( new User("1", "user@user.com", "user", "1234", 0,LocalDateTime.now(),false));

        given(userRepository.findAll()).willReturn(users);

        List <User> expected =userService.findAll();
        assertEquals(expected,users);
    }

    @Test
    public void whenRegisterUserThenHasRegistrationDate() {
        User user = new User("kotsos@kotsos.gr","kotsos","3333");
        userService.registerUser(user);
        Assert.assertNotNull(user.getDateCreated());
    }

    @Test
    public void when_a_user_with_similar_email_try_to_signUp_return_true() {

        when(userRepository.findUserByEmail("pakis@pakis.gr")).thenReturn(user);
        assertTrue(userService.emailExists("pakis@pakis.gr"));
    }

    @Test
    public void when_a_user_with_unique_email_try_to_signUp_return_false() {

        when(userRepository.findUserByEmail("pakis@pakis.gr")).thenReturn(user);
        assertFalse(userService.emailExists("pakis@pakisss.gr"));
    }

    @Test
    public void when_a_user_with_similar_username_try_to_signUp_return_true() {

        when(userRepository.findUserByUsername("pakis")).thenReturn(user);
        assertTrue(userService.usernameExists("pakis"));
    }

    @Test
    public void when_a_user_with_unique_username_try_to_signUp_return_false() {

        when(userRepository.findUserByUsername("pakis")).thenReturn(user);
        assertFalse(userService.usernameExists("pakisss"));
    }


    @Test(expected = Exception.class)
    public void when_a_user_exists_should_throw_exception() throws EmailIsAlreadyInUseException {
        User user = new User("2", "pakis@pakis.gr", "sdf", "sdf", 0, LocalDateTime.now(),false);
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);
        when(userService.emailExists(user.getEmail())).thenReturn(true);
        willThrow(new Exception()).given(userService).registerUser(user);
        fail("email exists");

    }

    @Test
    public void loginApprovalWithWrongLoginInfo(){
        String wrongPassword = "12312";
        when(userRepository.existsByUsername("elena")).thenReturn(true);
        when(userRepository.findUserByUsername("elena")).thenReturn(user);

        boolean result = userService.loginApproval("elena",wrongPassword);
        assertFalse(result);

    }

    @Test
    public void loginApprovalWithRightLoginInfo(){
        String password = "111";
        when(userRepository.existsByUsername("elena")).thenReturn(true);
        when(userRepository.findUserByUsername("elena")).thenReturn(user);

        boolean result = userService.loginApproval("elena",password);
        assertTrue(result);

    }

    @Test
    public void tryToDeleteUser(){
        userService.deleteUser(user.getId());
        verify(userRepository,times(1)).deleteById(user.getId());
    }

    @Test
    public void whenUserExistsGetHisUsername(){
        User user = new User("2", "pakis@pakis.gr", "sdf", "sdf", 0, LocalDateTime.now(),false);
        when(userRepository.findUserByUsername("sdf")).thenReturn(user);
        User exp= userService.getUserByUsername("sdf");
        assertEquals(user,exp);
    }

    @Test
    public void whenUserNotExistsGetNothing(){
        User user = new User("2", "pakis@pakis.gr", "sdf", "sdf", 0, LocalDateTime.now(),false);
        when(userRepository.findUserByUsername("sdffff")).thenReturn(null);
        User exp= userService.getUserByUsername("sdffff");
        assertNull(exp);
    }

    @Test
    public void FindUserByIdTest(){

        User user = new User("2", "pakis@pakis.gr", "sdf", "sdf", 0, LocalDateTime.now(),false);
        when(userRepository.findById("2")).thenReturn(Optional.of(user));
        Optional <User> userExp= userService.findById("2");
        assertEquals(userExp,Optional.of(user));
    }

    @Test
    public void UpdateUserTest(){

        User user = new User("2", "pakis@pakis.gr", "sdf", "sdf", 0, LocalDateTime.now(),false);
        when(userRepository.save(user)).thenReturn(user);
        User userExp= userService.updateUser(user);
        assertEquals(userExp,user);
    }


}