package org.codegrinders.treasure_hunter.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserTest {

    private User user =
            new User("1","user@user.com","user","1234",0);



    @Test
    public void SetterGetterUser(){
        user.setId("2");
        user.setUsername("spec");
        user.setEmail("df@bh.fg");
        user.setPassword("asdf");
        user.setPoints(1);

        Assert.assertEquals("spec", user.getUsername());
        Assert.assertEquals("2",user.getId());
        Assert.assertEquals("df@bh.fg",user.getEmail());
        Assert.assertEquals("asdf",user.getPassword());
        assertEquals(1,user.getPoints());

    }


}