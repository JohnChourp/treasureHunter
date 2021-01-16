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
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)

@SpringBootTest
public class MarkerServiceTest {

    @Mock
    private MarkerRepository markerRepository;

    @InjectMocks
    private final MarkerService markerService = new MarkerService();

    @Test
    public void WhenFindAllMarkersCheckIfMarkerIdIsCorrect() {
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker("1", 10, 20, "marker1", "a marker", "1", true,"go there"));
        markers.add(new Marker("2", 30, 40, "marker2", "another marker", "2", true,"go there"));
        given(markerRepository.findAll()).willReturn(markers);
        List<Marker> expected = markerService.findAll();
        assertEquals(expected.get(0).getId(), markers.get(0).getId());
    }

    @Test
    public void findById() {
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker("1", 10, 20, "marker1", "a marker", "1", true,"go there"));
        markers.add(new Marker("2", 30, 40, "marker2", "another marker", "2", true,"go there"));
        given(markerRepository.findAll()).willReturn(markers);
        List<Marker> expected = markerService.findAll();
        assertEquals(expected.get(0).getId(), markers.get(0).getId());
    }

    @Test
    public void whenFindByPuzzleIdIsCalledWithExistingIdItReturnsCorrespondingMarkerId(){
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker("first marker",0,0,null,null,"first puzzle id",true,""));
        markers.add(new Marker("second marker",0,0,null,null,"second puzzle id",true,""));
        markers.add(new Marker("third marker",0,0,null,null,"third puzzle id",true,""));
        given(markerRepository.findAll()).willReturn(markers);
        assertEquals("second marker",markerService.findMarkerByPuzzleId("second puzzle id"));
    }

    @Test
    public void whenFindByPuzzleIdIsCalledWithNonExistingIdItReturnsNull(){
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker("first marker",0,0,null,null,"first puzzle id",true,""));
        markers.add(new Marker("second marker",0,0,null,null,"second puzzle id",true,""));
        markers.add(new Marker("third marker",0,0,null,null,"third puzzle id",true,""));
        given(markerRepository.findAll()).willReturn(markers);
        assertNull(markerService.findMarkerByPuzzleId("nothing"));
    }

    @Test
    public void tryToDeleteMarker(){
        List<Marker> markers = new ArrayList<>();
        markers.add(new Marker("first marker",0,0,null,null,"first puzzle id",true,""));
        markers.add(new Marker("second marker",0,0,null,null,"second puzzle id",true,""));
        markers.add(new Marker("third marker",0,0,null,null,"third puzzle id",true,""));

        markerService.deleteMarker("first marker");

        verify(markerRepository,times(1)).deleteById("first marker");
    }

    @Test
    public void tryToAddMarker(){
        Marker marker = new Marker("first marker",0,0,null,null,"first puzzle id",true,"");
        given(markerRepository.insert(marker)).willReturn(marker);
        Marker result = markerService.addMarker(marker);
        assertEquals(marker,result);

    }

    @Test
    public void tryToUpdateMarker(){
        Marker marker = new Marker("first marker",10,10,null,null,"first puzzle id",true,"");
        given(markerRepository.save(marker)).willReturn(marker);
        Marker result = markerService.updateMarker(marker);
        assertEquals(marker,result);

    }


}