package wumpus.game;

public class GameMap {
    private Room[][] rooms;

    public GameMap(){
        rooms = new Room[5][4];
    }

    public Room[][] getRooms() {
        return rooms;
    }
}
