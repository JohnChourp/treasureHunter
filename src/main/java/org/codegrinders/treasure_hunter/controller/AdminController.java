package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.codegrinders.treasure_hunter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    PuzzleService puzzleService;
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/save")
    public String save(Puzzle puzzle) {
        try {
            puzzleService.addPuzzle(puzzle);
        } catch (Exception e) {
            e.printStackTrace();
            return "puzzles";
        }
        return "puzzles";
    }

    @GetMapping("/allPuzzles")
    public String showAll(Model model) {
        model.addAttribute("puzzles", puzzleService.findAll());
        return "allPuzzles";
    }
}