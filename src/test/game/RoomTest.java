package test.game;

import org.junit.Assert;
import org.junit.Test;
import wumpus.game.Room;
import wumpus.game.enums.RoomType;

public class RoomTest {

    @Test
    public void newRoom_returnsEmptyRoom() {

        //arrange - act
        Room room = new Room();

        //assert
        Assert.assertEquals(RoomType.Empty, room.getType());
    }

    @Test
    public void newRoom_returnsPitRoom() {

        //arrange - act
        Room room = new Room(RoomType.Pit);

        //assert
        Assert.assertEquals(RoomType.Pit, room.getType());
    }

    @Test
    public void newRoom_returnsRoomWithBats() {

        //arrange - act
        Room room = new Room(RoomType.Bats);

        //assert
        Assert.assertEquals(RoomType.Bats, room.getType());
    }
}