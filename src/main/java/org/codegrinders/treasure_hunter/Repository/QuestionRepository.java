package org.codegrinders.treasure_hunter.Repository;


import org.codegrinders.treasure_hunter.Model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@Qualifier("QuestionTreasureHunter")
public interface QuestionRepository extends MongoRepository<Question, Integer> {
}
