package game.play.player;

import game.character.*;
import game.board.*;
import game.board.tile.*;
import game.board.resource.*;
import game.exception.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;


public class FarmingPlayerTest{

    @Test
    public void looseGoldTest(){
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Abass",board);
        player.looseGold(2);
        assertEquals(13,player.getAmountGold());
    }

    @Test
    public void getNbWorkerTest(){
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Nawfel",board);
        assertEquals(0,player.getNbWorker());
    }

    @Test
    public void getAllWorkersTest() throws TileCantBeOccupedException, TileIsOccupedException{
        CommonTile tile = new CommonTile(5,5);
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Abass",board);
        Worker worker = new Worker(tile,board,player);
        player.addWorker(worker);
        
        List<Worker> workers = new ArrayList<Worker>();
        workers.add(worker);

        assertEquals(workers,player.getAllWorkers());
    }

    @Test
    public void getBilanTest()throws TileCantBeOccupedException,TileIsOccupedException{
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Nawfel",board);
        CommonTile tile = new CommonTile(6,6);
        Worker worker = new Worker(tile,board,player);
        worker.addGold(5);
        player.addWorker(worker);
        assertEquals(5,player.getBilan());
    }

    @Test
    public void addWorkerTest() throws TileCantBeOccupedException,TileIsOccupedException{
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Romain",board);
        CommonTile tile = new CommonTile(6,6);
        Worker worker = new Worker(tile,board,player);
        player.addWorker(worker);
        assertEquals(1,player.getNbWorker());
    }

    @Test
    public void changeResourceToGoldTest(){
        Board board = new Board(10,10);
        FarmingPlayer player = new FarmingPlayer("Nawfel",board);

        Resource sand = Resource.Sand;
        Resource stone = Resource.Stone;
        Resource wheat = Resource.Wheat;
        Resource wood1 = Resource.Wood;
        Resource wood2 = Resource.Wood;

        player.addResource(sand);
        player.addResource(stone);
        player.addResource(wheat);
        player.addResource(wood1);
        player.addResource(wood2);

        player.changeResourceToGold();

        assertEquals(34,player.getAmountGold());
    }

  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.play.player.FarmingPlayerTest.class);
  }
}