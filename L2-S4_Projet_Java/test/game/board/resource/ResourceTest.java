package game.board.resource;

import org.junit.*;
import static org.junit.Assert.*;

public class ResourceTest{

  @Test
  public void getPriceTest(){
    assertEquals(5,Resource.Sand.getPrice());
  }

  @Test
  public void getFoodTest(){
    assertEquals(0,Resource.Sand.getFood());
  }

  public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.board.resource.ResourceTest.class);
  }

}