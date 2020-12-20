package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Marker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MarkerRepositoryTest {

    @Autowired
    private MarkerRepository markerRepository;

    private Marker marker;

    @Before
    public void setUp() {
        markerRepository.deleteAll();
        marker = new Marker(45.2345, 23.1231, "library", "easy", "1", true);
        markerRepository.save(marker);
    }

    @Test
    public void findPuzzleByPuzzleIdMustReturnAPuzzle() {

        Assert.assertNotNull(markerRepository.findById(marker.getId()));
    }

    @After
    public void tearDown() {
        markerRepository.deleteAll();
    }
}