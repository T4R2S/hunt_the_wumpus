package wumpus.game;

import wumpus.game.enums.Direction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Wumpus extends Player {

    public Wumpus(Position position) {
        super(position);
    }

    public boolean move(int cols, int rows) {
        Random random = new Random();

        ArrayList<Direction> possibleDirections = new ArrayList<>();

        if (cols != position.getX())
            possibleDirections.add(Direction.EAST);

        if (rows != position.getY())
            possibleDirections.add(Direction.WEST);

        if (cols != 0)
            possibleDirections.add(Direction.SOUTH);

        if (rows != 0)
            possibleDirections.add(Direction.NORTH);

        Optional<Direction> wumpusMoveDirection;

        do {
            Direction test = Direction.values()[random.nextInt(3)];
            wumpusMoveDirection = possibleDirections.stream().filter(x -> x == test).findFirst();

        } while (wumpusMoveDirection.isPresent() == false);


        super.move(wumpusMoveDirection.get());

        return true;
    }
}
