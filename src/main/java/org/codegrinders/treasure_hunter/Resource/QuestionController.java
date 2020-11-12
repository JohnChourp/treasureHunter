package org.codegrinders.treasure_hunter.Resource;

import org.codegrinders.treasure_hunter.Model.Question;
import org.codegrinders.treasure_hunter.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/question")
@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/addQuestion")
    public void addQuestion(@RequestBody Question question) {  //POST request
        questionService.addQuestion(question);
    }

    @GetMapping("/findAllQuestions")
    public List<Question> getAllQuestion(){
        return questionService.getAllQuestion();
    }
}
