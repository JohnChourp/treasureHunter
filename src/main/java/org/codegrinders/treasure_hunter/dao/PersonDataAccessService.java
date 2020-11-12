package org.codegrinders.treasure_hunter.dao;

import org.codegrinders.treasure_hunter.Model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository("ClusterTreasureHunter")
public class PersonDataAccessService implements PersonDao{
    private final List<Person> TreasureHunter = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        TreasureHunter.add(new Person(id, person.getName(), person.getPassword(), person.getEmail()));
        return 0;
    }


    @Override
    public List<Person> selectAllPersons() {
        return TreasureHunter;
    }

}



