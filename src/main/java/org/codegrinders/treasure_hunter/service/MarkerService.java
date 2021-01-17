package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkerService {

    @Autowired
    MarkerRepository markerRepository;

    public List<Marker> findAll() {
        return markerRepository.findAll();
    }

    public Optional<Marker> findById(String id) {
        return markerRepository.findById(id);
    }

    public Marker addMarker(Marker marker) {
        return markerRepository.insert(marker);
    }

    public String findMarkerByPuzzleId(String puzzleId) {
        for (int i = 0; i < findAll().size(); i++) {
            if (findAll().get(i).getPuzzleId().equals(puzzleId)) {
                return findAll().get(i).getId();
            }
        }
        return null;
    }

    public void deleteMarker(String id) {
        markerRepository.deleteById(id);
    }

    public Marker updateMarker(Marker marker) {
        return markerRepository.save(marker);
    }

    public List <Marker>  getMarkerByTitle(String title){
        return markerRepository.getMarkerByTitle(title);
    }
}
