package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
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
public class PuzzleServiceTest {
    @Mock
    private PuzzleRepository puzzleRepository;

    @InjectMocks
    private final PuzzleService puzzleService=new PuzzleService();


    @Test
    public void FindAllPuzzles(){
        List<Puzzle> puzzles=new ArrayList<>();
        puzzles.add( new Puzzle("what is life?","unknown",1));
        puzzles.add( new Puzzle("what is live?","unknown2",2));

        given(puzzleRepository.findAll()).willReturn(puzzles);

        List <Puzzle> expected =puzzleService.findAll();
        assertEquals(expected,puzzles);

    }

    @Test
    public void FindPuzzleById(){
        List<Puzzle> puzzles=new ArrayList<>();
        puzzles.add( new Puzzle("1","what is life?","unknown",1));
        puzzles.add( new Puzzle("2","what is live?","unknown2",2));

        given(puzzleRepository.findAll()).willReturn(puzzles);

        List <Puzzle> expected =puzzleService.findAll();
        assertEquals("1",puzzles.get(0).getId());
    }
}