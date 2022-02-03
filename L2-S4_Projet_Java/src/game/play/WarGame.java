package game.play;

import game.board.*;

import java.util.Scanner;
import java.util.*;
import game.play.player.*;
import game.play.*;
import game.character.*;
import game.board.*;

public class WarGame{

  protected ArrayList<WarPlayer> players;
  protected Board board;
  protected int nbTurn;

  public WarGame(Board board,int nbTurn){
    this.players=new ArrayList<WarPlayer>();
    this.board=board;
    this.nbTurn=nbTurn;
  }

  /**
  *add player in Game 
  *@param p player  
  */
  public void addPlayer(WarPlayer p){
    this.players.add(p);
  }

  /*Make ranking with all player of this game
  *@return the List in order by the point
  */
  public List<WarPlayer> classement(){
    List<WarPlayer> sortedPlayers = new ArrayList<WarPlayer>(this.players);
    sortedPlayers.sort((a,b) -> b.getBilan() - a.getBilan());
    return sortedPlayers;
  }

  /**
  *give the winner of this game 
  *@return the winner of the game
  */
  public WarPlayer winner(){
    return classement().get(0);
  }

}
