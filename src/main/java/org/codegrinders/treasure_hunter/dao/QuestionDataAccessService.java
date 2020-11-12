package org.codegrinders.treasure_hunter.dao;

import org.codegrinders.treasure_hunter.Model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("QuestionTreasureHunter")
public class QuestionDataAccessService implements QuestionDao{
    private static List<Question> TreasureHunter = new ArrayList<>();

    @Override
    public int insertQuestion(UUID id, Question question) {
        TreasureHunter.add(new Question(id, question.getQuestion(),question.getAnswer()));
        return 0;
    }

    @Override
    public List<Question> selectAllQuestion() {
        return TreasureHunter;
    }

}
