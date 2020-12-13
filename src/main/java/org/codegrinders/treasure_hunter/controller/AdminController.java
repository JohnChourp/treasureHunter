package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    PuzzleService puzzleService;

    @RequestMapping("/welcome")
    public void  sayHello(Model model)
    {
        Puzzle puzzle=new Puzzle();
        puzzle.setQuestion("what");
        puzzle.setAnswer("se");
        puzzle.setPoints(0);
        model.addAttribute(puzzle);

    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
