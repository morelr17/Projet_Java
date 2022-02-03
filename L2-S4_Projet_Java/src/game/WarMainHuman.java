package game;

import game.play.*;
import game.board.*;
import game.play.player.*;

import java.util.Scanner;

public class WarMainHuman{
  public static void main(String[] args){
    Scanner r=new Scanner(System.in);
    System.out.println("-------------------------------------------------------------");
    System.out.println("\nChoose the width x of the map(x>2) \n");
    int x=r.nextInt();
    while(x<=2){
      System.out.println("\nx must be positive and superior to 2\n");
      System.out.println("\nChoose the width x of the map(x>2) \n");
      x=r.nextInt();
    }
    System.out.println("\nChoose the height y of the map(y>2)\n");
    int y=r.nextInt();
    while(y<=2){
      System.out.println("\ny must be positive and superior to 2\n");
      System.out.println("\nChoose the height y of the map(y>2)\n");
      y=r.nextInt();
    }    
    Board b = new Board(x, y);
    b.initBoard();
    b.displayBoard();
    WarGameHuman g = new WarGameHuman(b, 10);
    System.out.println("-------------------------------------------------------------");
    System.out.println("\nChoose the number of Players\n");    
    int nb_players=r.nextInt();
    String pseudo = "";
    for (int i=0;i<nb_players;i++){
      System.out.println("\nChoose the pseudo of Player "+i+"\n");
      pseudo=r.next();
      WarPlayer p=new WarPlayer(pseudo,b);
      g.addPlayer(p);
    }
    System.out.println("-------------------------------------------------------------");
    try{
    g.play();
    }
    catch(Exception e){
      System.out.println(e);
    }

  }

}