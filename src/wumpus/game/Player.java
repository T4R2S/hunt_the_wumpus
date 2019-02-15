package wumpus.game;

import wumpus.game.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected Position position;
    private boolean isDeath;
    private byte arrows;

    public Player() {
        this(new Position(0, 0));
    }

    public Player(Position position) {
        this.position = position;
        this.arrows = 5;
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
        this.arrows--;
        return new Arrow(direction);
    }

    public Position getPosition() {
        return position;
    }

    public void setDeath() {
        isDeath = true;
    }

    public boolean getDeath(){
        return isDeath;
    }

    public int getCountOfArrows() {
        return arrows;
    }
}
