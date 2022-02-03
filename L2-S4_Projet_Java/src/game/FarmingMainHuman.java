package game;

import game.play.*;
import game.board.*;
import game.play.player.*;

import java.util.Scanner;

public class FarmingMainHuman{

  private static final int NB_TOURS = 6;

  private static int readFromScanner(Scanner scanner, String display){
    System.out.println(display);
    int val = scanner.nextInt();
    while(val<=2){
      System.out.println(display);
      System.out.println("\nx must be positive and superior to 2\n");
      val = scanner.nextInt();
    }
    return val;
  }
  public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);

    System.out.println("-------------------------------------------------------------");
    int x = readFromScanner(scanner, "\nChoose the width val of the map(val>2) \n");
    int y = readFromScanner(scanner, "\nChoose the height val of the map(val>2) \n");

    Board board = new Board(x,y);
    board.initBoard();
    board.displayBoard();

    FarmingGameHuman game = new FarmingGameHuman(board,NB_TOURS);

    System.out.println("-------------------------------------------------------------");
    System.out.println("\nChoose the number of players\n");    
    int nb_players = scanner.nextInt();
    
    for (int i = 1;i < nb_players + 1;i++){
      System.out.println("\nChoose the pseudo of Player "+i+"\n");
      String pseudo = scanner.next();
      FarmingPlayer p = new FarmingPlayer(pseudo,board);
      game.addPlayer(p);
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