package test.game;

import org.junit.Assert;
import org.junit.Test;
import wumpus.game.Wumpus;

public class WumpusTest {

    @Test
    public void moveTest(){

        //arrange
        Wumpus wumpus = new Wumpus();

        //act
        boolean result = wumpus.move();

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void attackTest(){

        //arrange
        Wumpus wumpus = new Wumpus();

        //act
        boolean result = wumpus.attack();

        //assert
        Assert.assertTrue(result);
    }
}