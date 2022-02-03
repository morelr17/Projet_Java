package game.play.player;

import game.board.*;
import game.character.*;
import game.board.tile.*;
import game.play.*;
import game.play.player.*;
import game.board.resource.*;
import game.exception.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class WarPlayerTest{
    private Board b;
    private WarPlayer p;
    private Army a1;
    private PlainTile t;
    @Before
    public void init() throws Exception{
        this.b=new Board(3,3);
        b.initBoard();
        this.p=new WarPlayer("TEST",b);
        this.t=new PlainTile(1,2);
        this.a1=new Army(1,t,b,p,0);                
    }

    @Test 
    public void getNbWarriorsTest(){
        assertEquals(this.p.getNbWarriors(),35);
    }

    @Test 
    public void setCharactersTest(){
        this.p.setCharacter(this.a1);
        assertTrue(this.p.getCharacters().get(0).equals(this.a1));
    }

    @Test
    public void deleteArmyTest(){
        this.p.setCharacter(this.a1);
        this.p.deleteArmy(0);
        assertEquals(this.p.getCharacters().size(),0);
    }

    @Test 
    public void removeWarriorsTest(){
        this.p.removeWarriors(5);
        assertEquals(this.p.getNbWarriors(),30);/**car de base a 35 */
    }

    



  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.play.player.WarPlayerTest.class);
  }
}
