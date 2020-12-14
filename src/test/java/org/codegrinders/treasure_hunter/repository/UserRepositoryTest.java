package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.TreasureHunterApplication;
import org.codegrinders.treasure_hunter.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHunterApplication.class)
@WebAppConfiguration
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByUsername_should_return_user() {
        User user = new User("7", "mits@mits.gr", "pakis", "111", 0, LocalDateTime.now());
        Assert.assertNotNull(userRepository.findUserByUsername(user.getUsername()));

    }

    @Test
    public void testFindUserByEmail_should_return_user() {
        User user = new User("7", "pakis@pakis.gr", "mits", "111", 0,LocalDateTime.now());
        Assert.assertNotNull(userRepository.findUserByEmail(user.getEmail()));
    }

}