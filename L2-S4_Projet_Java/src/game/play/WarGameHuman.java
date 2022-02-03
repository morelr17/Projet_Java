package game.play;

import game.play.player.*;
import game.play.*;
import game.character.*;
import game.board.*;
import game.exception.*;
import game.board.tile.*;
import game.board.resource.*;

import java.util.Scanner;
import java.util.*;

public class WarGameHuman extends WarGame{

  public WarGameHuman(Board board,int nbTurn){
    super(board,nbTurn);
  }

  

  /**
  *Play one round of the game with all player  
  */
  public void playOneRound() throws Exception{
    for(int i=0;i<this.players.size();i++){
      System.out.println("\nTurn of player : "+this.players.get(i).getPseudo());
      this.board.displayBoard();
      System.out.println("\nType 1 to deploy an army or 2 to skip your turn");
      Scanner r=new Scanner(System.in);
      int choose=r.nextInt();
      if(choose==2){
        System.out.println("\n You skip Your Turn");
      }
      if(choose ==1 && this.players.get(i).getNbWarriors()!=0){
        System.out.println("\nType the abscissa x of the tile that you want to add an army");
        int x=r.nextInt();
        System.out.println("\nType the ordinate y of the tile that you want to add an army");
        int y=r.nextInt();
        try{
          this.addCharacter(this.players.get(i),x,y);
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
      System.out.println("Classement:\n");
      List<WarPlayer> warList = this.classement();
      warList.forEach(fp -> System.out.println(fp.getPseudo() +" has "+ fp.getBilan() +" points"));
    }
    System.out.println("---------- The winner is "+ this.winner().getPseudo() + " with " + this.winner().getBilan() + " points ----------");
  }

  /**
  *add one army on the Board in coordinate X,Y
  *@param x abscissa of the Tile who put the Army 
  *@param y Ordiante of the Tile who put the Army
  */
  public void addCharacter(WarPlayer player,int x,int y) throws Exception{
    Scanner userValue=new Scanner(System.in);
    System.out.println("Choose the number of warrior that you want to had in your army");
    int n=userValue.nextInt();
    ArrayList<CommonTile> l = this.board.getFreeCommonTiles();
    int i=0;
    while(i<l.size()){
      if(l.get(i).getX()==x && l.get(i).getY()==y){
        break;
      }
      i++;
    }
    if(i<l.size() && player.getNbWarriors()-n>=0){
      CommonTile t=l.get(i);
      try{
        int index=player.getCharacters().size();
        Army a=new Army(n,t,this.board,player, index);
        player.setCharacter(a);
        player.removeWarriors(n);
        System.out.println("\nYou create an army of size "+n+" in the ("+t.getX()+","+t.getY()+") tile");
      }
      catch(MaxCapacityException e){
        userValue=new Scanner(System.in);
        System.out.println(e);
        while(n>t.getMaxCapacity()){
          System.out.println("Choose an other size, MaxCapacity of this tile is "+t.getMaxCapacity());
          n=userValue.nextInt();
        }
        int index=player.getCharacters().size();
        Army a=new Army(n,t,this.board,player, index);
        player.addArmy(a);
      }
      catch(Exception e){
        throw e;
      }
    }
    else{
      if(i==l.size()){
        System.out.println("This Tile is not free or you can't create an army on this");
        System.out.println("\nType the abscissa x of the tile that you want to add an army");
        x=userValue.nextInt();
        System.out.println("\nType the ordinate y of the tile that you want to add an army");
        y=userValue.nextInt();
      }
      else{
        System.out.println("You only had "+player.getNbWarriors());
      }
      this.addCharacter(player,x,y);
    }
  }

}
