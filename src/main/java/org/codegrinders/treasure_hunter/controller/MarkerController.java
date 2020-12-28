package org.codegrinders.treasure_hunter.controller;

import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.service.MarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marker")
public class MarkerController {

    @Autowired
    private MarkerService markerService;

    @GetMapping("/")
    public List<Marker> getAll() {
        return markerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Marker> getById(@PathVariable String id) {
        return markerService.findById(id);
    }

    @GetMapping("/allDescriptions")
    public List<String> getAllDescriptions(){

        List<String> descriptions = new ArrayList<>();
        List<Marker> markers=getAll();
        for (int i=0;i<markers.size();i++){
            descriptions.add(markers.get(i).getDescription());
        }
        return descriptions;
    }
}
