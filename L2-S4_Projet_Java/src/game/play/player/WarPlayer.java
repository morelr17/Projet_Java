package game.play.player;

import game.character.*;
import game.board.*;
import game.board.tile.*;
import game.board.resource.*;
import game.exception.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

public class WarPlayer extends Player{

  private int nbWarriors;
  private ArrayList<Army> characters;
  private Board board;
  private int food;

  public static int INITIAL_WARRIORS = 35;
  
  
  public WarPlayer(String pseudo,Board b){
    super(pseudo);
    this.characters=new ArrayList<Army>();
    this.nbWarriors = INITIAL_WARRIORS;
    this.board=b;
    this.amountGold = 0;
    this.food=10;
  }

  public void addFood(int n){
    this.food+=n;
  }

  /**
  *@return the Stock of warior remaining 
  */
  public int getNbWarriors(){
    return this.nbWarriors;
  }

  /**
  *@return Army list of Warplayer
  */
  public ArrayList<Army> getCharacters(){
    return this.characters;
  }

  /**
  *Add one Army in Army List 
  *@param p player 
  */
  public void setCharacter(Army p){
    this.characters.add(p);
  }

  /**
  *@return all player points 
  */
  public int getBilan(){
    int i=this.getAmountGold();
    ArrayList<Army> l=this.getCharacters();
    for(int x=0;x<l.size();x++){
      i+=l.get(x).getGold();
      if(l.get(x).getPosition() instanceof PlainTile){
        i+=1;
      }
      else if(l.get(x).getPosition() instanceof ForestTile){
        i+=2;
      }
      else{
        i+=4;
      }
    }
    if(l.size()>=10){
      i+=5;
    }
    return i;
  }
  /**
  * change all ressource in food 
  */
  public void changeResource(){
    int res=0;
    Map<Resource,Integer> ressources=this.getResources();
    for (Resource resource : ressources.keySet()) {
      res += resource.getFood()* ressources.get(resource);
    }
    super.resetResources();
    this.addFood(res);
    System.out.println("You won "+res+" food ,you have now "+this.food+" food");
  }

  

  /**
  *Give food for all army 
  */
  public void feed(){
    ArrayList<Army> charac=this.getCharacters();
    for(int i=0;i<charac.size();i++){
      if(this.food<charac.get(i).getCost()){
        int x=charac.get(i).getX();
        int y=charac.get(i).getY();
        this.board.setCharacToNull(x, y);
        this.characters.remove(i);
        this.addGold(1);
        System.out.println("You lost your army in ("+x+","+y+")");
      }
      else{
        this.addFood(-charac.get(i).getCost());
        int x=charac.get(i).getX();
        int y=charac.get(i).getY();
        System.out.println("The ("+x+","+y+") army receive "+charac.get(i).getCost()+" foods");
      }
    }    
  }

  /**
  *delete one army of the board 
  *@param index , index of the army 
  */
  public void deleteArmy(int index){
    this.characters.remove(index);
  }

  public void removeWarriors(int n){
    this.nbWarriors-=n;
  }

  public void addArmy(Army a){
    this.characters.add(a);
  }
}



