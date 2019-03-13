package test.game;

import org.junit.Assert;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import wumpus.game.*;
import wumpus.game.enums.RoomType;

import static org.mockito.Mockito.*;

public class GameTests {

    @RepeatedTest(100)
    public void initTest() {

        //arrange
        Game game = new Game(new GameMap());

        //act
        game.init();

        Room[][] rooms = game.getMap().getRooms();
        Position playerPosition = game.getPlayer().getPosition();
        Position wumpusPosition = game.getWumpus().getPosition();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {

                if (playerPosition.getX() == i && playerPosition.getY() == j)
                    System.out.print("(1)");

                else if (wumpusPosition.getX() == i && wumpusPosition.getY() == j)
                    System.out.print("(W)");

                else if (rooms[i][j].getType() == RoomType.Empty)
                    System.out.print("( )");

                else if (rooms[i][j].getType() == RoomType.Pit)
                    System.out.print("(P)");

                else if (rooms[i][j].getType() == RoomType.Bats)
                    System.out.print("(B)");

            }
            System.out.println();
        }
        System.out.println();

        //assert
        Assert.assertNotNull(game.getPlayer());
        Assert.assertNotNull(game.getWumpus());
        Assert.assertNotNull(game.getMap());
        Assert.assertFalse(game.getPlayer().getPosition().equals(game.getWumpus().getPosition()));
    }

    @RepeatedTest(10)
    public void checkWumpus_returnsTrue() {

        //arrange
        Game game = spy(new Game(new GameMap()));

        when(game.getWumpus()).thenReturn(new Wumpus(new Position(1, 1)));

        //act
        boolean result = game.checkWumpus(new Position(1, 1));

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void checkPit_returnsTrue() {

        //arrange
        GameMap gameMapMock = mock(GameMap.class);

        when(gameMapMock.getRoom(new Position(1, 1))).thenReturn(new Room(RoomType.Pit));

        Game game = new Game(gameMapMock);

        //act
        boolean result = game.checkPit(new Position(1, 1));

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void checkArrow_playerHasNotArrows_setDeath() {

        //arrange
        GameMap gameMap = new GameMap();
        Game game = spy(new Game(gameMap));

        Player player = spy(new Player(new Position(1, 1)));

        when(player.getCountOfArrows()).thenReturn(0);
        when(game.getPlayer()).thenReturn(player);

        //act
        game.checkArrow();

        //assert
        Assert.assertEquals(0, game.getPlayer().getCountOfArrows());
        Assert.assertTrue(game.getPlayer().getDeath());
    }
}