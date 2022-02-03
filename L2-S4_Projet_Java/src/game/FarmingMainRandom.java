package game;

import game.play.*;
import game.board.*;
import game.play.player.*;

public class FarmingMainRandom{

  private static final int NB_TOURS = 6;
  public static void main(String[] args){

    final int nbPlayers = args.length;
    if(nbPlayers == 0){
      System.out.println("Impossible to play with 0 player");
      return;
    } 

    int x = 10;
    int y = 10;
    Board board = new Board(x,y);
    board.initBoard();
    board.displayBoard();

    FarmingGameRandom game = new FarmingGameRandom(board, NB_TOURS);
    
    for (int i = 0;i < nbPlayers;i++){
      String pseudo = args[i];
      game.addPlayer(new FarmingPlayer(pseudo,board));
    }
    System.out.println("-------------------------------------------------------------");
    try{
        game.play();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}