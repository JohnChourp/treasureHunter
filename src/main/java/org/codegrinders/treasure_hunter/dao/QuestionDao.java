package org.codegrinders.treasure_hunter.dao;


import org.codegrinders.treasure_hunter.Model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionDao {
    int insertQuestion(UUID id, Question question); //given id

    default int insertQuestion(Question question){    //without given id
        UUID id=UUID.randomUUID();
        return insertQuestion(id,question);

    }
    List<Question> selectAllQuestion();
}
