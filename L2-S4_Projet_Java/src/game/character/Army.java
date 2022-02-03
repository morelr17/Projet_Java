package game.character;

import game.board.*;
import game.board.tile.*;
import game.play.*;
import game.exception.*;
import game.play.player.*;

import java.util.*;

public class Army extends Character {


    private int nbWarrior;
    private boolean needEat;
    private int cost;
    private int capacity;
    private int x;
    private int y;
    private WarPlayer player;
    private int index;
    private Tile[][] theTiles;
    private Board board;

    /**
     * create an army of nbWarrior in the position (the tile we used in param)
     * @param nbWarrior
     * @param t a tile
     * @param board a board
     * @param p a warPlayer
     * @param index an index
     * @throws MaxCapacityException
     */
    public Army(int nbWarrior,CommonTile t,Board board,WarPlayer p,int index) throws MaxCapacityException,TileIsOccupedException,TileCantBeOccupedException{
        super(t);
        this.theTiles=board.getTiles();
        this.cost = position.getArmyCost();
        this.capacity = position.getMaxCapacity();
        this.player=p;
        this.index=index;
        this.board=board;
        this.x=t.getX();
        this.y=t.getY();
        
        if(!(this.position.getCanBeOccupied())){
          throw new TileCantBeOccupedException("This Tile can't be Occupied because this is an oceanTile");
        }
        if(nbWarrior > position.getMaxCapacity() ) {
          throw new MaxCapacityException("Maximal size of an army on this tile is " + this.capacity + " warrior(s)");
        }
        if(this.position.getIsOccuped()){
            throw new TileIsOccupedException("This tile is already occupied !");
        }
        this.nbWarrior=nbWarrior;
        this.theTiles[this.x][this.y].setArmy(this);
        this.theTiles=this.board.getTiles();
        if(t instanceof MountainTile){
          this.nbWarrior+=2;
        }
        ArrayList<Tile> l=board.getAdjacent(this.position);
        for(int i=0;i<l.size();i++){
          if(l.get(i) instanceof CommonTile && l.get(i).getIsOccuped()){
            this.gestionArmyAdjacent(l.get(i));
          }
        }
        
    }

    /**
    *@return warPlayer 
    */
    public WarPlayer getPlayer(){
      return this.player;
    }

    /**
    *set Player
    *@param p , warPlayer
    */
    public void setPlayer(WarPlayer p){
      this.player = p ;
    }

    /**
    *manages the effects of adjacent armies
    *@param tile the Tile where the arm will be placed 
    */
    public void gestionArmyAdjacent(Tile tile){

      if((tile.getArmy().getPlayer().getPseudo() == this.getPlayer().getPseudo()) && (tile.getArmy().nbWarrior<this.nbWarrior)){
        if(tile instanceof MountainTile){
          tile.getArmy().addWarriorMountain(1);
        }
        else{
          tile.getArmy().addWarrior(1);
        }
        this.addGold(1);
      }

      else if ((tile.getArmy().getPlayer().getPseudo() != this.getPlayer().getPseudo()) && (tile.getArmy().nbWarrior<this.nbWarrior) && (tile.getArmy().nbWarrior==1 || (tile.getArmy().nbWarrior==3 && tile instanceof MountainTile))){
        this.board.setPlayer(tile.getX(),tile.getY(), this.getPlayer());
        this.getPlayer().setCharacter(tile.getArmy());
        tile.getArmy().getPlayer().deleteArmy(tile.getArmy().index);
        this.addGold(2);
        System.out.println("l'armée en ("+tile.getX()+","+tile.getY()+") est prise par "+this.getPlayer().getPseudo());
      }
      else if((tile.getArmy().getPlayer().getPseudo() != this.getPlayer().getPseudo()) && (tile.getArmy().nbWarrior<this.nbWarrior)){
        if(tile instanceof MountainTile){
          tile.getArmy().looseWarriorMountain();
        }
        else{
          tile.getArmy().looseWarrior(tile.getArmy().nbWarrior/2);
        }
      }
    }

    /**
    *return the number of warrior in the army
    *@return the number of warrior in the army
    */
    public int getNbWarrior(){
      return this.nbWarrior;
    }

    /**
    *return the max capacity of the army
    *@return the max capacity of the army
    */
    public int getMaxCapacity(){
      return this.capacity;
    }

    /**
    *Add n warrior in army
    *@param n int number of warrior who need to add
    *on as besoin de la modélisation des joueurs,ce code vas changer quoi qu'il arrive
    */
    public void addWarrior(int n){
      if ((this.nbWarrior+n) <= this.capacity){
        this.nbWarrior = this.nbWarrior+n;
        System.out.println("The ("+this.x+","+this.y+") army of player "+this.player.getPseudo()+" won "+n+" warrior this army have now " + this.nbWarrior + " warrior(s)");
      }
    }

    public void addWarriorMountain(int n){
      if ((this.nbWarrior+n-2) <= this.capacity){
        this.nbWarrior = this.nbWarrior+n;
        System.out.println("The ("+this.x+","+this.y+") army of player "+this.player.getPseudo()+" won "+n+" warrior this army have now " +(this.nbWarrior-2)+ " warrior(s)");
      }
    }

    /**
    *took off warrior of your army
    *@param n int number of warrior who need to loose
    *on vas peut être déplacer cette fonction autre part
    */
    public void looseWarrior(int n){
        if ((this.nbWarrior-n)<=0){
          this.getPlayer().getCharacters().remove(this.index);
          this.theTiles[this.position.getX()][this.position.getY()].setArmyToNull();
        }
        else{
          this.nbWarrior = this.nbWarrior-n;
          System.out.println("The ("+this.x+","+this.y+") army of player "+this.player.getPseudo()+" lost "+n+" warrior this army have now " + this.nbWarrior + " warrior(s)");
        }
    }

    public void looseWarriorMountain(){
      if(this.position instanceof MountainTile){
        if(this.nbWarrior==4){
          this.nbWarrior = this.nbWarrior-1;
          System.out.println("The ("+this.x+","+this.y+") army of player "+this.player.getPseudo()+" lost "+1+" warrior this army have now " + (this.nbWarrior-2) + " warrior(s)");
        }
        else if(this.nbWarrior==3){
          this.getPlayer().getCharacters().remove(this.index);
          this.theTiles[this.position.getX()][this.position.getY()].setArmyToNull();
        }
      }
    }

    /**
    * return the amount of food that you need to give
    * @return the amount of food that you need to give
    */
    public int getCost(){
      if(this.position instanceof MountainTile){
        return (nbWarrior*this.position.getArmyCost())-2;
      }
      else{
        return nbWarrior*this.position.getArmyCost();
      }
    }

    /**
    *@return True if o is the same army 
    *@param o , object 
    */
    public boolean equals(Object o){
      if((o instanceof Army) && (o!=null)){
        Army that = (Army) o;
        return this.x == that.getX() && this.y==that.getY();
      }
      else{
        return false;
      }
    }

    /**
    * @return absissa of the Tile 
    */
    public int getX(){
      return this.x;
    }
    
    /**
    *@return ordinate of the Tile 
    */
    public int getY(){
      return this.y;
    }

    /**
    *@return String with information of the army and who have this Army 
    */
    public String toString(){
      return "A("+this.player.getPseudo()+","+this.nbWarrior+")";
    }

    /**
    * put index for the army 
    */
    public void setIndex(int x){
      this.index=x;
    }
}
