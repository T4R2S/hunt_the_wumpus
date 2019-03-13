package test.UI.Models;

import org.junit.Assert;
import org.junit.Test;
import wumpus.UI.Models.PlayerViewModel;
import wumpus.game.Player;
import wumpus.game.Position;
import wumpus.game.enums.Direction;

import java.io.FileNotFoundException;

public class PlayerViewModelTests {

    @Test
    public void moveTest() throws FileNotFoundException {

        //arrange
        PlayerViewModel playerViewModel = new PlayerViewModel(new Player());

        //act
//        playerViewModel.move(Direction.NORTH);

        //assert
//        Assert.assertEquals(new Position(0,1), playerViewModel.getPosition());
    }
}
