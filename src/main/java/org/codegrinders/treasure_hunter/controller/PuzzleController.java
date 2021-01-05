package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.MarkerService;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.codegrinders.treasure_hunter.service.UserService;
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

    @Autowired
    private MarkerService markerService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<Puzzle> getAllPuzzles() {
        return puzzleService.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{id}")
    public Optional<Puzzle> getPuzzle(@PathVariable String id) {
        return puzzleService.findById(id);
    }

    @GetMapping(value = "/answer")
    public Puzzle puzzleIsCorrect(@RequestParam("id") String puzzleId, @RequestParam("answer") String answer, @RequestParam("userId") String userId) {
        if (puzzleService.puzzleIsCorrect(puzzleId, answer)) {
            markerService.updateVisibility(markerService.findMarkerByPuzzleId(puzzleId), false);
            userService.updatePoints(userId, puzzleService.findById(puzzleId).get().getPoints());
            userService.updateHasWon(userId);
            return new Puzzle(puzzleService.findById(puzzleId).get().getId(), puzzleService.findById(puzzleId).get().getAnswer());
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