package wumpus.game;

import wumpus.game.enums.Direction;

public class Player {
    protected Position position;

    public Player() {
        position = new Position(0, 0);
    }

    public Player(Position position) {

        this.position = position;
    }

    public boolean move(Direction direction) {

        int x,y;

        switch (direction) {
            case NORTH:
                y = position.getY() + 1;
                position.setY(y);
                return true;

            case EAST:
                x = position.getX() + 1;
                position.setX(x);
                return true;

            case WEST:
                x = position.getX() - 1;
                position.setX(x);
                return true;

            case SOUTH:
                y = position.getY() - 1;
                position.setY(y);
                return true;

            default:
                return false;
        }
    }

    public Arrow attack(Direction direction) {
        return new Arrow(direction);
    }

    public Position getPosition() {
        return position;
    }
}
