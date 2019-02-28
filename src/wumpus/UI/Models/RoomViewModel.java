package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import wumpus.game.Position;

public class RoomViewModel {

    final int height = 40;
    final int width = 40;
    private Position position;


    public RoomViewModel(Position position) {
        this.position = position;
    }

    public void draw(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        gc.setLineWidth(5);
        gc.strokeRoundRect(position.getX() * 50, position.getY() * 50, width, height, 10, 10);
    }
}
