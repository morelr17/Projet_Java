package game.play.player;

import game.character.*;
import game.board.*;
import game.board.resource.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class FarmingPlayer extends Player{

  private List<Worker> workers;
  private Board board;
  public static final int INITIAL_GOLD = 15;

  /**
  *Create a FarmingPlayer
  *@param pseudo the pseudo of the player
  *@param board the board where the player play
  */
  public FarmingPlayer(String pseudo,Board board){
    super(pseudo);
    this.amountGold = INITIAL_GOLD;
    this.workers = new ArrayList<Worker>();
    this.board = board;
  }

  /**
  *loose gold to the player
  *@param gold the number of gold that you loose
  */
  public void looseGold(int gold){
    if(gold >= 0){
      this.amountGold -= gold;
      if(this.amountGold < 0){
        this.amountGold = 0;
      }
    }
  }

  /**
  *return the number of worker
  *@return the number of worker
  */
  public int getNbWorker(){
    return this.workers.size();
  }

  /**
  *return the array of the all worker
  *@return the array of the all worker
  */
  public List<Worker> getAllWorkers(){
    return this.workers;
  }

  /**
  *return the number of point of the Player
  *@return the number of point of the Player
  */
  public int getBilan(){
    int res = 0;
    for(Worker worker : workers){
      res += worker.getGold();
    }
    return res;
  }

  /**
  *add a worker to the player
  *@param worker a worker
  */
  public void addWorker(Worker worker){
    if(worker != null){
      this.workers.add(worker);
    }
  }

  /**
  *change the Resource to gold
  */
  public void changeResourceToGold(){

    Map<Resource,Integer> ressources = this.getResources();
    int res = 0;

    for (Resource resource : ressources.keySet()){

      res += resource.getPrice() * ressources.get(resource);
    }
    this.amountGold += res;
    super.resetResources();
    if(res > 0){
      System.out.println("\nYou win " + res + " gold, thanks to your resources\n");
    }
    else{
      System.out.println("\nYou didn't had any resource\n");
    }
  }
}
