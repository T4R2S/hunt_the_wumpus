package wumpus.game;

import wumpus.game.enums.Direction;

import java.util.Random;

public class Wumpus extends Player {

    public Wumpus(Position position) {
        super(position);
    }

    public boolean move(){
        Random random = new Random();
        super.move(Direction.values() [random.nextInt(3)]);

        return true;
    }
}
