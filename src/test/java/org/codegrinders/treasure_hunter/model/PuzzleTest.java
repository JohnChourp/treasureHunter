package org.codegrinders.treasure_hunter.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class PuzzleTest {

   private Puzzle puzzle=new Puzzle("what is life?","unknown",1);


    @Test
    public void SetterGetterPuzzle(){

        puzzle.setQuestion("what is that");
        puzzle.setAnswer("dont");
        puzzle.setPoints(3);

        assertEquals("what is that",puzzle.getQuestion());
        assertEquals("dont",puzzle.getAnswer());
        assertEquals(3,puzzle.getPoints());


    }


}