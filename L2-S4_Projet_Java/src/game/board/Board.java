package game.board;

import game.board.tile.*;
import java.util.*;
import game.play.player.*;

public class Board{
    private int x;
    private int y;
    private Tile theTiles[][];
    private int stockCommonTile;
    private Tile a;
    private Tile c;
    private boolean b;
    public Board(int x,int y){
        this.x=x;
        this.y=y;
        this.theTiles=new Tile[x][y];
        Random randomno=new Random();
        int d=Math.round((x*y)/3);
        int rng=randomno.nextInt(d+1);
        if (rng==1){
            rng+=1;
        }
        if(rng==0){
            rng+=2;
        }
        this.stockCommonTile=rng;/** stock case de terre */
    }

    /**
    *Create a random Common Tile in coordinate x,y
    *@param x , int abscissa
    *@param y , int ordinates
    *@return CommonTile on tile of coordinate (x,y)
    */
    public Tile randomCommonTile(int x,int y){
        Random randomno=new Random();
        int random=randomno.nextInt(4);
        if (random==0){
            this.stockCommonTile-=1;
            a=new PlainTile(x, y);
        }
        if (random==1){
            this.stockCommonTile-=1;
            a=new ForestTile(x, y);
        }
        if (random==2){
            this.stockCommonTile-=1;
            a=new DesertTile(x, y);
        }
        if (random==3){
            this.stockCommonTile-=1;
            a=new MountainTile(x, y);
        }
        return a;
    }


    /**
    *give a random position of the board
    *@return ArrayList of interger , with one coordinate of board
    */
    public ArrayList<Integer> randomPosition(){
        Random randomno=new Random();
        int randomx=randomno.nextInt(this.x);
        int randomy=randomno.nextInt(this.y);
        ArrayList<Integer> coordinate=new ArrayList<Integer>();
        coordinate.add(randomx);
        coordinate.add(randomy);
        return coordinate;
    }

    /**
    *Create a Random CommonTile on the down tile
    *@return Random CommonTile on the down tile
    */
    public Tile createDownTile(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return randomCommonTile(x, y-1);
    }

    /**
    *Create a Random CommonTile on the Up tile
    *@return Random CommonTile on the Up tile
    */
    public Tile createUpTile(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return randomCommonTile(x, y+1);
    }

    /**
    *Create a Random CommonTile on the right tile
    *@return Random CommonTile on the right tile
    */
    public Tile createRightTile(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return randomCommonTile(x+1, y);
    }

    /**
    *Create a Random CommonTile on the Left tile
    *@return Random CommonTile on the Left tile
    */
    public Tile createLeftTile(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return randomCommonTile(x-1, y);
    }

    /**
    *Create Tile on random adjacent Tile
    *@return Tile on random adjacent Tile
    */
    public Tile standardRandomAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(4);
        if (random==0){
            return this.createDownTile(tile);
        }
        else if (random==1){
            return this.createUpTile(tile);
        }
        else if (random==2){
            return this.createRightTile(tile);
        }
        else{
            return this.createLeftTile(tile);
        }

    }
    /**
    *return true if this Tile has an adjacent CommonTile in standard event
    *@return true if this Tile has an adjacent CommonTile in standard event
    */
    public boolean standardCommonIsAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y-1] instanceof CommonTile) || (this.theTiles[x][y+1] instanceof CommonTile)||(this.theTiles[x+1][y] instanceof CommonTile)||(this.theTiles[x-1][y] instanceof CommonTile);
    }

    /**
    *return true if this Tile is on Left edge event
    *@return true if this Tile is on Left edge event
    */
    public boolean isLeftEdge(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (x==0 && y<this.y-1 && y>0);
    }

    /**
    *return true if this Tile is on Right edge event
    *@return true if this Tile is on Right edge event
    */
    public boolean isRightEdge(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (x==this.x-1 && y<this.y-1 && y>0);
    }

    /**
    *return true if this Tile is on Botton edge event
    *@return true if this Tile is on botton edge event
    */
    public boolean isLowerEdge(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (y==0 && x<this.x-1 && x>0);
    }

    /**
    *return true if this Tile is on Upper edge event
    *@return true if this Tile is on Upper edge event
    */
    public boolean isUpperEdge(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (y==this.y-1 && x<this.x-1 && x>0);
    }

    /**
    *return true if this Tile is on edge event
    *@return true if this Tile is on edge event
    */
    public boolean isEdge(Tile tile){
        return this.isLeftEdge(tile) || this.isLowerEdge(tile)||this.isRightEdge(tile)||this.isUpperEdge(tile);
    }

    /**
    *Create CommonTile for left edge event
    *@param tile , Tile where we put CommonTile
    *@return random commonTile
    */
    public Tile leftEdgeRandomAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(3);
        if (random==0){
            return this.createDownTile(tile);
        }
        else if (random==1){
            return this.createUpTile(tile);
        }
        else{
            return this.createRightTile(tile);
        }
    }

    /**
    *Create CommonTile for right edge event
    *@param tile , Tile where we put CommonTile
    *@return random commonTile
    */
    public Tile rightEdgeRandomAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(3);
        if (random==0){
            return this.createDownTile(tile);
        }
        else if (random==1){
            return this.createUpTile(tile);
        }
        else{
            return this.createLeftTile(tile);
        }
    }

    /**
    *Create CommonTile for upper edge event
    *@param tile , Tile where we put CommonTile
    *@return random commonTile
    */
    public Tile upperEdgeRandomAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(4);
        if (random==0){
            return this.createDownTile(tile);
        }
        else if (random==1){
            return this.createRightTile(tile);
        }
        else{
            return this.createLeftTile(tile);
        }
    }

    /**
    *Create CommonTile for Botton edge event
    *@param tile , Tile where we put CommonTile
    *@return random commonTile
    */
    public Tile lowerEdgeRandomAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(3);
        if (random==0){
            return this.createUpTile(tile);
        }
        else if (random==1){
            return this.createRightTile(tile);
        }
        else{
            return this.createLeftTile(tile);
        }
    }

    /**
    *return True if , the tile has Adjacent CommonTile on Left Edge Event
    *@param tile , tile who need check for adjacent CommonTile
    *@return True if , the tile has Adjacent CommonTile on Left Edge Event
    */
    public boolean isLeftEdgeAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y-1] instanceof CommonTile) || (this.theTiles[x][y+1] instanceof CommonTile) || (this.theTiles[x+1][y]instanceof CommonTile);
    }

    /**
    *return True if , the tile has Adjacent CommonTile on right Edge Event
    *@param tile , tile who need check for adjacent CommonTile
    *@return True if , the tile has Adjacent CommonTile on right Edge Event
    */
    public boolean isRightEdgeAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y-1] instanceof CommonTile) || (this.theTiles[x][y+1] instanceof CommonTile) || (this.theTiles[x-1][y]instanceof CommonTile);
    }

    /**
    *return True if , the tile has Adjacent CommonTile on upper Edge Event
    *@param tile , tile who need check for adjacent CommonTile
    *@return True if , the tile has Adjacent CommonTile on upper Edge Event
    */
    public boolean isUpperEdgeAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y-1] instanceof CommonTile) || (this.theTiles[x-1][y] instanceof CommonTile) || (this.theTiles[x+1][y]instanceof CommonTile);
    }

    /**
    *return True if , the tile has Adjacent CommonTile on Botton Edge Event
    *@param tile , tile who need check for adjacent CommonTile
    *@return True if , the tile has Adjacent CommonTile on Botton Edge Event
    */
    public boolean isLowerEdgeAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y+1] instanceof CommonTile) || (this.theTiles[x-1][y] instanceof CommonTile) || (this.theTiles[x+1][y]instanceof CommonTile);
    }

    /**
    *return True if , the tile has Adjacent CommonTile on Edge Event
    *@param tile , tile who need check for adjacent CommonTile
    *@return True if , the tile has Adjacent CommonTile on Edge Event
    */
    public boolean isEdgeAdjacent(Tile tile){
        if(this.isLeftEdge(tile)){
            return this.isLeftEdgeAdjacent(tile);
        }
        else if(this.isRightEdge(tile)){
            return this.isRightEdgeAdjacent(tile);
        }
        else if(this.isUpperEdge(tile)){
            return this.isUpperEdgeAdjacent(tile);
        }
        else{
            return this.isLowerEdgeAdjacent(tile);
        }
    }
    /**
    *Create Tile when is Edge event
    *@param tile , the tile we are looking
    *@return tile in adjacent tile
    */
    public Tile randomEdgeAdjacent(Tile tile){
        if(this.isLeftEdge(tile)){
            return this.leftEdgeRandomAdjacent(tile);
        }
        else if(this.isRightEdge(tile)){
            return this.rightEdgeRandomAdjacent(tile);
        }
        else if(this.isUpperEdge(tile)){
            return this.upperEdgeRandomAdjacent(tile);
        }
        else{
            return this.lowerEdgeRandomAdjacent(tile);
        }

    }

    /**
    *return True if the tile is on Top Left Corner of board
    *@param tile , the tile we are looking
    *@return True if the tile is on Top Left Corner of board
    */
    public boolean isTopLeftCorner(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (x==0 && y==this.y-1);
    }

    /**
    *return True if the tile is on Top Right Corner of board
    *@param tile , the tile we are looking
    *@return True if the tile is on Top Right Corner of board
    */
    public boolean isTopRightCorner(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (x==this.x-1 && y==this.y-1);
    }

    /**
    *return True if the tile is on Bot Left Corner of board
    *@param tile , the tile we are looking
    *@return True if the tile is on Bot Left Corner of board
    */
    public boolean isBotLeftCorner(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (x==0 && y==0);
    }

    /**
    *return True if the tile is on Bot right Corner of board
    *@param tile , the tile we are looking
    *@return True if the tile is on Bot right Corner of board
    */
    public boolean isBotRightCorner(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (x==this.x-1 && y==0);
    }

    /**
    *return True if the tile is Corner of board
    *@param tile , the tile we are looking
    *@return True if the tile is Corner of board
    */
    public boolean isCorner(Tile tile){
        return this.isBotLeftCorner(tile)||this.isBotRightCorner(tile)||this.isTopLeftCorner(tile)||this.isTopRightCorner(tile);
    }

    /**
    *return true if Bot left conner of board has adjacent CommonTile
    *@param tile , the tile we are looking
    *@return true if Bot left conner of board has adjacent CommonTile
    */
    public boolean isBotLeftCornerAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y+1] instanceof CommonTile)||(this.theTiles[x+1][y] instanceof CommonTile);
    }

    /**
    *return true if Bot Right conner of board has adjacent CommonTile
    *@param tile , the tile we are looking
    *@return true if Bot Right conner of board has adjacent CommonTile
    */
    public boolean isBotRightCornerAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y+1] instanceof CommonTile)||(this.theTiles[x-1][y] instanceof CommonTile);
    }

    /**
    *return true if Top Right conner of board has adjacent CommonTile
    *@param tile , the tile we are looking
    *@return true if Top Right conner of board has adjacent CommonTile
    */
    public boolean isTopRightCornerAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y-1] instanceof CommonTile)||(this.theTiles[x-1][y] instanceof CommonTile);
    }

    /**
    *return true if Top Left conner of board has adjacent CommonTile
    *@param tile , the tile we are looking
    *@return true if Top Left conner of board has adjacent CommonTile
    */
    public boolean isTopLeftCornerAdjacent(Tile tile){
        int x=tile.getPosition().get(0);
        int y=tile.getPosition().get(1);
        return (this.theTiles[x][y-1] instanceof CommonTile)||(this.theTiles[x+1][y] instanceof CommonTile);
    }

    /**
    *return true if one tile of conner of board has adjacent CommonTile
    *@param tile , the tile we are looking
    *@return true if the conner tile of board has adjacent CommonTile
    */
    public boolean isCornerAdjacent(Tile tile){
        if(this.isBotLeftCorner(tile)){
            return this.isBotLeftCornerAdjacent(tile);
        }
        else if(this.isBotRightCorner(tile)){
            return this.isBotRightCornerAdjacent(tile);
        }
        else if(this.isTopRightCorner(tile)){
            return this.isTopRightCornerAdjacent(tile);
        }
        else{
            return this.isTopLeftCornerAdjacent(tile);
        }
    }

    /**
    *create tile on the Adjacent Tile of this tile in Top Left event
    *@param tile the tile we are looking
    *@return tile on the Adjacent Tile of this tile
    */
    public Tile randomTopLeftCornerAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(2);
        if(random==0){
            return this.createDownTile(tile);
        }
        else{
            return this.createRightTile(tile);
        }
    }

    /**
    *create tile on the Adjacent Tile of this tile in Top Right event
    *@param tile the tile we are looking
    *@return tile on the Adjacent Tile of this tile
    */
    public Tile randomTopRightCornerAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(2);
        if(random==0){
            return this.createDownTile(tile);
        }
        else{
            return this.createLeftTile(tile);
        }
    }

    /**
    *create tile on the Adjacent Tile of this tile in Bot Right event
    *@param tile the tile we are looking
    *@return tile on the Adjacent Tile of this tile
    */
    public Tile randomBotRightCornerAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(2);
        if(random==0){
            return this.createUpTile(tile);
        }
        else{
            return this.createLeftTile(tile);
        }
    }

    /**
    *create tile on the Adjacent Tile of this tile in Bot Left event
    *@param tile the tile we are looking
    *@return tile on the Adjacent Tile of this tile
    */
    public Tile randomBotLeftCornerAdjacent(Tile tile){
        Random randomno=new Random();
        int random=randomno.nextInt(2);
        if(random==0){
            return this.createUpTile(tile);
        }
        else{
            return this.createRightTile(tile);
        }
    }

    /**
    *create tile on the Adjacent Tile of this tile in Corner event
    *@param tile the tile we are looking
    *@return tile on the Adjacent Tile of this tile
    */
    public Tile randomCornerAdjacent(Tile tile){
        if(this.isBotLeftCorner(tile)){
            a=this.randomBotLeftCornerAdjacent(tile);
            return a;
        }
        else if(this.isBotRightCorner(tile)){
            a=this.randomBotRightCornerAdjacent(tile);
            return a;
        }
        else if(this.isTopRightCorner(tile)){
            a=this.randomTopRightCornerAdjacent(tile);
            return a;
        }
        else{
            a=this.randomTopLeftCornerAdjacent(tile);
            return a;
        }
    }

    /**
    *create on the board Common Tile when is standard event
    *@param tile the tile we are looking
    */
    public void standartManagement(Tile tile){
        if (!(this.standardCommonIsAdjacent(tile))){
            a=this.standardRandomAdjacent(tile);
            this.theTiles[a.getX()][a.getY()]=a;
        }
    }

    /**
    *create on the board Common Tile when is Edge Event
    *@param tile the tile we are looking
    */
    public void edgeManagement(Tile tile){
        if (!(this.isEdgeAdjacent(tile))){
            a=this.randomEdgeAdjacent(tile);
            this.theTiles[a.getX()][a.getY()]=a;
        }
    }

    /**
    *create on the board Common Tile when is corner Event
    *@param tile the tile we are looking
    */
    public void cornerManagement(Tile tile){
        if (!(this.isCornerAdjacent(tile))){
            a=this.randomCornerAdjacent(tile);
            this.theTiles[a.getX()][a.getY()]=a;
        }
    }

    /**
    *return true if the tile has adjacent CommonTile
    *@param tile the tile we are looking
    *@return return true if the tile has adjacent CommonTile
    */
    public boolean checkAdjacent(Tile tile){
        if(this.isEdge(tile)){
            return this.isEdgeAdjacent(tile);
        }
        else if(this.isCorner(tile)){
            return this.isCornerAdjacent(tile);
        }
        else{
            return this.standardCommonIsAdjacent(tile);
        }
    }

    /**
    *Create Board with all condition
    */
    public void initBoard(){
        System.out.println("\nThe number of available tile is " + (this.stockCommonTile) + "\n");
        ArrayList<Integer> l=new ArrayList<Integer>();
        for (int i=0;i<this.x;i++){
            for (int j=0;j<this.y;j++){
                this.theTiles[i][j]=new Ocean(i,j);
            }
        }
        while(this.stockCommonTile>1){
            l=this.randomPosition();
            while(this.theTiles[l.get(0)][l.get(1)] instanceof CommonTile){
                l=this.randomPosition();
            }
            this.theTiles[l.get(0)][l.get(1)]=randomCommonTile(l.get(0),l.get(1));
            a=this.theTiles[l.get(0)][l.get(1)];
            if(this.stockCommonTile>0){
                if(this.isCorner(a)){
                    this.cornerManagement(a);
                }
                else if(this.isEdge(a)){
                    this.edgeManagement(a);
                }
                else{
                    this.standartManagement(a);
                }
            }
        }
        l=this.randomPosition();
        while((this.theTiles[l.get(0)][l.get(1)] instanceof CommonTile) || !(this.checkAdjacent(this.theTiles[l.get(0)][l.get(1)]))){
            l=this.randomPosition();

        }
        this.theTiles[l.get(0)][l.get(1)]=randomCommonTile(l.get(0),l.get(1));
        c=this.theTiles[l.get(0)][l.get(1)];
    }

    /**
    *Print all tile of the board
    */
    public void displayBoard(){
        String ligne="";
        String ligne2="";
        String ligne3="";
        for (int j=0;j<this.y;j++){
            for (int i=0;i<this.x;i++){
                ligne=ligne+" | "+this.theTiles[i][j].toString();
                if(this.theTiles[i][j].getArmy()!=null){
                    ligne2=ligne2+" | "+this.theTiles[i][j].getArmy().toString();
                }
                else{
                    ligne2=ligne2+" |        ";
                }
                ligne3=ligne3+" |________";
                
            }
            System.out.println(ligne);
            System.out.println(ligne2);
            System.out.println(ligne3);
            ligne="";
            ligne2="";
            ligne3="";
        }
    }

    /**
    *return all Tiles of the board 
    *@return all Tiles of the board
    */
    public Tile[][] getTiles(){
        return this.theTiles;
    }

    /**
    *return arraylist of Adjacent Tile of t , when it's Edge of the board  
    *@param t , Tile who we need to know adjacent tile
    *@return arraylist of Adjacent Tile of t 
    */

    public ArrayList<Tile> getEdgeAdjacent(Tile t){
        ArrayList<Tile> l=new ArrayList<Tile>();
        int x=t.getX();
        int y=t.getY();
        if(this.isUpperEdge(t)){
            l.add(this.theTiles[x-1][y]);
            l.add(this.theTiles[x+1][y]);
            l.add(this.theTiles[x][y-1]);
        }
        if(this.isLowerEdge(t)){
            l.add(this.theTiles[x-1][y]);
            l.add(this.theTiles[x+1][y]);
            l.add(this.theTiles[x][y+1]);
        }
        if(this.isLeftEdge(t)){
            l.add(this.theTiles[x][y-1]);
            l.add(this.theTiles[x+1][y]);
            l.add(this.theTiles[x][y+1]);
        }
        if(this.isRightEdge(t)){
            l.add(this.theTiles[x][y-1]);
            l.add(this.theTiles[x-1][y]);
            l.add(this.theTiles[x][y+1]);
        }
        return l;        
    }

    /**
    *return arraylist of Adjacent Tile of t , when it's Corner of the board 
    *@param t , Tile who we need to know adjacent tile
    *@return arraylist of Adjacent Tile of t 
    */
    
    public ArrayList<Tile> getCornerAdjacent(Tile t){
        ArrayList<Tile> l=new ArrayList<Tile>();
        int x=t.getX();
        int y=t.getY();
        if(this.isTopRightCorner(t)){
            l.add(this.theTiles[x-1][y]);
            l.add(this.theTiles[x][y-1]);
        }
        if(this.isTopLeftCorner(t)){
            l.add(this.theTiles[x+1][y]);
            l.add(this.theTiles[x][y-1]);
        }
        if(this.isBotLeftCorner(t)){
            l.add(this.theTiles[x+1][y]);
            l.add(this.theTiles[x][y+1]);
        }
        if(this.isBotRightCorner(t)){
            l.add(this.theTiles[x-1][y]);
            l.add(this.theTiles[x][y+1]);
        }
        return l;        
    }

    /**
    *return arraylist of Adjacent Tile of t , when it's standart tile of the board  
    *@param t , Tile who we need to know adjacent tile
    *@return arraylist of Adjacent Tile of t 
    */

    public ArrayList<Tile> getStandartAdjacent(Tile t){
        ArrayList<Tile> l=new ArrayList<Tile>();
        int x=t.getX();
        int y=t.getY();
        l.add(this.theTiles[x-1][y]);
        l.add(this.theTiles[x][y-1]);
        l.add(this.theTiles[x+1][y]);
        l.add(this.theTiles[x][y+1]);
        return l;
    }

    /**
    *return arraylist of Adjacent Tile of t
    *@param t , Tile who we need to know adjacent tile
    *@return arraylist of Adjacent Tile of t 
    */

    public ArrayList<Tile> getAdjacent(Tile t){
        if(this.isEdge(t)){
            return this.getEdgeAdjacent(t);
        }
        if(this.isCorner(t)){
            return this.getCornerAdjacent(t);
        }
        else{
            return this.getStandartAdjacent(t);
        }
    }

    /**
    *return True when all Tile is Occupied
    *@return True when all Tile is Occupied
    */
    public Boolean allTileOccupied(){
        for (int x = 0;x<this.x;x++){
            for(int y = 0;y<this.y;y++){
                if ((this.theTiles[x][y] instanceof CommonTile) && !(this.theTiles[x][y].getIsOccuped())){
                    return false;
                }
            }
        }
        return true;
    }

    /**
    *return arrayList of free CommonTile in the board
    *@return arrayList of free CommonTile in the board
    */
    public ArrayList<CommonTile> getFreeCommonTiles (){
        ArrayList<CommonTile> l= new ArrayList<CommonTile>();
        for (int x = 0;x<this.x;x++){
            for(int y = 0;y<this.y;y++){
                if ((this.theTiles[x][y] instanceof CommonTile) && !(this.theTiles[x][y].getIsOccuped())){
                    l.add((CommonTile)this.theTiles[x][y]);
                }
            }
        }
        return l;
    }

    /**
    *change the state of the Tile to Not ocupied 
    *@param x,y the coordinate of the Tile 
    */
    public void setCharacToNull(int x,int y){
        this.theTiles[x][y].setArmyToNull();
    }

    /**
    *Set player for 1 army 
    *@param x,y Cordinate of the Tile 
    *@param p , The Player 
    */
    public void setPlayer(int x,int y,WarPlayer p){
        this.theTiles[x][y].getArmy().setPlayer(p);
    }

    
    
    /**
    * @return the x of the Tile 
    */
    public int getX(){
        return this.x;
    }

    /**
    * @return the y of the Tile 
    */
    public int getY(){
        return this.y;
    }
}
