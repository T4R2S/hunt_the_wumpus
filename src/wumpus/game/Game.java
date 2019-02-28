package wumpus.game;

import wumpus.game.enums.RoomType;

import java.util.Random;

public class Game {

    private Player player;
    private Wumpus wumpus;
    private IGameMap map;

    public Game(IGameMap gameMap) {
        map = gameMap;
    }

    public void init() {

        int rows = map.getRows();
        int cols = map.getCols();

        Random random = new Random();

        while (player == null) {

            int x = random.nextInt(rows);
            int y = random.nextInt(cols);

            Room room = map.getRooms()[x][y];

            if (room.getType() == RoomType.Empty)
                player = new Player(new Position(x, y));
        }

        while (wumpus == null) {

            int x = random.nextInt(rows);
            int y = random.nextInt(cols);

            if (!player.getPosition().equals(new Position(x, y)))
                wumpus = new Wumpus(new Position(x, y));
        }
    }

    public boolean checkPit(Position position) {

//        Position playerPosition = getPlayer().getPosition();
        Room room = map.getRoom(position);

        return room.getType() == RoomType.Pit;
    }

    public void checkBats() {

    }

    public boolean checkWumpus(Position position) {
        Position wumpusPosition = getWumpus().getPosition();

        return wumpusPosition.equals(position);
    }

    public Player getPlayer() {
        return player;
    }

    public Wumpus getWumpus() {
        return wumpus;
    }

    public IGameMap getMap() {
        return map;
    }

    public void checkArrow() {
        if (getPlayer().getCountOfArrows() == 0)
            getPlayer().setDeath();
    }
}