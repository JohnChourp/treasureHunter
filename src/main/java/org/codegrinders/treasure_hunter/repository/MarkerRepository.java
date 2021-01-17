package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Marker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MarkerRepository extends MongoRepository<Marker, String> {

    List<Marker> getMarkerByTitle(String title);

}
