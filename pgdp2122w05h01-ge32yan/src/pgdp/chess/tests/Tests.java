package pgdp.chess.tests;


import org.junit.jupiter.api.Test;
import pgdp.chess.GameBoard;
import pgdp.chess.PinguChess;
import static org.junit.jupiter.api.Assertions.*;


public class Tests {


    // Here I tested the checkPath method 3 times. First I tested the edge case, where the character doesn't move
    // at all. In this case the checkPath method should be false, because not moving is not a valid path.
    @Test
    void testCheckPath(){
        PinguChess p = new PinguChess();
        assertEquals(false, p.checkPath(0,0,0,0));
    }

    // Then I tested the edge case, in which the character tries to land on a field where a teammate is present.
    // In this case the checkPath method should be false, because one can not land on a field where a teammate is present.
    @Test
    void testCheckPath2(){
        PinguChess p = new PinguChess();
        assertEquals(false, p.checkPath(0,0,2,1));
    }

    // Lastly, I tested if the checkPath method would be false if I gave a valid path which has an invalid
    // direction.
    @Test
    void testCheckPath3(){
        PinguChess p = new PinguChess();
        assertEquals(false, p.checkPath(2,1,4,4));
    }




    // Here I tested the checkJump method 3 times. First I tested the edge case, in which the character doesn't
    // move at all. CheckJump should be false in this case.
    @Test
    void testCheckJump(){
        PinguChess p = new PinguChess();
        assertEquals(false,p.checkJump(3,1,3,1));
    }

    // Then I tested if an invalid direction for a jump makes the checkJump method false or not
    @Test
    void testCheckJump2(){
        PinguChess p = new PinguChess();
        assertEquals(false,p.checkJump(5,0,2,4));
    }

    // Lastly I tested if the checkJump method becomes false when there is a teammate at the end point.
    @Test
    void testCheckJump3(){
        PinguChess p = new PinguChess();
        p.getGameBoard().set(4,2,'K');
        assertEquals(false,p.checkJump(2,0,4,2));
    }



    // Here I tested the checkMove method 3 times. First I checked if Kaiserpinguin could go unlimited blocks if
    // it was surrounded by at least 2 teammates and the path was valid.
    @Test
    void testCheckMove(){
        PinguChess p = new PinguChess();
        p.getGameBoard().set(3,1,' ');
        assertEquals(true, p.checkMove(3,0,3,14));
    }

    // Then I tested if Zügelpinguine could go 3 blocks if it was in positive Y direction. And if they could go
    // maximum 1 blocks if it was any other direction as they supposed to.
    @Test
    void testCheckMove2(){
        PinguChess p = new PinguChess();
        assertEquals(true, p.checkMove(2,1,2,4));
        assertEquals(false, p.checkMove(2,1,5,3));
    }

    // Lastly I tested if Felsenpinguine could really jump a maximum of 2 blocks by giving an end point with
    // the distance of 2 and 3.
    @Test
    void testCheckMove3(){
        PinguChess p = new PinguChess();
        assertEquals(false, p.checkMove(2,0,5,3));
        assertEquals(true, p.checkMove(2,0,4,2));
    }


}
