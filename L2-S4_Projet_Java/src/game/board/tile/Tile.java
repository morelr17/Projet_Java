package game.board.tile;

import game.character.*;

import java.util.List;
import java.util.ArrayList;

public class Tile{
  protected boolean canBeOccupied;
  private boolean isOccuped;
  private int x;
  private int y;
  protected Army army;
  
  /**
   * Create a Tile at position(x,y) in the board
   * @param x abscissa of the tile
   * @param y ordinate of the tile
   */
  public Tile(int x,int y){
    this.x=x;
    this.y=y;
    this.canBeOccupied=true;
    this.isOccuped=false;
    this.army=null ;
  }

  /**return the boolean who said if a tile can be occupied or not
   * @return return true if the boolean that said if the tile can be occupied or not
  */
  public boolean getCanBeOccupied(){
    return this.canBeOccupied;
  }

  /**
  * liberate the tile
   */
  public void free(){
    this.isOccuped = false;
  }
  /**
  * the case is occuped
   */
  public void occupate(){
    this.isOccuped = true;
  }

  /**return the location(x,y) of the tile
   * @return the location(x,y) of the tile in the board
   */
  public List<Integer> getPosition(){
    List<Integer> coordinate=new ArrayList<Integer>();
    coordinate.add(this.x);
    coordinate.add(this.y);
    return coordinate;
  }

  /**
  * @return the X of the Tile 
  */
  public int getX(){
    return this.x;
  }

  /**
  * @return the Y of the Tile 
  */
   public int getY(){
    return this.y;
  }

  /**
   * @return the boolean that said if the tile is occuped or not
   */
  public boolean getIsOccuped(){
    return this.isOccuped;
  }

  /**
  *put 1 army on this Tile 
  *@param c , it's 1 Army 
  */
  public void setArmy(Army c){
    this.army = c;
    this.isOccuped=true;
  }

  
  /**
  * delete army of the Tile
  */
  public void setArmyToNull(){
    this.army=null;
    this.isOccuped=false;
  }

  /**
  *return Army on this Tile
  *@return Army on this Tile
  */
  public Army getArmy(){
    return this.army;
  }

  

}