package org.codegrinders.treasure_hunter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Puzzle")
public class Puzzle {
    @Id
    private String id;
    private String question;
    private String answer;
    private int points;

    @PersistenceConstructor
    public Puzzle(String id, String question, String answer, int points) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public Puzzle(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public Puzzle() {
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Puzzle{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}