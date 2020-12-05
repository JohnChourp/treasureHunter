package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.exception.EmailIsAlreadyInUseException;
import org.codegrinders.treasure_hunter.exception.UsernameAlreadyInUseException;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
public UserService(){}

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.insert(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public boolean emailExists(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    public boolean usernameExists(String username){
        return userRepository.findUserByUsername(username) != null;
    }
    public void registerUser(User user){

        if (this.emailExists(user.getEmail())) {
            throw new EmailIsAlreadyInUseException(user.getEmail());
        }

        if (this.usernameExists(user.getUsername())) {
            throw new UsernameAlreadyInUseException(user.getUsername());
        }

        userRepository.save(user);
    }

}
