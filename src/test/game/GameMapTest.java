package test.game;

import org.junit.Assert;
import org.junit.jupiter.api.RepeatedTest;
import wumpus.game.GameMap;
import wumpus.game.enums.RoomType;

public class GameMapTest {

    @RepeatedTest(100)
    public void NewGameMapTest() {

        //arrange - act
        GameMap map = new GameMap();

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
        Assert.assertEquals(20, countOfRooms);
        Assert.assertEquals(2, countOfRoomsWithBats);
        Assert.assertEquals(2, countOfRoomsWithPit);
    }
}