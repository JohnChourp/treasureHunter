package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    @Autowired
    private PuzzleService puzzleService;

    @GetMapping("/")
    public List<Puzzle> getAllPuzzles() {
        return puzzleService.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{id}")
    public Optional<Puzzle> getUser(@PathVariable String id) {
        return puzzleService.findById(id);
    }

    @GetMapping(value = "/answer")
    public Puzzle puzzleIsCorrect(@RequestParam("id") String id, @RequestParam("answer") String answer) {
        if (puzzleService.puzzleIsCorrect(id, answer)) {
            return new Puzzle(puzzleService.findById(id).get().getId(), puzzleService.findById(id).get().getAnswer());
        }
        return new Puzzle();
    }

    @PutMapping(value = "/")
    private Puzzle updatePuzzle(@RequestBody Puzzle puzzle) {
        return puzzleService.updatePuzzle(puzzle);
    }

    @DeleteMapping("/{id}")
    public void deletePuzzle(@PathVariable("id") String id) {
        puzzleService.deletePuzzle(id);
    }


}