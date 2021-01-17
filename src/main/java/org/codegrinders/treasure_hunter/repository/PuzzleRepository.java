package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PuzzleRepository extends MongoRepository<Puzzle, String> {

    List<Puzzle> getPuzzleByQuestion(String question);

    List<Puzzle> findByQuestion(String question);


}
