package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import wumpus.game.Player;
import wumpus.game.Position;
import wumpus.game.enums.Direction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerViewModel {

    private final int height = 40;
    private final int width = 40;

    public Player getPlayer() {
        return player;
    }

    private Player player;
    private Image image = new Image(new FileInputStream(getClass().getResource("../images.png").getFile()), width, height, false, false);

    public PlayerViewModel(Player player) throws FileNotFoundException {
        this.player = player;
    }

    public Direction getBackDirection(Position oldPosition) {
        Position currentPosition = player.getPosition();

        if (currentPosition.getY() < oldPosition.getY())
            return Direction.NORTH;

        if (currentPosition.getY() > oldPosition.getY())
            return Direction.SOUTH;

        if (currentPosition.getX() > oldPosition.getX())
            return Direction.WEST;
        else
            return Direction.EAST;
    }

    public void setRandomPosition(GraphicsContext gc, int x, int y){
        Position position = player.getPosition();
        gc.clearRect(position.getX() * (height + 10), position.getY() * (width + 10), width, height);

        player.setPosition(x,y);
    }

    public void move(GraphicsContext gc, Direction direction) {
        Position position = player.getPosition();

        gc.clearRect(position.getX() * (height + 10), position.getY() * (width + 10), width, height);

        player.move(direction);

        draw(gc);
    }

    public void draw(GraphicsContext gc) {

        Position position = player.getPosition();
        gc.drawImage(image, position.getX() * (height + 10), position.getY() * (width + 10), width, height);
    }
}
