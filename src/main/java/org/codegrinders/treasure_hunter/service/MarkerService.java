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

    public void updateVisibility(String markerId, boolean visibility) {
        if(markerRepository.existsById(markerId)) {
            markerRepository.save(new Marker(
                    findById(markerId).get().getId(),
                    findById(markerId).get().getLatitude(),
                    findById(markerId).get().getLongitude(),
                    findById(markerId).get().getTitle(),
                    findById(markerId).get().getSnippet(),
                    findById(markerId).get().getPuzzleId()
                    , visibility,
                    markerRepository.findById(markerId).get().getDescription()
            ));
        }
    }

    public String findMarkerByPuzzleId(String puzzleId) {
        for (int i = 0; i < findAll().size(); i++) {
            if (findAll().get(i).getPuzzleId().equals(puzzleId)) {
                return findAll().get(i).getId();
            }
        }
        return null;
    }
}
