package org.codegrinders.treasure_hunter.service;

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

    public User addUser(User user)  {

            return userRepository.insert(user);

    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public boolean loginApproval(String username,String password){
        if (userRepository.existsByUsername(username)){
            String userPassword = userRepository.findUserByUsername(username).getPassword();
            if (userPassword.equals(password))
                return true;
            return false;
        }
        return false;
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

}
