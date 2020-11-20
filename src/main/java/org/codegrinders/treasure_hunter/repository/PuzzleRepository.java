package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PuzzleRepository extends MongoRepository<Puzzle, String> {

}
