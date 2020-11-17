package org.codegrinders.treasure_hunter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>( Arrays.asList(
            new User(UUID.randomUUID(), "pakis@pakis.gr", "pakis", "111", 0),
            new User(UUID.randomUUID(), "sakis@sakis.gr", "sakis", "222", 0),
            new User(UUID.randomUUID(), "takis@takis.gr", "takis", "333", 0)
    ));

    public static List<User> getAllUsers() {
        return users;
    }

    public static User getUser(UUID id){
        return users.stream().filter(t->t.getId().equals(id)).findFirst().get();
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void updateUsername(UUID id, User username) {
        for(int i=0;i<users.size();i++){
            User u = users.get(i);
            if(u.getId().equals(id)){
                users.set(i,username);
                return;
            }
        }
    }

    public void updatePoints(UUID id, User points){
        for(int i=0;i<users.size();i++){
            User u = users.get(i);
            if(u.getId().equals(id)){
                users.add(i,points);
            }
        }
    }

    public static void deleteUser(UUID id) {
        users.removeIf(t -> t.getId().equals(id));
    }

}
