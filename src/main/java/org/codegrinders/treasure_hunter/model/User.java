package org.codegrinders.treasure_hunter.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String username;
    private String password;
    private int points;

    public User(UUID id, String email, String username, String password, int points) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
