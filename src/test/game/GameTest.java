package test.game;

import org.junit.Assert;
import org.junit.jupiter.api.RepeatedTest;
import wumpus.game.Game;
import wumpus.game.Position;
import wumpus.game.Room;
import wumpus.game.enums.RoomType;

public class GameTest {

    @RepeatedTest(100)
    public void startTest() {

        //arrange - act
        Game game = new Game();

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
        Assert.assertNotEquals(game.getPlayer().getPosition().getX(), game.getWumpus().getPosition().getX());
    }
}