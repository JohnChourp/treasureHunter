package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    private final PuzzleRepository puzzleRepository;

    public PuzzleController(PuzzleRepository puzzleRepository){
        this.puzzleRepository = puzzleRepository;
    }

    @GetMapping("/all")
    public List<Puzzle> getAll(){
        return this.puzzleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Puzzle> getById(@PathVariable String id) {
        return this.puzzleRepository.findById(id);
    }

}
