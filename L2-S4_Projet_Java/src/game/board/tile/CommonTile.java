package game.board.tile;

import game.board.resource.*;

public class CommonTile extends Tile{

    private Resource resource;
    private int armyCost;
    private int workerCost;
    private int maxCapacity;
    private int workerGold;

    /**
     * create a commonTile at the position (x,y) in the board
     * @param x abscissa of the tile
     * @param y ordinate of the tile
     */
    public CommonTile(int x,int y){
        this(x,y,null);
    }

    /**
     * create a commonTile at the position (x,y) in the board
     * @param x abscissa of the tile
     * @param y ordinate of the tile
     * @param resource the resource of the tile
     */
    public CommonTile(int x,int y,Resource resource){
        super(x,y);
        this.resource = resource;
    }

    /**
     * create a commonTile at the position (x,y) in the board
     * @param x abscissa of the tile
     * @param y ordinate of the tile
     * @param resource the resource of the tile
     * @param workerCost the cost of the worker in this tile
     * @param maxCapacity the max capacity of the army in this tile
     * @param armyCost the cost the army in this tile
     * @param workerGold the gold of the worker in this tile
     */
    public CommonTile(int x,int y,Resource resource, int workerCost, int maxCapacity, int armyCost,int workerGold){
        this(x,y,resource);
        this.workerCost = workerCost;
        this.maxCapacity = maxCapacity;
        this.armyCost = armyCost;
        this.workerGold = workerGold;
    }

    /**
     * @return the resource of the tile
     */
    public Resource getResource() {
        return this.resource;
    }

    /**
     * @return the upkeep cost of an army on this tile
     */
    public int getArmyCost(){
        return this.armyCost;
    }

    /**
     * @return the upkeep cost of a worker on this tile
     */
    public int getWorkerCost(){
        return this.workerCost;
    }

    /**
    *@return the max capicity of the tile 
    */
    public int getMaxCapacity(){
      return this.maxCapacity;
    }

    /**
     * @return the upkeep gold of a worker on this tile
     */
    public int getWorkerGold(){
        return this.workerGold;
    }

}
