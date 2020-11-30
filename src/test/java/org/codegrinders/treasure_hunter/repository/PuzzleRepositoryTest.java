package org.codegrinders.treasure_hunter.repository;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PuzzleRepositoryTest {

    @Autowired
    private PuzzleRepository puzzleRepository;

    private Puzzle puzzle;

    @Before
    public void initialiaze(){

        puzzleRepository.deleteAll();
        puzzle=new Puzzle("1","what is life?","unknown",1);
        puzzleRepository.save(puzzle);

    }

    @Test
    public void findPuzzleByPuzzleIdMustReturnAPuzzle() {

        Assert.assertNotNull(puzzleRepository.findById(puzzle.getId()));;
    }

    @After
    public void cleanRepository(){
        puzzleRepository.deleteAll();
    }

}