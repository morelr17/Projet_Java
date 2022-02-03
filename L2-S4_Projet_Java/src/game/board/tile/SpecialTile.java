package game.board.tile;

public class SpecialTile extends Tile{
    public SpecialTile(int x, int y){
        super(x, y);
        this.canBeOccupied=false;
    }
}


