package wumpus.UI;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import wumpus.UI.Models.GameViewModel;
import wumpus.game.Position;
import wumpus.game.enums.Direction;
import wumpus.game.enums.RoomType;

import java.io.FileNotFoundException;

public class Controller {

    public Canvas gameMap;
    public TextArea gameLog;

    private GameViewModel game;

    public Controller() throws FileNotFoundException {
        this.game = new GameViewModel();
    }

    public void initialize() {
        GraphicsContext gc = gameMap.getGraphicsContext2D();
        game.draw(gc);
    }

    public void moveNorth(ActionEvent actionEvent) {
        GraphicsContext gc = gameMap.getGraphicsContext2D();

        Position playerPosition = game.getPlayer().getPosition();
        gc.clearRect(playerPosition.getX() * 50, playerPosition.getY() * 50, 40, 40);

        game.getPlayer().move(Direction.SOUTH);

        RoomType roomInfo = game.getRoomInfo(playerPosition);

        if (roomInfo == RoomType.Pit)
            game.checkWumpus(playerPosition);

        if (roomInfo == RoomType.Bats)


        game.draw(gc);
    }

    public void moveWest(ActionEvent actionEvent) {
        GraphicsContext gc = gameMap.getGraphicsContext2D();

        Position playerPosition = game.getPlayer().getPosition();
        gc.clearRect(playerPosition.getX() * 50, playerPosition.getY() * 50, 40, 40);

        game.getPlayer().move(Direction.WEST);

        game.draw(gc);
    }

    public void moveEast(ActionEvent actionEvent) {
        GraphicsContext gc = gameMap.getGraphicsContext2D();

        Position playerPosition = game.getPlayer().getPosition();
        gc.clearRect(playerPosition.getX() * 50, playerPosition.getY() * 50, 40, 40);

        game.getPlayer().move(Direction.EAST);

        game.draw(gc);
    }

    public void moveSouth(ActionEvent actionEvent) {
        GraphicsContext gc = gameMap.getGraphicsContext2D();

        Position playerPosition = game.getPlayer().getPosition();
        gc.clearRect(playerPosition.getX() * 50, playerPosition.getY() * 50, 40, 40);

        game.getPlayer().move(Direction.NORTH);

        game.draw(gc);
    }
}
