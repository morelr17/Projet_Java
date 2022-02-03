package game.play;

import game.character.*;
import game.board.*;
import game.board.tile.*;
import game.play.player.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class FarmingGameRandom extends FarmingGame{
  

  private final Random rand = new Random();
  
  /**
  *Create a FarmingGame
  *@param board the board where the Farming is played
  *@param nbTurns the number of turn on the game
  */
  public FarmingGameRandom(Board board,int nbTurns){
    super(board,nbTurns);
  }

  /**
  *Put a character in a Tile
  *@param x abscissa of the Tile who put the Worker 
  *@param y ordinate of the Tile who put the Worker
  *@throws Exception
  */
  public void addCharacter(int x, int y,FarmingPlayer player) throws Exception{

    ArrayList<CommonTile> freeTile = this.board.getFreeCommonTiles();
    int i = 0;

    while(i<freeTile.size()){
      if(freeTile.get(i).getX()==x && freeTile.get(i).getY()==y){
        break;
      }
      i++;
    }

    if(i<freeTile.size()){
      CommonTile tile = freeTile.get(i);
      Worker worker = new Worker(tile,this.board,player);
      player.addWorker(worker);
      tile.occupate();
    }
    else{
      if(i==freeTile.size()){
        Random rand = new Random();
        int j;
        int k;
        
        j = rand.nextInt(10);;
        k = rand.nextInt(10);;

        addCharacter(j,k,player);
      }
    }
  }

  /**
  *put your character on a tile of your choice
  *@param player a FarmingPlayer
  *@param rand a random number
  */
  public void putCharacterInTile(FarmingPlayer player,Random rand){
    List<CommonTile> freeTile = this.board.getFreeCommonTiles();
    int i = 0;

    System.out.println("\nYour choose to put a worker in a tile\n");

    int x = rand.nextInt(10);
    int y = rand.nextInt(10);

    try{
      addCharacter(x,y,player);
    }
    catch(Exception e){
      System.out.println(e);
    }
    System.out.println("\nYour worker is put on the tile\n");
  }

  /**
  *Play one round of the game with all player  
  */
  public void playOneRound() throws Exception{

    //Initialization of the turn

    for(FarmingPlayer player : players){
      System.out.println("\nTurn of player : " + player.getPseudo());
      System.out.println("\nYou have " + player.getAmountGold() + " gold\n");
      this.board.displayBoard();

      List<Worker> workers = player.getAllWorkers();

      workers.forEach(worker -> System.out.println("\nYou have a worker in tile " + worker.getPosition()));

      switch(rand.nextInt(3) + 1){
        case 1:
        putCharacterInTile(player,rand);
        break;
        case 2:
        conversionOfGold(player);
        break;
        case 3:
        passTurn(player);
        break;
      }

      //Recolt of Resource
      recoltOfResource(player);

      //Salary of the worker
      salaryOfWorker(player);

      if(this.board.allTileOccupied()){
        break;
      }

      System.out.println("-------------------------------------------------------------\n");
    }
  }

  /**
  *Play one game 
  */
  public void play() throws Exception{
    int turn = 1;
    while( (turn != nbTurns+1) && !(this.board.allTileOccupied())){
      System.out.println("-------------------------------------------------------------\n");
      System.out.println("Turn "+ turn);
      this.playOneRound();
      turn++;
      System.out.println("Classement:\n");
      List<FarmingPlayer> farmerList = this.classement();
      farmerList.forEach(fp -> System.out.println(fp.getPseudo() +" has "+ fp.getBilan() +" points"));
    }
    System.out.println("---------- The winner is "+ this.winner().getPseudo() + " with " + this.winner().getBilan() + " points ----------");
  }

}