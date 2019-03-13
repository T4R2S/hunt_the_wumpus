package test.game;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import wumpus.game.GameMap;
import wumpus.game.Position;
import wumpus.game.Room;
import wumpus.game.enums.RoomType;

public class GameMapTests {

    @RepeatedTest(100)
    public void NewGameMapTest() {

        //arrange
        int rows = 4;
        int cols = 5;

        //act
        GameMap map = new GameMap(rows,cols);

        int countOfRooms = 0;
        int countOfRoomsWithBats = 0;
        int countOfRoomsWithPit = 0;

        for (int i = 0; i < map.getRooms().length; i++){
            countOfRooms += map.getRooms()[i].length;

            for (int j = 0; j < map.getRooms()[i].length; j++) {
                if (map.getRooms()[i][j].getType() == RoomType.Bats) {
                    countOfRoomsWithBats++;
                    System.out.print("(B)");
                }
                else if (map.getRooms()[i][j].getType() == RoomType.Pit) {
                    countOfRoomsWithPit++;
                    System.out.print("(P)");
                }
                else {
                    System.out.print("( )");
                }
            }

            System.out.println();
        }

        System.out.println();

        //assert
        Assert.assertEquals(rows*cols, countOfRooms);
        Assert.assertEquals(2, countOfRoomsWithBats);
        Assert.assertEquals(2, countOfRoomsWithPit);
    }

    @Test
    public void getRoomTest(){

        //arrange
        GameMap gameMap = new GameMap(4,5);

        Position position = new Position(2, 3);

        //act
        Room result = gameMap.getRoom(position);

        //assert
        Assert.assertNotNull(result);
    }
}