package wumpus.game;

import wumpus.game.enums.Direction;

public class Arrow {

    private Direction direction;
    private Position position;

    public Arrow(Direction direction, Position position) {
        this.direction = direction;
        this.position = move(new Position(position));
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

    private Position move(Position position) {

        int x, y;

        switch (direction) {
            case NORTH:
                y = position.getY() + 1;
                position.setY(y);
                break;

            case EAST:
                x = position.getX() + 1;
                position.setX(x);
                break;

            case WEST:
                x = position.getX() - 1;
                position.setX(x);
                break;

            case SOUTH:
                y = position.getY() - 1;
                position.setY(y);
                break;

        }
        return position;

    }
}