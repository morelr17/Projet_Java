package game.board.tile;

import game.board.resource.*;

public class DesertTile extends CommonTile{


    public DesertTile(int x,int y){
        super(x, y,Resource.Sand,3,3,2,2);
    }

    /**
    *@return coordinate of Tile and type with D for desert
    */
    public String toString(){
      return "D:(" + getX() + "," + getY() + ")";
    }
}
