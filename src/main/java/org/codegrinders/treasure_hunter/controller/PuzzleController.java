package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PuzzleController {
    @Autowired
    private static PuzzleService puzzleService;

    @RequestMapping("/puzzles")
    public List<Puzzle> getAllPuzzles() {
        return puzzleService.getAllPuzzles();
    }

    @RequestMapping("/puzzle/{id}")
    public static Puzzle getPuzzle(@PathVariable UUID id) {
        return puzzleService.getPuzzle(id);
    }

    @PostMapping("/addPuzzle")
    public void addPuzzle(@RequestBody Puzzle puzzle) {  //POST request
        puzzleService.addPuzzle(puzzle);
    }

}
