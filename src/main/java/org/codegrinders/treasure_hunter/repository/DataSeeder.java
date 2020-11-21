package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DataSeeder implements CommandLineRunner {

    @Autowired
    private final PuzzleRepository puzzleRepository;
    @Autowired
    private final UserRepository userRepository;

    public DataSeeder(PuzzleRepository puzzleRepository, UserRepository userRepository) {
        this.puzzleRepository = puzzleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        puzzleRepository.deleteAll();
        userRepository.deleteAll();

        puzzleRepository.save(new Puzzle("1", "5+5 equals? (10)", "10", 500));
        puzzleRepository.save(new Puzzle("2", "Does the donkey fly? (yes)", "yes", 1100));
        puzzleRepository.save(new Puzzle("3", "Are apples and oranges vegetables? (no)", "no", 300));
        puzzleRepository.save(new Puzzle("4", "How is the weather today? (35)", "35", 650));
        puzzleRepository.save(new Puzzle("5", "You can't pass? (miss mary)", "miss mary", 2300));

        userRepository.save(new User("1", "pakis@pakis.gr", "pakis", "111", 0));
        userRepository.save(new User("2", "sakis@sakis.gr", "sakis", "222", 0));
        userRepository.save(new User("3", "takis@takis.gr", "takis", "333", 0));

    }

}
