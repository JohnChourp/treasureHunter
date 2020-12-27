package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.repository.MarkerRepository;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuzzleService {

    @Autowired
    PuzzleRepository puzzleRepository;
    @Autowired
    MarkerRepository markerRepository;

    public PuzzleService() { }

    public List<Puzzle> findAll() {
        return puzzleRepository.findAll();
    }

    public Optional<Puzzle> findById(String id) {
        return puzzleRepository.findById(id);
    }

    public Puzzle addPuzzle(Puzzle puzzle) {
        return puzzleRepository.insert(puzzle);
    }

    public Puzzle updatePuzzle(Puzzle puzzle) {
        return puzzleRepository.save(puzzle);
    }

    public void deletePuzzle(String id) {
        puzzleRepository.deleteById(id);
    }

    public boolean puzzleIsCorrect(String id, String answer) {
        if (puzzleRepository.existsById(id)) {
            String puzzleAnswer = puzzleRepository.findById(id).get().getAnswer();
            if (puzzleAnswer.equals(answer)) {

                String markerId = null;

                for(int i = 0;i<markerRepository.findAll().size();i++){
                    if (markerRepository.findAll().get(i).getPuzzleId().equals(id)){
                        markerId = markerRepository.findAll().get(i).getId();
                    }
                }

                assert markerId != null;
                markerRepository.save(new Marker(
                        markerRepository.findById(markerId).get().getId(),
                        markerRepository.findById(markerId).get().getLatitude(),
                        markerRepository.findById(markerId).get().getLongitude(),
                        markerRepository.findById(markerId).get().getTitle(),
                        markerRepository.findById(markerId).get().getSnippet(),
                        markerRepository.findById(markerId).get().getPuzzleId()
                        ,false
                ));
                return true;
            }
            return false;
        }
        return false;
    }
}