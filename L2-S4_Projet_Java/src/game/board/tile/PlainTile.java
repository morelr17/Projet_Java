package game.board.tile;

import game.board.resource.*;

public class PlainTile extends CommonTile{

    public PlainTile(int x,int y){
        super(x, y,Resource.Wheat,1,5,1,1);
    }

    /**
    *@return the coordinate of the Tile and the type with P for Plain
    */
    public String toString(){
      return "P:(" + getX() + "," + getY() + ")";
    }

}
