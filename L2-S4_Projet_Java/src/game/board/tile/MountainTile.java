package game.board.tile;

import game.board.resource.*;

public class MountainTile extends CommonTile{

    public MountainTile(int x,int y){
        super(x, y,Resource.Stone,5,3,1,0);
        
    }

  /**
    *@return the coordinate of the Tile and the type with M for Mountain
    */
    public String toString(){
      return "M:(" + getX() + "," + getY() + ")";
    }
}
