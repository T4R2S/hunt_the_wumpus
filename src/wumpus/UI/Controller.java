package wumpus.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import wumpus.UI.Models.GameViewModel;
import wumpus.game.Arrow;
import wumpus.game.Game;
import wumpus.game.GameMap;
import wumpus.game.Position;
import wumpus.game.enums.Direction;
import wumpus.game.enums.RoomType;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

public class Controller {

    public Canvas gameMap;
    public TextArea gameLog;

    private GameViewModel game;

    public Controller() throws FileNotFoundException {
        this.game = new GameViewModel(new Game(new GameMap()));
    }

    public void initialize() {
        GraphicsContext gc = gameMap.getGraphicsContext2D();
        gc.clearRect(0, 0, gameMap.getWidth(), gameMap.getHeight());
        gc.save();
        gc.translate(6, 4);

        Position playerPosition = game.getPlayerViewModel().getPlayer().getPosition();

        updateGameLog(playerPosition);
        game.draw(gc);

    }

    @FXML
    public void handleMoveNorthAction(ActionEvent actionEvent) throws FileNotFoundException {
        move(Direction.SOUTH);
    }

    @FXML
    public void handleMoveWestAction(ActionEvent actionEvent) throws FileNotFoundException {
        move(Direction.WEST);
    }

    @FXML
    public void handleMoveSouthAction(ActionEvent actionEvent) throws FileNotFoundException {
        move(Direction.NORTH);
    }

    @FXML
    public void handleMoveEastAction(ActionEvent actionEvent) throws FileNotFoundException {
        move(Direction.EAST);
    }

    @FXML
    public void handleAttackNorthAction(ActionEvent actionEvent) throws FileNotFoundException {
        attack(Direction.SOUTH);
    }

    @FXML
    public void handleAttackWestAction(ActionEvent actionEvent) throws FileNotFoundException {
        attack(Direction.WEST);
    }

    @FXML
    public void handleAttackSouthAction(ActionEvent actionEvent) throws FileNotFoundException {
        attack(Direction.NORTH);
    }

    @FXML
    public void handleAttackEastAction(ActionEvent actionEvent) throws FileNotFoundException {
        attack(Direction.EAST);
    }

    private void move(Direction direction) throws FileNotFoundException {
        GraphicsContext gc = gameMap.getGraphicsContext2D();

        Position playerPosition = game.getPlayerViewModel().getPlayer().getPosition();
        Position oldPlayerPosition = new Position(playerPosition.getX(), playerPosition.getY());
        game.getPlayerViewModel().move(gc, direction);
        game.setRoomInfo(gc, oldPlayerPosition, Color.GREEN);

        getCurrentRoomInfo(gc, playerPosition, oldPlayerPosition);
        updateGameLog(playerPosition);
    }

    private void attack(Direction direction) throws FileNotFoundException {
        Arrow arrow = game.getPlayerViewModel().getPlayer().attack(direction);

        Position arrowPosition = arrow.getPosition();
        Position wumpusPosition = game.getGame().getWumpus().getPosition();

        if (arrowPosition.equals(wumpusPosition)) {
            gameLog.setText("Hit");
            win();
            return;
        }

        Random random = new Random();

        if (random.nextInt(3) != 1) {
            int cols = game.getGame().getMap().getCols();
            int rows = game.getGame().getMap().getRows();

            game.getGame().getWumpus().move(cols, rows);
        }
        Position playerPosition = game.getPlayerViewModel().getPlayer().getPosition();

        if (game.getGame().checkWumpus(playerPosition))
            gameOver();

        int countOfArrows = game.getGame().getPlayer().getCountOfArrows();

        if (countOfArrows <= 0)
            gameOver();
        else
            updateGameLog(playerPosition);
    }

    private void updateGameLog(Position playerPosition) {
        String nearestRoomInfo = getNearestRoomsInfo();
        int countOfArrows = game.getGame().getPlayer().getCountOfArrows();
        String countOfArrowsString = "\ncount of arrows: " + countOfArrows;

        gameLog.setText("Player at " + playerPosition + countOfArrowsString + nearestRoomInfo);
    }

    private void getCurrentRoomInfo(GraphicsContext gc, Position playerPosition, Position oldPlayerPosition) throws FileNotFoundException {
        RoomType roomInfo = game.getRoomInfo(playerPosition);

        if (roomInfo == null) {
            Direction backDirection = game.getPlayerViewModel().getBackDirection(oldPlayerPosition);
            game.getPlayerViewModel().move(gc, backDirection);
            game.setRoomInfo(gc, playerPosition, Color.GREEN);
            return;
        }

        if (game.getGame().checkWumpus(playerPosition) || roomInfo == RoomType.Pit) {
            game.setRoomInfo(gc, playerPosition, Color.RED);
            gameOver();
        } else if (roomInfo == RoomType.Bats) {
            Random random = new Random();

            int cols = game.getGame().getMap().getCols();
            int rows = game.getGame().getMap().getRows();

            game.setRoomInfo(gc, playerPosition, Color.YELLOW);
            oldPlayerPosition = new Position(playerPosition.getX(), playerPosition.getY());

            game.getPlayerViewModel().setRandomPosition(gc, random.nextInt(cols - 1), random.nextInt(rows - 1));
            game.getPlayerViewModel().draw(gc);

            getCurrentRoomInfo(gc, playerPosition, oldPlayerPosition);
        } else {
            game.setRoomInfo(gc, playerPosition, Color.GREEN);
        }
    }

    private String getNearestRoomsInfo() {
        //берем позицию игрока
        Position playerPosition = game.getPlayerViewModel().getPlayer().getPosition();

        //берем комнаты вокруг
        RoomType northRoom = game.getRoomInfo(new Position(playerPosition.getX(), playerPosition.getY() - 1));
        RoomType northEastRoom = game.getRoomInfo(new Position(playerPosition.getX() + 1, playerPosition.getY() - 1));
        RoomType eastRoom = game.getRoomInfo(new Position(playerPosition.getX() + 1, playerPosition.getY()));
        RoomType eastSouthRoom = game.getRoomInfo(new Position(playerPosition.getX() + 1, playerPosition.getY() + 1));
        RoomType southRoom = game.getRoomInfo(new Position(playerPosition.getX(), playerPosition.getY() + 1));
        RoomType southWestRoom = game.getRoomInfo(new Position(playerPosition.getX() - 1, playerPosition.getY() + 1));
        RoomType westRoom = game.getRoomInfo(new Position(playerPosition.getX() - 1, playerPosition.getY()));

        //проверяем комнаты вокруг на наличие мышей, ям
        String result = checkBatsAndPits(northRoom, northEastRoom, eastRoom, eastSouthRoom, southRoom, southWestRoom, westRoom);

        //проверяем комнаты вокруг на наличие вампуса
        boolean hasWumpus = game.getGame().checkWumpus(new Position(playerPosition.getX(), playerPosition.getY() - 1))
                || game.getGame().checkWumpus(new Position(playerPosition.getX() + 1, playerPosition.getY() - 1))
                || game.getGame().checkWumpus(new Position(playerPosition.getX() + 1, playerPosition.getY()))
                || game.getGame().checkWumpus(new Position(playerPosition.getX() + 1, playerPosition.getY() + 1))
                || game.getGame().checkWumpus(new Position(playerPosition.getX(), playerPosition.getY() + 1))
                || game.getGame().checkWumpus(new Position(playerPosition.getX() - 1, playerPosition.getY() + 1))
                || game.getGame().checkWumpus(new Position(playerPosition.getX() - 1, playerPosition.getY()));

        result += hasWumpus ? "\nI smell a Wumpus" : "";

        return result;
    }

    private String checkBatsAndPits(RoomType... roomTypes) {
        String result = "";

        boolean hasPit = Arrays.asList(roomTypes).contains(RoomType.Pit);
        boolean hasBats = Arrays.asList(roomTypes).contains(RoomType.Bats);

        result += hasPit ? "\nI feel a draft" : "";
        result += hasBats ? "\nI hear a bats" : "";

        return result;
    }

    private void gameOver() throws FileNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Game over");
        alert.setHeaderText("Game over");
        alert.setContentText("Wanna repeat?");

        alert.showAndWait();

        ButtonType result = alert.getResult();

        if (result.getButtonData().isDefaultButton()) {
            this.game = new GameViewModel(new Game(new GameMap()));
            gameMap.getGraphicsContext2D().restore();
            initialize();

        } else {
            Stage stage = (Stage) gameLog.getScene().getWindow();
            stage.close();
        }
    }

    private void win() throws FileNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("You win!");
        alert.setHeaderText("You win!");
        alert.setContentText("Wanna repeat?");

        alert.showAndWait();

        ButtonType result = alert.getResult();

        if (result.getButtonData().isDefaultButton()) {
            this.game = new GameViewModel(new Game(new GameMap()));
            gameMap.getGraphicsContext2D().restore();
            initialize();

        } else {
            Stage stage = (Stage) gameLog.getScene().getWindow();
            stage.close();
        }
    }
}
