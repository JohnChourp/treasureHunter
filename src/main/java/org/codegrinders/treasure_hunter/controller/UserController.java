package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping(value = "/{leaderboard}")
    public List<User> findUserByIdAndPoints(@PathVariable String id) {
        return userService.findUserByIdAndPoints(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{id}")
    public Optional<User> getUser(@PathVariable String id){
        return userService.findById(id);

    }
    @ResponseStatus(value = HttpStatus.CREATED, reason = "All good")
    @PostMapping(value = "/")
    public void addUser(@RequestBody User user){
        userService.registerUser((user));
    }

    @PutMapping(value = "/")
    private User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.delete(id);
    }

}

