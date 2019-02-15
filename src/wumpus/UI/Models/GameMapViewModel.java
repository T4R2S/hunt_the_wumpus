package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import wumpus.game.IGameMap;
import wumpus.game.Position;
import wumpus.game.Room;

public class GameMapViewModel {

    private IGameMap map;

    public GameMapViewModel(IGameMap map) {
        this.map = map;
    }

    public void draw(GraphicsContext gc) {

        Room[][] rooms = map.getRooms();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                RoomViewModel roomVM = new RoomViewModel(new Position(i, j));
                roomVM.draw(gc);
            }
        }
    }
}
