package game.character;

import game.board.*;
import game.board.tile.*;
import game.play.*;
import game.play.player.*;
import game.exception.*;

import org.junit.*;
import static org.junit.Assert.*;


public class WorkerTest{


  @Test
  public void getCostTest() throws TileCantBeOccupedException,TileIsOccupedException{
        DesertTile tile = new DesertTile(5,5);
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Abass",board);
        Worker worker = new Worker(tile,board,player);
        assertEquals(3,worker.getCost());
  }

  @Test
  public void getPlayerTest() throws TileCantBeOccupedException,TileIsOccupedException{
    CommonTile tile = new CommonTile(5,5);
    Board board = new Board(10,10);
    FarmingPlayer player = new FarmingPlayer("Nawfel",board);
    Worker worker = new Worker(tile,board,player);
    assertEquals("Nawfel",worker.getPlayer().getPseudo());
  }

  @Test
  public void equalsTrueTest() throws TileCantBeOccupedException,TileIsOccupedException{
      CommonTile tile = new CommonTile(5,5);
      Board board = new Board(10,10);
      FarmingPlayer player = new FarmingPlayer("Romain",board);
      Worker worker1 = new Worker(tile,board,player);
      Worker worker2 = new Worker(tile,board,player);
      assertTrue(worker1.equals(worker2));
  }

  @Test
  public void equalsFalseTest() throws TileCantBeOccupedException,TileIsOccupedException{
      CommonTile tile1 = new CommonTile(5,5);
      CommonTile tile2 = new CommonTile(3,5);
      Board board = new Board(10,10);
      FarmingPlayer player1 = new FarmingPlayer("Nawfel",board);
      FarmingPlayer player2 = new FarmingPlayer("Abass",board);
      Worker worker1 = new Worker(tile1,board,player1);
      Worker worker2 = new Worker(tile2,board,player2);
      assertFalse(worker1.equals(worker2));
  }


  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.character.WorkerTest.class);
  }
}
