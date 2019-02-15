package wumpus.UI.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import wumpus.game.Player;
import wumpus.game.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerViewModel {

    final int height = 40;
    final int width = 40;
    private Player player;
    private Image image = new Image(new FileInputStream("/Users/romantrufanov/IdeaProjects/hunt_the_wumpus/src/wumpus/UI/images.png"), width, height, false, false);

    public PlayerViewModel(Player player) throws FileNotFoundException {
        this.player = player;
    }

    public void draw(GraphicsContext gc) {

        Position position = player.getPosition();

        gc.drawImage(image, position.getX() * height, position.getY() * width);
    }
}
