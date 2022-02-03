package game.character;

import game.board.*;
import game.board.tile.*;
import game.play.*;
import game.exception.*;
import game.play.player.*;

public class Worker extends Character{

    private int cost;
    private Board board;
    private FarmingPlayer player;

    /**
    *Create a new worker
    *@param position the position of the worker
    *@param board the board of the worker
    *@param player the player who had the worker
    *@throws TileCantBeOccupedException
    *@throws TileIsOccupedException
    */
    public Worker(CommonTile position,Board board,FarmingPlayer player) throws TileCantBeOccupedException,TileIsOccupedException{
      super(position);
      this.cost = this.position.getWorkerCost();
      this.board = board;
      this.player = player;
      if(!(this.position.getCanBeOccupied())){
          throw new TileCantBeOccupedException("This tile can't be Occupied because this is an oceanTile");
        }
      else if(this.position.getIsOccuped()){
            throw new TileIsOccupedException("This tile is already occupied !");
        }
    }

    /**
    *return the cost
    *@return the cost
    */
    public int getCost(){
      return this.cost;
    }

    /**
    *return the FarmingPlayer who had the worker
    *@return the FarmingPlayer who had the worker
    */
    public FarmingPlayer getPlayer(){
      return this.player;
    }

    /**
    *@param o object 
    *@return True if o is esquals 
    */
    public boolean equals(Object o){
      if(o instanceof Worker){
        Worker that = (Worker) o;
        return this.position == that.position;
      }
      else{
        return false;
      }
    }
  }
