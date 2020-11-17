package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private static UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/user/{id}")
    public static User getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public void updateUsername(@RequestBody UUID id, @PathVariable User user) {
        userService.updateUsername(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/{points}")
    public void updatePoints(UUID id, @PathVariable User points){
        userService.updatePoints(id, points);
    }

}
