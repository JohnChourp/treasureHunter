package org.codegrinders.treasure_hunter.Resource;

import org.codegrinders.treasure_hunter.Model.Person;
import org.codegrinders.treasure_hunter.Repository.PersonRepository;
import org.codegrinders.treasure_hunter.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/question")
@RestController
public class PersonController {
    @Autowired
    private final PersonService personService;
    private PersonRepository personRepository;
    public PersonController(PersonService personService,PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository=personRepository;
    }

    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person)
    {
        personService.addPerson(person);
        return "Added Person with name: "+person.getName();
    }

    @GetMapping("/findAllPersons")
    public List<Person> getPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/findAllPersons/{id}")
    public Optional<Person> getPerson(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id){
        personRepository.deleteById(id);
        return " Person deleted with id: "+id;
    }
}
