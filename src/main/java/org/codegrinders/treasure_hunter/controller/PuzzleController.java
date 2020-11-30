package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    @Autowired
    private PuzzleService puzzleService;

    @GetMapping("/")
    public List<Puzzle> getAll(){
        return puzzleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Puzzle> getById(@PathVariable String id) {
        return puzzleService.findById(id);
    }

}