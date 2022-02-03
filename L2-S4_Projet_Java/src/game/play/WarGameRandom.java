package game.play;

import game.play.player.*;
import game.play.*;
import game.character.*;
import game.board.*;
import game.exception.*;
import game.board.tile.*;
import game.board.resource.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class WarGameRandom extends WarGame{

  public WarGameRandom(Board board,int nbTurn){
    super(board, nbTurn);
  }

  /**
  *Play one round of the game with all player  
  */
  public void playOneRound() throws Exception{
    for(int i=0;i<this.players.size();i++){
      System.out.println("\nTurn of player : "+this.players.get(i).getPseudo());
      this.board.displayBoard();
      System.out.println("\nType 1 to deploy an army or 2 to skip your turn");
      Random r=new Random();
      int choose=r.nextInt(2)+1;
      if(choose==2){
        System.out.println("\n You skip Your Turn");
      }
      if(choose ==1 && this.players.get(i).getNbWarriors()!=0){
        try{
          this.addCharacter(this.players.get(i));
        }
        catch(Exception e){
          System.out.println(e);
        }
      }
      else if(choose ==1 && this.players.get(i).getNbWarriors()!=0){
        System.out.println("\nYou didn't have any warriors left");
      }
      for(int x=0;x<this.players.get(i).getCharacters().size();x++){
        Resource resource=this.players.get(i).getCharacters().get(x).getPosition().getResource();
        this.players.get(i).addResource(resource);
      }
      this.players.get(i).changeResource();
      this.players.get(i).feed();
      if(this.board.allTileOccupied()){
        break;
      }
    }
  }

  /**
  *Play one game 
  */
  public void play() throws Exception{
    int t=0;
    while(t!=nbTurn && !(this.board.allTileOccupied())){
      System.out.println("-------------------------------------------------------------\n");
      System.out.println("Turn "+t);
      this.playOneRound();
      t+=1;
      List<WarPlayer> warList = this.classement();
      warList.forEach(fp -> System.out.println(fp.getPseudo() +" has "+ fp.getBilan() +" points"));
    }
    System.out.println("---------- The winner is "+ this.winner().getPseudo() + " with " + this.winner().getBilan() + " points ----------");
  }
  /**
  *add one army on the Board in coordinate X,Y
  *@param player a Warplayer
  */
  public void addCharacter(WarPlayer player) throws Exception{
    Random r=new Random();
    ArrayList<CommonTile> l = this.board.getFreeCommonTiles();
    int i=r.nextInt(l.size());    
    int n=r.nextInt(l.get(i).getMaxCapacity())+1;
    while(player.getNbWarriors()-n<0){
      n=r.nextInt(l.get(i).getMaxCapacity())+1;
    }
    CommonTile t=l.get(i);
    try{
      int index=player.getCharacters().size();
      Army a=new Army(n,t,this.board,player, index);
      player.setCharacter(a);
      player.removeWarriors(n);
      System.out.println("\nYou create an army of size "+n+" in the ("+t.getX()+","+t.getY()+") tile");
    }
    catch(MaxCapacityException e){
      while(n>t.getMaxCapacity()){
        n=r.nextInt(l.get(i).getMaxCapacity())+1;
      }
      int index=player.getCharacters().size();
      Army a=new Army(n,t,this.board,player, index);
      player.addArmy(a);
      System.out.println("\nYou create an army of size "+n+" in the ("+t.getX()+","+t.getY()+") tile");
    }
      catch(Exception e){
        throw e;
      }
  }

  

}
