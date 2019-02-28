package test.game;

import org.junit.Assert;
import org.junit.Test;
import wumpus.game.Arrow;
import wumpus.game.Player;
import wumpus.game.Position;
import wumpus.game.enums.Direction;


public class PlayerTests {

    @Test
    public void move_north_returnsTrueWithNewPosition() {

        //arrange
        Position expectedPosition = new Position(0,1);

        Player player = new Player(new Position(0,0));

        //act
        boolean result = player.move(Direction.NORTH);

        //assert
        Assert.assertTrue(result);
        Assert.assertEquals(expectedPosition.getX(), player.getPosition().getX());
        Assert.assertEquals(expectedPosition.getY(), player.getPosition().getY());
    }

    @Test
    public void move_west_returnsTrueWithNewPosition() {

        //arrange
        Position expectedPosition = new Position(0,0);

        Position position = new Position(1,0);

        Player player = new Player(position);

        //act
        boolean result = player.move(Direction.WEST);

        //assert
        Assert.assertTrue(result);
        Assert.assertEquals(expectedPosition.getX(), player.getPosition().getX());
        Assert.assertEquals(expectedPosition.getY(), player.getPosition().getY());
    }

    @Test
    public void move_south_returnsTrueWithNewPosition() {

        //arrange
        Position expectedPosition = new Position(0,0);

        Position position = new Position(0,1);

        Player player = new Player(position);

        //act
        boolean result = player.move(Direction.SOUTH);

        //assert
        Assert.assertTrue(result);
        Assert.assertEquals(expectedPosition.getX(), player.getPosition().getX());
        Assert.assertEquals(expectedPosition.getY(), player.getPosition().getY());
    }

    @Test
    public void move_east_returnsTrueWithNewPosition() {

        //arrange
        Position expectedPosition = new Position(1,0);

        Position position = new Position(0,0);

        Player player = new Player(position);

        //act
        boolean result = player.move(Direction.EAST);

        //assert
        Assert.assertTrue(result);
        Assert.assertEquals(expectedPosition.getX(), player.getPosition().getX());
        Assert.assertEquals(expectedPosition.getY(), player.getPosition().getY());
    }

    @Test
    public void newPlayerTest(){

        //arrange - act
        Player player = new Player();

        //assert
        Assert.assertEquals(5, player.getCountOfArrows());
    }

    @Test
    public void attackTest() {

        //arrange
        Player player = new Player();

        //act
        Arrow result = player.attack(Direction.NORTH);

        //assert
        Assert.assertNotNull(result);
        Assert.assertEquals(4, player.getCountOfArrows());
    }

    @Test
    public void setDeathTest(){

        //arrange
        Player player = new Player();

        //act
        player.setDeath();

        //assert
        Assert.assertTrue(player.getDeath());
    }
}