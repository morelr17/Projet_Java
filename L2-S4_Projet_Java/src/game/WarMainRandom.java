package game;

import game.play.*;
import game.board.*;
import game.play.player.*;

import java.util.Random;
import java.util.Scanner;

public class WarMainRandom{
  public static void main(String[] args){
    final int nbPlayers = args.length;
    if(nbPlayers == 0){
      System.out.println("You cant play with 0 player retry and had 1 name or more behind the commands to had 1 or more players");
      return;
    }
    System.out.println("-------------------------------------------------------------");
    int x=10;
    int y=10;
    Board b = new Board(x, y);
    b.initBoard();
    b.displayBoard();
    WarGameRandom g = new WarGameRandom(b, 10);
    for (int i = 0;i < nbPlayers;i++){
      String pseudo = args[i];
      g.addPlayer(new WarPlayer(pseudo,b));
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