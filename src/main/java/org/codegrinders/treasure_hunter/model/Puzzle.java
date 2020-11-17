package org.codegrinders.treasure_hunter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Puzzle")
public class Puzzle {
    @Id
    private UUID id;
    private String question;
    private String answer;
    private int points;

    public Puzzle(@JsonProperty("id") UUID id,
                  @JsonProperty("question") String question,
                  @JsonProperty("answer") String answer,
                  @JsonProperty("points") int points) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
