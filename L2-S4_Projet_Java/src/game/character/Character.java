package game.character;

import game.board.*;
import game.board.tile.*;

public class Character{

    protected int gold;
    protected CommonTile position;


    /**
    *Create a new Character
    *@param tile a CommonTile
    */
    public Character(CommonTile tile){
      this.gold = 0;
      this.position = tile;
    }

    /**
    *return the position of the character
    *@return the position of the character
    */
    public CommonTile getPosition(){
     return this.position;
    }

    /**
    *return the number of gold
    *@return the number of gold
    */
    public int getGold(){
      return this.gold;
    }

    /**
    *add gold for the character
    *@param gold the number of gold
    */
    public void addGold(int gold){
      this.gold += gold;
    }

}
