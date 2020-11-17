package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PuzzleService {
    @Autowired
    private static PuzzleRepository puzzleRepository;
    @Autowired
    private static UserRepository userRepository;

    private static final List<Puzzle> puzzles = new ArrayList<>( Arrays.asList(
            new Puzzle(UUID.randomUUID(), "Posa kanei 5+5? (10)", "10", 500),
            new Puzzle(UUID.randomUUID(), "Petaei o gaidaros? (oxi)", "oxi", 1100),
            new Puzzle(UUID.randomUUID(), "Posa kanei 3+mila? (yes)", "yes", 300),
            new Puzzle(UUID.randomUUID(), "Einai kalos o kairos? (maybe)", "maybe", 650),
            new Puzzle(UUID.randomUUID(), "Den pernas? (kyra maria)", "kyra maria", 2300)
    ));

    public static List<Puzzle> getAllPuzzles() {
        return puzzles;
    }

    public static Puzzle getPuzzle(UUID id){
        return puzzles.stream().filter(t->t.getId().equals(id)).findFirst().get();
    }

}

