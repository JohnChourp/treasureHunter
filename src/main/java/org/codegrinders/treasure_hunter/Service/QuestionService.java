package org.codegrinders.treasure_hunter.Service;

import org.codegrinders.treasure_hunter.Model.Question;
import org.codegrinders.treasure_hunter.dao.QuestionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public int addQuestion(Question question){
        return questionDao.insertQuestion(question);
    }
    public List<Question> getAllQuestion(){
        return questionDao.selectAllQuestion();
    }
}
