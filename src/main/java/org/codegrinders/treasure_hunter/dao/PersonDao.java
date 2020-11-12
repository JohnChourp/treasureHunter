package org.codegrinders.treasure_hunter.dao;

import org.codegrinders.treasure_hunter.Model.Person;
import java.util.List;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);
    List<Person> selectAllPersons();

    default int insertPerson(Person person){    //without given id
        UUID id=UUID.randomUUID();
        return insertPerson(id,person);

    }

}
