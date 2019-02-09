package wumpus.game;

public class Game {

    private Player player;
    private Wumpus wumpus;
    private GameMap map;

    // Скорее всего это лучше перенести в конструктор
    public boolean start() {

        player = new Player();
        wumpus = new Wumpus();
        map = new GameMap();

        return true;
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