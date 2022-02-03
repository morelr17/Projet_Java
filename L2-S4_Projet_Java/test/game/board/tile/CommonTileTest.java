package game.board.tile;

import game.board.resource.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CommonTileTest{


  @Test
  public void getResourceTest(){
    CommonTile t = new CommonTile(5,5,Resource.Wheat);
    assertEquals(Resource.Wheat,t.getResource());
  }

  @Test
  public void getArmyCostTest(){
    CommonTile t = new CommonTile(5,5,Resource.Wheat,1,5,1,2);
    assertEquals(1,t.getArmyCost());
  }

  @Test
  public void getWorkerCostTest(){
    CommonTile t = new CommonTile(5,5,Resource.Wheat,1,5,1,2);
    assertEquals(1,t.getWorkerCost());
  }

  @Test
  public void getMaxCapacityTest(){
    CommonTile t = new CommonTile(5,5,Resource.Wheat,1,5,1,2);
    assertEquals(5,t.getMaxCapacity());
  }

  public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.board.tile.CommonTileTest.class);
  }

}
