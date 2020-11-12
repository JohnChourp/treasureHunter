package org.codegrinders.treasure_hunter.Repository;


import org.codegrinders.treasure_hunter.Model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ClusterTreasureHunter")
public interface PersonRepository extends MongoRepository <Person, Integer> {
}
