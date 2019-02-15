package wumpus.game;

import wumpus.game.enums.Direction;

public class Arrow {

    private Direction direction;

    public Arrow(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}