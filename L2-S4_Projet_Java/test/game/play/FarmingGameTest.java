package game.play;

import game.character.*;
import game.board.*;
import game.board.tile.*;
import game.play.player.*;
import game.exception.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class FarmingGameTest{

    @Test
    public void addPlayerTest(){
        Board board = new Board(10,10);
        FarmingGame game = new FarmingGame(board,6);

        FarmingPlayer player = new FarmingPlayer("Nawfel",board);
        game.addPlayer(player);

        assertEquals(1,game.getNbPlayers());
    }

    @Test
    public void getPlayersTest(){
        Board board = new Board(10,10);
        FarmingGame game = new FarmingGame(board,6);

        FarmingPlayer player = new FarmingPlayer("Nawfel",board);
        
        List<FarmingPlayer> fl = new ArrayList<FarmingPlayer>();
        fl.add(player);
        game.addPlayer(player);

        assertEquals(fl,game.getPlayers());
    }

    @Test
    public void getNbPlayersTest(){
        Board board = new Board(10,10);
        FarmingGame game = new FarmingGame(board,6);

        FarmingPlayer player1 = new FarmingPlayer("Nawfel",board);
        FarmingPlayer player2 = new FarmingPlayer("Abass",board);
        
        game.addPlayer(player1);
        game.addPlayer(player2); 

        assertEquals(2,game.getNbPlayers());
    }

    @Test
    public void classementTest() throws TileCantBeOccupedException,TileIsOccupedException{
        Board board = new Board(10,10);
        FarmingGame game = new FarmingGame(board,6);

        FarmingPlayer player1 = new FarmingPlayer("Nawfel",board);
        FarmingPlayer player2 = new FarmingPlayer("Abass",board);

        CommonTile tile1 = new CommonTile(5,5);
        CommonTile tile2 = new CommonTile(5,6);

        Worker worker1 = new Worker(tile1,board,player1);
        Worker worker2 = new Worker(tile2,board,player2);

        game.addPlayer(player1);
        game.addPlayer(player2);

        player1.addWorker(worker1);
        player2.addWorker(worker2);

        worker2.addGold(5);
        
        List<FarmingPlayer> classement = new ArrayList<FarmingPlayer>();
        classement.add(player2);
        classement.add(player1);

        assertEquals(classement,game.classement());
    }

    @Test
    public void winnerTest() throws TileCantBeOccupedException,TileIsOccupedException{
        Board board = new Board(10,10);
        FarmingGame game = new FarmingGame(board,6);

        FarmingPlayer player1 = new FarmingPlayer("Nawfel",board);
        FarmingPlayer player2 = new FarmingPlayer("Abass",board);
        FarmingPlayer player3 = new FarmingPlayer("Romain",board);

        CommonTile tile1 = new CommonTile(5,5);
        CommonTile tile2 = new CommonTile(5,6);
        CommonTile tile3 = new CommonTile(5,7);

        Worker worker1 = new Worker(tile1,board,player1);
        Worker worker2 = new Worker(tile2,board,player2);
        Worker worker3 = new Worker(tile3,board,player3);

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        player1.addWorker(worker1);
        player2.addWorker(worker2);
        player3.addWorker(worker3);

        worker2.addGold(5);
        worker3.addGold(10);

        assertEquals(player3,game.winner());
    }

  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.play.FarmingGameTest.class);
  }
}