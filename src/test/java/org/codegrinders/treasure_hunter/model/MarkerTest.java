package org.codegrinders.treasure_hunter.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class MarkerTest {

    private final Marker marker = new Marker();
    private final Marker marker2 = new Marker(45.2345, 23.1231, "library", "easy", "1", true);

    @Test
    public void whenSetLatitudeThenCheckIfGetLatitudeIsCorrect(){
        marker.setLatitude(45.2345);
        assertEquals(45.2345,marker.getLatitude(),0.0001);
    }

    @Test
    public void whenSetLongitudeThenCheckIfGetLongitudeIsCorrect(){
        marker.setLongitude(23.1231);
        assertEquals(23.1231,marker.getLongitude(),0.0001);
    }

    @Test
    public void whenSetMarkerTitleThenCheckIfGetMarkerTitleIsCorrect(){
        marker.setTitle("library");
        assertEquals("library", marker.getTitle());
    }

    @Test
    public void whenSetSnippetThenCheckIfGetSnippetIsCorrect(){
        marker.setSnippet("easy");
        assertEquals("easy", marker.getSnippet());
    }

    @Test
    public void whenSetPuzzleIdThenCheckIfGetPuzzleIdIsCorrect(){
        marker.setPuzzleId("1");
        assertEquals("1", marker.getPuzzleId());
    }

    @Test
    public void whenSetVisibilityThenCheckIfGetVisibilityIsCorrect(){
        marker.setVisibility(true);
        assertTrue(marker.getVisibility());
    }
}
