package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Component
@Qualifier("UserTreasureHunter")
public interface UserRepository extends MongoRepository<User, UUID> {

}
