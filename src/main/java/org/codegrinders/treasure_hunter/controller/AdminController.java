package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.service.MarkerService;
import org.codegrinders.treasure_hunter.service.PuzzleService;
import org.codegrinders.treasure_hunter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    PuzzleService puzzleService;
    @Autowired
    UserService userService;
    @Autowired
    MarkerService markerService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @PostMapping("/save")
    public String PostPuzzle(Puzzle puzzle, Model model) {
        model.addAttribute("puzzle", puzzleService.addPuzzle(puzzle));
        return "puzzles";
    }

    @GetMapping("/save")
    public String GetPuzzle(Puzzle puzzle, Model model) {
        model.addAttribute("puzzle", new Puzzle());
        return "puzzles";
    }

    @GetMapping("/allPuzzles")
    public String showAll(Model model) {
        model.addAttribute("puzzles", puzzleService.findAll());
        return "allPuzzles";
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "allUsers";
    }

    @GetMapping("/allMarkers")
    public String showAllMarkers(Model model) {
        model.addAttribute("markers", markerService.findAll());
        return "allMarkers";
    }

    @GetMapping("/deletePuzzle/{id}")
    public String deletePuzzle(@PathVariable("id") String id, Model model) {
        Puzzle puzzle = puzzleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid puzzle Id:" + id));
        puzzleService.deletePuzzle(puzzle.getId());
        model.addAttribute("puzzles", puzzleService.findAll());
        return "allPuzzles";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteUser(user.getId());
        model.addAttribute("users", userService.findAll());
        return "allUsers";
    }

    @GetMapping("/deleteMarker/{id}")
    public String deleteMarker(@PathVariable("id") String id, Model model) {
        Marker marker = markerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid marker Id:" + id));
        markerService.deleteMarker(marker.getId());
        model.addAttribute("markers", markerService.findAll());
        return "allMarkers";
    }

    @GetMapping("/addMarker")
    public String welcome1(Marker marker, Model model) {
        model.addAttribute("marker", new Marker());
        return "addMarker";
    }

    @PostMapping("/addMarker")
    public String saveMarker(Marker marker, Model model) {
        model.addAttribute("marker", markerService.addMarker(marker));
        return "addMarker";
    }

    @GetMapping("/editPuzzle/{id}")
    public String showUpdateFormPuzzle(@PathVariable("id") String id, Model model) {
        Puzzle puzzle = puzzleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("puzzle", puzzle);
        return "updatePuzzle";
    }

    @PostMapping("/updatePuzzle/{id}")
    public String updatePuzzle(@PathVariable("id") String id, Puzzle puzzle, BindingResult result, Model model) {
        if (result.hasErrors()) {
            puzzle.setId(id);
            return "updatePuzzle";
        }

        puzzleService.updatePuzzle(puzzle);
        model.addAttribute("puzzles", puzzleService.findAll());
        return "redirect:/allPuzzles";
    }

    @GetMapping("/editUser/{id}")
    public String showUpdateFormUser(@PathVariable("id") String id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") String id, User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "updateUser";
        }

        userService.updateUser(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/allUsers";
    }
    @GetMapping("/editMarker/{id}")
    public String showUpdateFormMarker(@PathVariable("id") String id, Model model) {
        Marker marker = markerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid marker Id:" + id));

        model.addAttribute("marker", marker);
        return "updateMarker";
    }
    @PostMapping("/updateMarker/{id}")
    public String updateMarker(@PathVariable("id") String id, Marker marker, BindingResult result, Model model) {
        if (result.hasErrors()) {
            marker.setId(id);
            return "updateMarker";
        }

        markerService.updateMarker(marker);
        model.addAttribute("markers", markerService.findAll());
        return "redirect:/allMarkers";
    }
    @GetMapping("/search")
    public String search(@RequestParam(value = "searchTerm" ,required = false,defaultValue ="none") String searchTerm, Model model){
        User userList=userService.getUserByUsername(searchTerm);
        List< Puzzle> puzzleList=puzzleService.getPuzzleByQuestion(searchTerm);
        List<Marker> markerList=markerService.getMarkerByTitle(searchTerm);
        model.addAttribute("puzzles",puzzleList);
        model.addAttribute("users",userList);
        model.addAttribute("markers",markerList);
        return "search";
    }
    @GetMapping("/autocomplete")
    @ResponseBody
    public List<String> autocomplete (@RequestParam(value="term", required = false, defaultValue="") String term){

        List<Puzzle>puzzles=puzzleService.findByQuestion(term);
        List<String> all =new ArrayList<String>();
        for (Puzzle puzzle:puzzles)
        {
            all.add(puzzle.getId());
        }
        return all;

    }
}