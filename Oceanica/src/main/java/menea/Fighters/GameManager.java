package menea.Fighters;

import menea.Tiles.Board;


public class GameManager {

    private Fighter fighters[];
    private Board board;
    
    public GameManager() {
        fighters = new Fighter[3];
        board = new Board();
    }
    
     public Board getBoard() {
        return board;
    }

}
