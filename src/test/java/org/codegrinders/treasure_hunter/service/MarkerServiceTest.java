package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.repository.MarkerRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)

@SpringBootTest
public class MarkerServiceTest {

    @Mock
    private MarkerRepository markerRepository;

    @InjectMocks
    private final MarkerService markerService = new MarkerService();

    @Test
    public void findAll() {
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker(10, 20, "marker1", "a marker", "1"));
        markers.add(new Marker(30, 40, "marker2", "another marker", "2"));
        given(markerRepository.findAll()).willReturn(markers);
        List<Marker> expected = markerService.findAll();
        assertEquals(expected, markers);
    }

    @Test
    public void findById() {
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker("1", 10, 20, "marker1", "a marker", "1"));
        markers.add(new Marker("2", 30, 40, "marker2", "another marker", "2"));
        given(markerRepository.findAll()).willReturn(markers);
        List<Marker> expected = markerService.findAll();
        assertEquals(expected.get(1).getId(), markers.get(1).getId());
    }
}