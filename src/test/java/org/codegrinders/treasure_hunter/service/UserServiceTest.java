package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.exception.EmailIsAlreadyInUseException;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.junit.Assert;
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

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private  UserService userService;

    private User user = new User("4", "elena@elena.gr", "elena", "111", 0, LocalDateTime.now());

    @Test
    public void whenAddUserReturnUser(){
        when(userRepository.insert(user)).thenReturn(user);
        User created=userService.addUser(user);
        assertEquals(created,user);
    }
    @Test
    public void FindAllUsers(){
        List<User> users=new ArrayList<>();
        users.add( new User("1", "user@user.com", "user", "1234", 0,LocalDateTime.now()));

        given(userRepository.findAll()).willReturn(users);

        List <User> expected =userService.findAll();
        assertEquals(expected,users);
    }

    @Test
    public void whenRegisterUserThenHasRegistrationDate() {
        User user = new User("kotsos@kotsos.gr","kotsos","3333");
        userService.registerUser(user);
        Assert.assertNotNull(user.getDate());
    }

    @Test
    public void when_a_user_with_similar_email_try_to_signUp_return_null() {
        User user1 = new User("4", "pakis@pakis.gr", "joss", "111", 0,LocalDateTime.now());
        userService.addUser(user1);
        User user2 = new User("5", "pakis@pakispak.gr", "joss1", "111", 0,LocalDateTime.now());
        when(userService.emailExists(user2.getEmail())).thenReturn(null);
        assertNull(userService.addUser(user2));
        //TODO error here!Test passes but it's wrong.
    }

    @Test(expected = EmailIsAlreadyInUseException.class)
    public void when_a_user_exists_should_throw_exception(){

        User user=new User("2","pakis@pakis.gr","sdf","sdf",0,LocalDateTime.now());
        when(userService.addUser(user)).thenThrow(EmailIsAlreadyInUseException.class);
        assertFalse(userService.emailExists(user.getEmail()));
       // assertThrows(EmailIsAlreadyInUseException.class);
        //TODO error here!

    }

}