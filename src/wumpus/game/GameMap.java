package wumpus.game;

import wumpus.game.enums.RoomType;

import java.util.Random;

public class GameMap {
    private Room[][] rooms;

    public GameMap() {

        Random random = new Random();

        int rows = 5;
        int cols = 4;
        int countOfRoomsWithBats = 2;
        int countOfRoomsWithPit = 2;

        rooms = new Room[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                rooms[i][j] = new Room();

        while (countOfRoomsWithBats > 0) {

            int x = random.nextInt(rows);
            int y = random.nextInt(cols);

            Room room = rooms[x][y];

            if (room.getType() != RoomType.Bats && room.getType() != RoomType.Pit) {
                rooms[x][y] = new Room(RoomType.Bats);
                countOfRoomsWithBats--;
            }
        }

        while (countOfRoomsWithPit > 0) {

            int x = random.nextInt(rows);
            int y = random.nextInt(cols);

            Room room = rooms[x][y];

            if (room.getType() != RoomType.Pit && room.getType() != RoomType.Bats) {
                rooms[x][y] = new Room(RoomType.Pit);
                countOfRoomsWithPit--;
            }
        }
    }

    public Room[][] getRooms() {
        return rooms;
    }
}
