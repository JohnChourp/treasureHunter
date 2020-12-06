package org.codegrinders.treasure_hunter;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
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

    @Override
    public void run(String... args) {

        puzzleRepository.deleteAll();
        userRepository.deleteAll();

        puzzleRepository.save(new Puzzle("1", "5+5 equals? (10)", "10", 500));
        puzzleRepository.save(new Puzzle("2", "Does the donkey fly? (yes)", "yes", 1100));
        puzzleRepository.save(new Puzzle("3", "Are apples and oranges vegetables? (no)", "no", 300));
        puzzleRepository.save(new Puzzle("4", "How is the weather today? (35)", "35", 650));
        puzzleRepository.save(new Puzzle("5", "You can't pass? (miss mary)", "miss mary", 2300));
        puzzleRepository.save(new Puzzle("does it does it? (y)","yes",5));

        userRepository.save(new User("1", "pakis@pakis.gr", "pakis", "111",0, LocalDateTime.now()));
        userRepository.save(new User("2", "sakis@sakis.gr", "sakis", "222",0,LocalDateTime.now()));
        userRepository.save(new User("3", "takis@takis.gr", "takis", "333",0,LocalDateTime.now()));
      //  userRepository.save(new User("totis@totis.gr","totis","123123"));
    }

}