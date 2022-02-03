
package game.play;

import game.character.*;
import game.board.*;
import game.board.tile.*;
import game.play.player.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FarmingGameHuman extends FarmingGame{

  /**
  *Create a FarmingGame
  *@param board the board where the Farming is played
  *@param nbTurns the number of turn on the game
  */
  public FarmingGameHuman(Board board,int nbTurns){
    super(board,nbTurns);
  }

  /**
  *Put a character in a Tile
  *@param x abscissa of the Tile who put the Worker 
  *@param y ordinate of the Tile who put the Worker
  *@throws Exception
  */
  public void addCharacter(int x, int y,FarmingPlayer player) throws Exception{

    List<CommonTile> freeTile = this.board.getFreeCommonTiles();
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
        Scanner userValue = new Scanner(System.in);
        int j;
        int k;

        System.out.println("This tile is not free or you can't create an army on this tile");

        System.out.println("\nType the abscissa x of the tile that you want to add a worker");
        j = userValue.nextInt();

        System.out.println("\nType the ordinate y of the tile that you want to add a worker");
        k = userValue.nextInt();

        addCharacter(j,k,player);
      }
    }
  }

  /**
  *put your character on a tile of your choice
  *@param player a FarmingPlayer
  *@param r a Scanner
  */
  public void putCharacterInTile(FarmingPlayer player,Scanner r){
    System.out.println("\nType the abscissa x of the tile that you want to add a worker");
    int x = r.nextInt();

    System.out.println("\nType the ordinate y of the tile that you want to add a worker");
    int y = r.nextInt();

    try{
      addCharacter(x,y,player);
      System.out.println("\nYour worker is put on the tile\n");
    }
    catch(Exception e){
      System.out.println(e);
    }
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

      List<Worker> workers =player.getAllWorkers();

      workers.forEach(worker -> System.out.println("\nYou have a worker in tile " + worker.getPosition()));

      System.out.println("\nType: \n- 1 to deploy a worker \n- 2 to change your resource to gold \n- 3 to skip to the recolt of the resource");
      Scanner r = new Scanner(System.in);
      
      switch(r.nextInt()){
        case 1:
        putCharacterInTile(player,r);
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