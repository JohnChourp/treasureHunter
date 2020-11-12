package org.codegrinders.treasure_hunter;


import org.codegrinders.treasure_hunter.Model.Person;
import org.codegrinders.treasure_hunter.Model.Question;
import org.codegrinders.treasure_hunter.Repository.PersonRepository;
import org.codegrinders.treasure_hunter.Repository.QuestionRepository;
import org.codegrinders.treasure_hunter.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class TreasureHunterApplication implements CommandLineRunner{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private QuestionRepository questionRepository;
    private PersonDao personDao;


    public static void main(String[] args){

        SpringApplication.run(TreasureHunterApplication.class, args);

    }

    //public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    //	return args -> {
    //		for (Person person : personDao.getAllPersons()) {
    //			System.out.println(person.getName());
    //		}};

    //}
    @Override
    public void run(String... args) throws Exception{
        Person person1= new Person(UUID.randomUUID(),"elena" ,"1234","elena@yahoo.gr");
        Person person2= new Person( UUID.randomUUID(),"athanasia","12345", "athanasia@yahoo.gr");
        Question question1=new Question(UUID.randomUUID(),"Capital of Greece","Greece");
        Question question2=new Question(UUID.randomUUID(),"Capital of Italy","Rome");

        personRepository.save(person1);
        personRepository.save(person2);

        questionRepository.save(question1);
        questionRepository.save(question2);

        System.out.println("=================");

        List <Person> persons=personRepository.findAll();

        for (Person person:persons){
            System.out.println(person.toString());
        }

    }
}


