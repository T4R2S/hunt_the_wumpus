package test.game;

import org.junit.Assert;
import org.junit.Test;
import wumpus.game.Game;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void startTest()
    {
        //arrange
        Game game = new Game();

        //act
        boolean result = game.start();

        //assert
        Assert.assertEquals(true, result);
        Assert.assertNotNull(game.getPlayer());
        Assert.assertNotNull(game.getWumpus());
        Assert.assertNotNull(game.getMap());
    }
}