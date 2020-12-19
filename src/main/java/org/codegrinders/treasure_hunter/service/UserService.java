package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.exception.EmailIsAlreadyInUseException;
import org.codegrinders.treasure_hunter.exception.UsernameAlreadyInUseException;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    MongoTemplate mongoTemplate;

    public UserService() {
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findUserByIdAndPoints() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
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

    public User registerUser(User user){

        if (this.emailExists(user.getEmail())) {
            throw new EmailIsAlreadyInUseException(user.getEmail());
        }

        if (this.usernameExists(user.getUsername())) {
            throw new UsernameAlreadyInUseException(user.getUsername());
        }
        return userRepository.insert(user);
    }

}
