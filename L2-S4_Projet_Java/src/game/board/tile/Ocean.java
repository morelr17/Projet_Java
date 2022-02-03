package game.board.tile;

public class Ocean extends SpecialTile{

    public Ocean(int x,int y){
        super(x, y);
    }

    /**
    *@return the coordinate of the Tile and the type with O for Ocean
    */
    public String toString(){
      return "O:(" + getX() + "," + getY() + ")";
    }
}
