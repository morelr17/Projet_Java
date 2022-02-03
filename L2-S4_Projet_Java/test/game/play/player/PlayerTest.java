package game.play.player;

import game.board.*;
import game.board.resource.*;
import game.exception.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import static org.junit.Assert.*;


public class PlayerTest{

    @Test
    public void getPseudoTest(){
        Player player = new Player("Abass");
        assertEquals(player.getPseudo(),"Abass");
    }

    @Test
    public void addResourceStoneTest(){
        Player player = new Player("Nawfel");
        Resource ressource = Resource.Stone;
        player.addResource(ressource);
        assertSame(1,player.getResources().get(Resource.Stone));
    }
    
    @Test
    public void addResourceWheatTest(){
        Player player = new Player("Romain");
        Resource ressource = Resource.Wheat;
        player.addResource(ressource);
        assertSame(1,player.getResources().get(Resource.Wheat));
    }

    @Test
    public void addResourceSandTest(){
        Player player = new Player("Felix");
        Resource ressource = Resource.Sand;
        player.addResource(ressource);
        assertSame(1,player.getResources().get(Resource.Sand));
    }

    @Test
    public void addResourceWoodTest(){
        Player player = new Player("Nawfel");
        Resource ressource = Resource.Wood;
        player.addResource(ressource);
        assertSame(1,player.getResources().get(Resource.Wood));
    }

    @Test
    public void resetResourceTest(){
        Player player = new Player("Nawfel");

        Resource wood = Resource.Wood;
        player.addResource(wood);

        Resource sand = Resource.Sand;
        player.addResource(sand);

        Resource wheat = Resource.Wheat;
        player.addResource(wheat);

        Resource stone = Resource.Stone;
        player.addResource(stone);
        
        player.resetResources();

        assertSame(0,player.getResources().get(wood));
        assertSame(0,player.getResources().get(sand));
        assertSame(0,player.getResources().get(stone));
        assertSame(0,player.getResources().get(wheat));
    }

    @Test
    public void getResourcesTest(){
        Player player = new Player("Nawfel");

        Map<Resource,Integer> resource = new HashMap<Resource,Integer>();
        resource.put(Resource.Wheat,0);
        resource.put(Resource.Stone,0);
        resource.put(Resource.Sand,0);
        resource.put(Resource.Wood,1);

        Resource wood = Resource.Wood;
        player.addResource(wood);

        assertEquals(resource,player.getResources());
    }

    @Test
    public void getAmountGoldTest(){
        Player player = new Player("Abass");
        assertEquals(0,player.getAmountGold());
    }

    @Test
    public void addGoldTest(){
        Player player = new Player("Nawfel");
        assertEquals(2,player.addGold(2));
        assertEquals(2,player.getAmountGold());
    }

  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.play.player.PlayerTest.class);
  }
}