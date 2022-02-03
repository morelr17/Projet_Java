
package game.play;

import game.character.*;
import game.board.*;
import game.board.tile.*;
import game.board.resource.*;
import game.play.player.*;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class FarmingGame{

  protected List<FarmingPlayer> players;
  protected Board board;
  protected int nbTurns;

  /**
  *Create a FarmingGame
  *@param board the board where the Farming is played
  *@param nbTurns the number of turn on the game
  */
  public FarmingGame(Board board,int nbTurns){
    this.players = new ArrayList<FarmingPlayer>();
    this.board = board;
    this.nbTurns = nbTurns;
  }

  /**
  *add player in Game 
  *@param farmer the player  
  */
  public void addPlayer(FarmingPlayer farmer){
    this.players.add(farmer);
  }

  /**
  *return the players who play the game
  *@return the players who play the game
  */
  public List<FarmingPlayer> getPlayers(){
    return Collections.unmodifiableList(this.players);
  }

  /**
  *return the number of players
  *@return the number of players
  */
  public int getNbPlayers(){
    return this.players.size();
  }

  /**
  *do the conversion of your resource to gold
  *@param player a FarmingPlayer
  */
  public void conversionOfGold(FarmingPlayer player){
    System.out.println("\nYou choose to convert your resource to gold\n");
    System.out.println("\nConversion of your resource to gold:");
    player.changeResourceToGold();
  }

  /**
  *pass your turn
  *@param player a FarmingPlayer
  */
  public void passTurn(FarmingPlayer player){
    List<Worker> workers = player.getAllWorkers();
    System.out.println("\nYou choose to pass your turn\n");
    int res = 0;

    for(Worker worker : workers){
        res += player.addGold(worker.getPosition().getWorkerGold());
    }

    if(res > 0){
      System.out.println("\nYou win " + res + " gold thanks to your workers\n");
    }
    else{
      System.out.println("\nYou didn't win any gold thanks to the tile of your workers\n");
    }
  }

  /**
  *Recolt the resource
  *@param player a FarmingPlayer
  */
  public void recoltOfResource(FarmingPlayer player){

    List<Worker> workers = player.getAllWorkers();

    if(workers.size() > 0){
        System.out.println("Recolt:");
      }
      for(Worker worker : workers){
        CommonTile workerPosition = worker.getPosition();
        Resource resource = workerPosition.getResource();
        player.addResource(resource);
        System.out.println("\nYou recolt 1 " + resource + " thanks to your worker in tile " + workerPosition);
      }
  }

  /**
  *distribute the salary of the worker
  *@param player a FarmingPlayer
  */
  public void salaryOfWorker(FarmingPlayer player){
      List<Worker> workersToDelete = new ArrayList<Worker>();
      List<Worker> workers = player.getAllWorkers();

      if(workers.size() > 0){
        System.out.println("\nSalary:\n");
      }
      for(Worker worker: workers){
        CommonTile workerPosition = worker.getPosition();
        int salary = workerPosition.getWorkerCost();

        if(salary > player.getAmountGold()){
          System.out.println("Your worker in tile " + workerPosition + " is dead");
          workerPosition.free();
          workersToDelete.add(worker);

        }
        else{
          player.looseGold(salary);
          worker.addGold(salary);
          System.out.println("You loose " + salary + " gold to pay your worker in tile " + workerPosition + "\n");
        }
      }

      //Utilisation de removeAll pour ne pas modifier la liste qui est parcouru
      workers.removeAll(workersToDelete);
  }

  /**
  *Make ranking with all player of this game
  *@return the List in order by the point
  */
  public List<FarmingPlayer> classement(){
    List<FarmingPlayer> sortedPlayers = new ArrayList<FarmingPlayer>(this.players);
    sortedPlayers.sort((a,b) -> b.getBilan() - a.getBilan());
    return sortedPlayers;
  }

  /**
  *give the winner of this game 
  *@return the winner of the game
  */
  public FarmingPlayer winner(){
    return classement().get(0);
  }

}