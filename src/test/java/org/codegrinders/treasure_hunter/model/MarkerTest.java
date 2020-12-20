package org.codegrinders.treasure_hunter.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class MarkerTest {

    private final Marker marker = new Marker(45.1031, 23.3452, "library", "easy", "1",true);

    @Test
    public void SetterGetterMarker() {

        marker.setLatitude(45.2345);
        marker.setLongitude(23.1231);
        marker.setMarkerTile("library");
        marker.setSnippet("easy");
        marker.setPuzzleId("1");

        assertEquals("easy", marker.getSnippet());
        assertEquals("library", marker.getMarkerTile());
        assertEquals("1", marker.getPuzzleId());

    }
}