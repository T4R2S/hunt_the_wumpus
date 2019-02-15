package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import wumpus.game.Game;
import wumpus.game.GameMap;
import wumpus.game.Position;
import wumpus.game.Room;
import wumpus.game.enums.RoomType;

import java.io.FileNotFoundException;

public class GameViewModel extends Game {

    private PlayerViewModel player;

    public GameViewModel() throws FileNotFoundException {
        super(new GameMap());

        init();

        player = new PlayerViewModel(super.getPlayer());
    }

    public void draw(GraphicsContext gc) {

        player.draw(gc);

        GameMapViewModel mapViewModel = new GameMapViewModel(getMap());

        mapViewModel.draw(gc);
    }

    public RoomType getRoomInfo(Position playerPosition) {
        Room room = getMap().getRooms()[playerPosition.getX()][playerPosition.getY()];

        return room.getType();
    }
}
