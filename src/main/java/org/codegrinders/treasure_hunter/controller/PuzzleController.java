package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PuzzleController {
    @Autowired
    private static PuzzleService puzzleService;

    @RequestMapping("/puzzles")
    public List<Puzzle> getAllPuzzles(){
        return puzzleService.getAllPuzzles();
    }

    @RequestMapping("/puzzle/{id}")
    public static Puzzle getPuzzle(@PathVariable UUID id) {
        return puzzleService.getPuzzle(id);
    }

}
