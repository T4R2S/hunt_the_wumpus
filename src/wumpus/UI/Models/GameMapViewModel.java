package wumpus.UI.Models;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import wumpus.game.IGameMap;
import wumpus.game.Position;
import wumpus.game.Room;

public class GameMapViewModel {

    private IGameMap map;

    public GameMapViewModel(IGameMap map) {
        this.map = map;
    }

    public void draw(GraphicsContext gc, Position playerPositon) {

        Room[][] rooms = map.getRooms();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                RoomViewModel roomVM = new RoomViewModel(new Position(i, j));

                if (playerPositon.getX() == i && playerPositon.getY() == j)
                    roomVM.draw(gc, Color.GREEN);
                else
                    roomVM.draw(gc, Color.BLUE);
            }
        }
    }

    public void setRoomColor(GraphicsContext gc, Position position, Color color){

        RoomViewModel roomVM = new RoomViewModel(position);
        roomVM.draw(gc, color);
    }
}
