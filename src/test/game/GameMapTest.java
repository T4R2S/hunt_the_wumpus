package test.game;

import org.junit.Assert;
import org.junit.Test;
import wumpus.game.GameMap;

public class GameMapTest {

    @Test
    public void NewGameMapTest(){

        //arrange - act
        GameMap map = new GameMap();

        int countOfRooms = 0;

        for (int i = 0; i < map.getRooms().length; i++)
            countOfRooms += map.getRooms()[i].length;

        //assert
        Assert.assertEquals(20, countOfRooms);
    }
}