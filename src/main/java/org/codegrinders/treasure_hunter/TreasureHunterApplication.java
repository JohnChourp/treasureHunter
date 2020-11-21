package org.codegrinders.treasure_hunter;

import org.codegrinders.treasure_hunter.repository.DataSeeder;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
    public void run(String... args) throws Exception {
        DataSeeder dataSeeder = new DataSeeder(puzzleRepository, userRepository);
        dataSeeder.run();
    }

}