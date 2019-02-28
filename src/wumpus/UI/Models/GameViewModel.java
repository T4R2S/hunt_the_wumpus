package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import wumpus.game.Game;
import wumpus.game.Position;
import wumpus.game.Room;
import wumpus.game.enums.RoomType;

import java.io.FileNotFoundException;

public class GameViewModel {

    private PlayerViewModel player;
    private GameMapViewModel mapVM;
    private Game game;

    public GameViewModel(Game game) throws FileNotFoundException {

        this.game = game;

        game.init();

        player = new PlayerViewModel(game.getPlayer());
    }

    public Game getGame() {
        return game;
    }

    public void draw(GraphicsContext gc) {

        player.draw(gc);

        mapVM = new GameMapViewModel(game.getMap());

        mapVM.draw(gc, game.getPlayer().getPosition());
    }

    public RoomType getRoomInfo(Position position) {

        int x = position.getX();
        int y = position.getY();

        if (game.getMap().getRooms().length <= x || game.getMap().getRooms()[0].length <= y || x < 0 || y < 0)
            return null;

        Room room = game.getMap().getRooms()[x][y];

        return room.getType();
    }

    public void setRoomInfo(GraphicsContext gc,Position position, Color color){
        mapVM.setRoomColor(gc, position, color);
    }

    public PlayerViewModel getPlayerViewModel() {
        return player;
    }
}
