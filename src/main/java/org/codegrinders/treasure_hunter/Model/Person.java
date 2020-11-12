package org.codegrinders.treasure_hunter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Person")
public class Person {
    @Id
    private UUID id;
    private String name;
    private String password;
    private String email;
    private int points;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("password") String password,
                  @JsonProperty("email") String email) {
        this.id = id;
        this.name=name;
        this.password=password;
        this.email=email;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    
}
