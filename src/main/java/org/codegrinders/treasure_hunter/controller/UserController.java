package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getUser(@PathVariable String id){
        return userRepository.findById(id);

    }

    @PostMapping(value = "/add")
    public String addUser(@RequestBody User user){
        userRepository.insert(user);
        return "Welcome " + user.getUsername();
    }



}
