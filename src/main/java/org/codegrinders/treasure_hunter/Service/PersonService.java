package org.codegrinders.treasure_hunter.Service;

import org.codegrinders.treasure_hunter.Model.Person;
import org.codegrinders.treasure_hunter.dao.PersonDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService{
    private final PersonDao personDao;

    public PersonService( PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){

        return personDao.insertPerson(person);
    }
    public List<Person> getAllPersons(){

        return personDao.selectAllPersons();
    }
}
