package org.codegrinders.treasure_hunter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Question")
public class Question {
    @Id
    private final UUID id;
    private String question;
    private String answer;

    public Question(@JsonProperty("id") UUID id,
                    @JsonProperty("question") String question,
                    @JsonProperty("answer") String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
