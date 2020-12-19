package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Marker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarkerRepository extends MongoRepository<Marker, String> {
}
