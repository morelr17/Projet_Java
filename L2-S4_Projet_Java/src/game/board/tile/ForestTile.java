package game.board.tile;

import game.board.resource.*;

public class ForestTile extends CommonTile{

    public ForestTile(int x,int y){
        super(x, y,Resource.Wood,1,5,1,1);
    }

    /**
    *@return the coordinate of the Tile and the type with F for Forest 
    */
    public String toString(){
      return "F:(" + getX() + "," + getY() + ")";
    }
}
