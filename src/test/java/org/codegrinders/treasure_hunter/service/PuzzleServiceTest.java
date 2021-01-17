package org.codegrinders.treasure_hunter.service;

import org.codegrinders.treasure_hunter.model.Puzzle;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
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

    @Test
    public void tryToDeletePuzzle(){
        List<Puzzle> puzzles=new ArrayList<>();
        puzzles.add( new Puzzle("1","what is life?","unknown",1));
        puzzles.add( new Puzzle("2","what is live?","unknown2",2));

        puzzleService.deletePuzzle("1");

        verify(puzzleRepository,times(1)).deleteById("1");

    }

    @Test
    public void UpdatePuzzleTest(){

        Puzzle puzzle= new Puzzle("1","what is life?","unknown",1);
        when(puzzleRepository.save(puzzle)).thenReturn(puzzle);
        Puzzle puzzleExp= puzzleService.updatePuzzle(puzzle);
        assertEquals(puzzleExp,puzzle);
    }

    @Test
    public void AddPuzzleTest(){

        Puzzle puzzle= new Puzzle("1","what is life?","unknown",1);
        when(puzzleRepository.insert(puzzle)).thenReturn(puzzle);
        Puzzle puzzleExp= puzzleService.addPuzzle(puzzle);
        assertEquals(puzzleExp,puzzle);
    }

    @Test
    public void findByQuestionTest(){
        List<Puzzle> puzzles=new ArrayList<>();
        puzzles.add( new Puzzle("1","what is life?","unknown",1));
        puzzles.add( new Puzzle("what is life?","unknown2",2));
        given(puzzleRepository.findByQuestion("what is life?")).willReturn(puzzles);
        List <Puzzle> exp =puzzleService.findByQuestion("what is life?");
        assertEquals(exp,puzzles);
    }
    @Test
    public void GetPuzzleByQuestionTest(){
        List<Puzzle> puzzles=new ArrayList<>();
        puzzles.add( new Puzzle("1","what is life?","unknown",1));
        given(puzzleRepository.getPuzzleByQuestion("what is life?")).willReturn(puzzles);
        List <Puzzle> exp =puzzleService.getPuzzleByQuestion("what is life?");
        assertEquals(exp,puzzles);
    }
    @Test
    public void puzzleIsCorrectTest(){
        Puzzle puzzle= new Puzzle("1","what is life?","unknown",1);
        when(puzzleRepository.existsById("1")).thenReturn(false);
        boolean exp= puzzleService.puzzleIsCorrect("2","haha");
        assertFalse(exp);
    }

    @Test
    public void FindPuzzleByIdTest(){

        Puzzle puzzle= new Puzzle("1","what is life?","unknown",1);
        when(puzzleRepository.findById("1")).thenReturn(Optional.of(puzzle));
        Optional <Puzzle> puzzleExp= puzzleService.findById("1");
        assertEquals(puzzleExp,Optional.of(puzzle));
    }

}