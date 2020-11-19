package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.UUID;


@Repository
public interface PuzzleRepository extends MongoRepository<Puzzle, String> {


}
