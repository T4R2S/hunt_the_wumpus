package wumpus.game;

public interface IGameMap {

    Room getRoom(Position position);

    Room[][] getRooms();

    int getRows();
    int getCols();
}
