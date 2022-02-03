package game.character;

import game.board.*;
import game.board.tile.*;
import game.exception.*;

import org.junit.*;
import static org.junit.Assert.*;


public class CharacterTest{

    @Test
    public void getPositionTest(){
        CommonTile tile = new CommonTile(5,8,null);
        Character character = new Character(tile);
        assertEquals(character.getPosition(),tile);
    }

    @Test 
    public void getGoldTest(){
        CommonTile tile = new CommonTile(5,8,null);
        Character character = new Character(tile);
        assertEquals(character.getGold(),0);
    }

    @Test
    public void addGoldTest() throws TileCantBeOccupedException,TileIsOccupedException{
      CommonTile tile = new CommonTile(5,5,null);
      Character character = new Character(tile);
      character.addGold(5);
      assertEquals(5,character.getGold());
    }

  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.character.CharacterTest.class);
  }
}