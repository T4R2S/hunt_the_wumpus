package wumpus.game;

import wumpus.game.enums.RoomType;

public class Room {

    private RoomType type;

    public Room() {
        type = RoomType.Empty;
    }

    public Room(RoomType type) {
        this.type = type;
    }

    public RoomType getType() {
        return type;
    }
}
