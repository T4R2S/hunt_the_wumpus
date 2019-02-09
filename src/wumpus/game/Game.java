package wumpus.game;

import wumpus.game.enums.RoomType;

import java.util.Random;

public class Game {

    private Player player;
    private Wumpus wumpus;
    private GameMap map;

    // Скорее всего это лучше перенести в конструктор
    public Game() {

        map = new GameMap();

        Random random = new Random();

        while (player == null) {

            int x = random.nextInt(5);
            int y = random.nextInt(4);

            Room room = map.getRooms()[x][y];

            if (player == null && room.getType() == RoomType.Empty)
                player = new Player(new Position(x, y));
        }

        while (wumpus == null) {

            int x = random.nextInt(5);
            int y = random.nextInt(4);

            Room room = map.getRooms()[x][y];

            if (wumpus == null && player.getPosition().getX() != x && player.getPosition().getY() != y)
                wumpus = new Wumpus(new Position(x, y));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Wumpus getWumpus() {
        return wumpus;
    }

    public GameMap getMap() {
        return map;
    }
}