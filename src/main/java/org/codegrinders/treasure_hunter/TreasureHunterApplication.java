package org.codegrinders.treasure_hunter;

import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.MarkerRepository;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;


@SpringBootApplication
public class TreasureHunterApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TreasureHunterApplication.class, args);
    }

    @Autowired
    private PuzzleRepository puzzleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MarkerRepository markerRepository;

    @Override
    public void run(String... args) {

        puzzleRepository.deleteAll();
        userRepository.deleteAll();
        markerRepository.deleteAll();

        puzzleRepository.save(new Puzzle("5+5 equals? (10)", "10", 100));
        puzzleRepository.save(new Puzzle("Does the donkey fly? (yes)", "yes", 300));
        puzzleRepository.save(new Puzzle("Are apples and oranges vegetables? (no)", "no", 500));
        puzzleRepository.save(new Puzzle("How is the weather today? (35)", "35", 100));
        puzzleRepository.save(new Puzzle("You can't pass? (miss mary)", "miss mary", 300));
        puzzleRepository.save(new Puzzle("does it does it? (yes)","yes",500));

        userRepository.save(new User("pakis@pakis.gr", "pakis", "111", 0, LocalDateTime.now()));
        userRepository.save(new User("sakis@sakis.gr", "sakis", "222", 0, LocalDateTime.now()));
        userRepository.save(new User("takis@takis.gr", "takis", "333", 0, LocalDateTime.now()));

        markerRepository.save(new Marker("1", 41.07639384506259, 23.55436861607741,"Library","easy", puzzleRepository.findAll().get(0).getId(),true,"There is a puzzle close to Canteen"));
        markerRepository.save(new Marker("2", 41.074579055248044, 23.553908368320705,"Canteen","medium", puzzleRepository.findAll().get(1).getId(),true,"There is a puzzle close to Management Building"));
        markerRepository.save(new Marker("3", 41.07641471735913, 23.553189582884215,"Management Building","hard", puzzleRepository.findAll().get(2).getId(),true,"There is a puzzle close to Student Club"));
        markerRepository.save(new Marker("4", 41.07559847572417, 23.550664485427845,"Student Club","easy", puzzleRepository.findAll().get(3).getId(),true,"There is a puzzle close to Theatre"));
        markerRepository.save(new Marker("5", 41.07416585770809, 23.554070023473408,"Theatre","medium", puzzleRepository.findAll().get(4).getId(),true,"There is a puzzle close to Computer Science Department"));
        markerRepository.save(new Marker("6", 41.07458492957822, 23.555268268790538,"Computer Science Department","hard", puzzleRepository.findAll().get(5).getId(),true,"There is a puzzle close to Library"));
    }
}