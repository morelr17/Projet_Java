package game.board.tile;

import game.character.*;
import game.play.*;
import game.exception.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class TileTest{


  @Test
  public void getCanBeOccupiedTest(){
      Tile tile = new Tile(5,8);
      assertTrue(tile.getCanBeOccupied());
  }

  @Test
  public void occupateTest(){
      Tile tile = new Tile(5,5);
      tile.occupate();
      assertTrue(tile.getIsOccuped());
  }

  @Test
  public void freeTest(){
      Tile tile = new Tile(5,5);
      tile.occupate();
      tile.free();
      assertFalse(tile.getIsOccuped());
  }

     
  @Test
  public void getPositionTest(){
      Tile tile = new Tile(5,8);
      List<Integer> l = new ArrayList<Integer>();
      l.add(5);
      l.add(8);
      assertEquals(l,tile.getPosition());
  }
  

  @Test
  public void getXTest(){
      Tile tile = new Tile(5,8);
      assertEquals(5,tile.getX());
  }

  @Test
  public void getYTest(){
      Tile tile = new Tile(5,8);
      assertEquals(8,tile.getY());
  }

  @Test
  public void getIsOccupedTest(){
      Tile tile = new Tile(5,8);
      assertFalse(tile.getIsOccuped());
  }

  /*
  @Test
  public void setArmyTest() throws MaxCapacityException,TileIsOccupedException,TileCantBeOccupedException{
      CommonTile tile = new CommonTile(5,8);
      Board board = new Board(10,10);

      WarPlayer player = new WarPlayer("Abass",board);
      Army army = new Army(3,tile,board,player,3);

      tile.setArmy(army);

      assertEquals(army,tile.getArmy());
      assertTrue(tile.getIsOccuped());
  }
  

  @Test
  public void setArmyToNullTest() throws MaxCapacityException,TileIsOccupedException,TileCantBeOccupedException{
      CommonTile tile = new CommonTile(5,8);
      Board board = new Board(10,10);

      WarPlayer player = new WarPlayer("Abass",board);
      Army army = new Army(3,tile,board,player,3);

      tile.setArmy(army);
      tile.setArmyToNull();

      assertNull(tile.getArmy());
      assertFalse(tile.getIsOccuped());
  }
  */
  

  @Test
  public void getArmyTest(){
      Tile tile = new Tile(5,8);
      assertNull(tile.getArmy());
  }

  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.board.tile.TileTest.class);
  }

}
