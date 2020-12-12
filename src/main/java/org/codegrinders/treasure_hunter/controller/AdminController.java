package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private PuzzleService puzzleService;

    @RequestMapping("/save")
    public String save(Puzzle puzzle) {
        try {
            puzzleService.addPuzzle(puzzle);
        } catch (Exception e) {
            e.printStackTrace();
            return "puzzle";
        }
        return "puzzle";
    }

}
