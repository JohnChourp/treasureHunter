package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.exception.EmailIsAlreadyInUseException;
import org.codegrinders.treasure_hunter.exception.PasswordDoesntMatchException;
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

    public UserService() {
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findAllByOrderByPointsDesc() {
        return userRepository.findAllByOrderByPointsDesc();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public boolean emailExists(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    public boolean usernameExists(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    public User registerUser(User user) {

        if (this.emailExists(user.getEmail())) {
            throw new EmailIsAlreadyInUseException(user.getEmail());
        }

        if (this.usernameExists(user.getUsername())) {
            throw new UsernameAlreadyInUseException(user.getUsername());
        }
        return userRepository.insert(user);
    }

    public boolean loginApproval(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            String userPassword = userRepository.findUserByUsername(username).getPassword();
            if (userPassword.equals(password))
                return true;
            return false;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void updatePoints(String id, int earnedPoints) {
        updateUser(new User(
                findById(id).get().getId(),
                findById(id).get().getEmail(),
                findById(id).get().getUsername(),
                findById(id).get().getPassword(),
                findById(id).get().getPoints() + earnedPoints,
                findById(id).get().getDateCreated(),
                false
        ));
    }

    public void updateHasWon(String userId) {
        if (findById(userId).get().getPoints() > 400) {
            updateUser(new User(
                    findById(userId).get().getId(),
                    findById(userId).get().getEmail(),
                    findById(userId).get().getUsername(),
                    findById(userId).get().getPassword(),
                    findById(userId).get().getPoints(),
                    findById(userId).get().getDateCreated(),
                    true
            ));
        }
    }

    public void updatePassword(String userId, String email, String oldPassword, String newPassword) {
        if (email.equals(findById(userId).get().getEmail()) && oldPassword.equals(findById(userId).get().getPassword())) {
            updateUser(new User(
                    findById(userId).get().getId(),
                    findById(userId).get().getEmail(),
                    findById(userId).get().getUsername(),
                    newPassword,
                    findById(userId).get().getPoints(),
                    findById(userId).get().getDateCreated(),
                    false
            ));
        }
    }

    public void updateEmail(String userId, String newEmail) {

        if (this.emailExists(newEmail)) {
            throw new EmailIsAlreadyInUseException(newEmail);
        } else {
            updateUser(new User(
                    findById(userId).get().getId(),
                    newEmail,
                    findById(userId).get().getUsername(),
                    findById(userId).get().getPassword(),
                    findById(userId).get().getPoints(),
                    findById(userId).get().getDateCreated(),
                    false
            ));
        }
    }
}
