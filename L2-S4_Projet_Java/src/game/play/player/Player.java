package game.play.player;

import game.board.*;
import game.board.resource.*;

import java.util.HashMap;
import java.util.Map;

public class Player{

  private Map<Resource,Integer> resources;
  private String pseudo;
  protected int amountGold;

  /**
  *Create a player
  *@param pseudo the pseudo of the player
  */
  public Player(String pseudo){
    this.pseudo = pseudo;
    this.resources = new HashMap<Resource,Integer>();
    this.resources.put(Resource.Wheat,0);
    this.resources.put(Resource.Stone,0);
    this.resources.put(Resource.Sand,0);
    this.resources.put(Resource.Wood,0);
    this.amountGold = 0;
  }

  /**
  *return the pseudo of the player
  *@return the pseudo of this player 
  */
  public String getPseudo(){
    return this.pseudo;
  }

  /**
  *add resource for the player
  *@param resource the type of resource
  */
  public void addResource(Resource resource){
    int value =this.resources.get(resource);
    this.resources.put(resource,value+1);
  }

  /**
  *remove resource of the player
  */
  public void resetResources(){
    for (Resource resource : this.resources.keySet()) {
      this.resources.put(resource,0);
    }
  }

  /**
  *return the HashMap of all the resources
  *@return the HashMap of all the resources
  */
  public Map<Resource,Integer> getResources(){
    return this.resources;
  }

   /**
  *return the number of gold
  *@return the number of gold
  */
  public int getAmountGold(){
    return this.amountGold;
  }

  /**
  *add gold to the player and return the number of gold that you win
  *@return the number of gold that you win
  *@param gold the number of gold that you loose
  */
  public int addGold(int gold){
    this.amountGold += gold;
    return gold;
  }

}
