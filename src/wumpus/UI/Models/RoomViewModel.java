package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import wumpus.game.Position;

public class RoomViewModel {

    private Position position;


    public RoomViewModel(Position position) {
        this.position = position;
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeRoundRect(position.getX() * 50, position.getY() * 50, 40, 40, 10, 10);
    }
}
