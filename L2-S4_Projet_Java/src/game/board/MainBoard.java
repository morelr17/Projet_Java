package game.board;
import game.board.tile.*;

public class MainBoard{
    public static void main (String[] args){
        Board a=new Board(3, 3);
        a.initBoard();
        a.displayBoard();
        Board b=new Board(10,10);
        b.initBoard();
        b.displayBoard();
        Board c=new Board(5,3);
        c.initBoard();
        c.displayBoard();
    }
}


