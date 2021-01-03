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

    public void updateVisibility(String id, boolean visibility) {
        //assert id != null;
        if(markerRepository.existsById(id)) {
            markerRepository.save(new Marker(
                    markerRepository.findById(id).get().getId(),
                    markerRepository.findById(id).get().getLatitude(),
                    markerRepository.findById(id).get().getLongitude(),
                    markerRepository.findById(id).get().getTitle(),
                    markerRepository.findById(id).get().getSnippet(),
                    markerRepository.findById(id).get().getPuzzleId()
                    , visibility
            ));
        }
    }

    public String findMarkerByPuzzleId(String puzzleId) {
        for (int i = 0; i < markerRepository.findAll().size(); i++) {
            if (markerRepository.findAll().get(i).getPuzzleId().equals(puzzleId)) {
                return markerRepository.findAll().get(i).getId();
            }
        }
        return null;
    }

    public Marker addMarker(Marker marker) {
        return markerRepository.insert(marker);
    }
}
