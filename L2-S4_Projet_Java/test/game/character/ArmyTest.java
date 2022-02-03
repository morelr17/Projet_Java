package game.character;

import game.board.*;
import game.board.tile.*;
import game.play.*;
import game.play.player.*;
import game.exception.*;

import org.junit.*;
import org.junit.Before;
import static org.junit.Assert.*;


public class ArmyTest{
  private DesertTile tile1;
  private MountainTile tile2;
  private PlainTile tile3;
  private ForestTile tile4;
  private Board board;
  private WarPlayer p ;
  private WarPlayer p2 ;
  private Army a1;
  private Army a2;
  private Army a3;
  private Army a4;
  @Before
  public void init(){
    this.tile1= new DesertTile(5,5);
    this.tile2= new MountainTile(1,1);
    this.tile3= new PlainTile(0,0);
    this.tile4= new ForestTile(2,5);
    this.board = new Board(10,10);
    this.board.initBoard();
    this.p= new WarPlayer("TEST", board);
    this.p2 = new WarPlayer("TEST2", board);
    try{
      this.a1=new Army(1, tile1, board, p, 0);
      this.a2=new Army(1, tile2, board, p, 1);
      this.a3=new Army(1, tile3, board, p, 2);
      this.a4=new Army(1, tile4, board, p, 3);
    }
    catch(Exception e){
      System.out.println(e);
    }
    p.addArmy(a1);
    p.addArmy(a2);
    p.addArmy(a3);
    p.addArmy(a4);
  }

  @Test
  public void getCostTest() throws TileCantBeOccupedException,TileIsOccupedException,MaxCapacityException{
        /** ici on crée les cases en dehors du board car board est aléatoire*/
        
        assertEquals(this.a1.getCost(),2);
        assertEquals(this.a2.getCost(),1);
        assertEquals(this.a3.getCost(),1);
        assertEquals(this.a4.getCost(),1);
  }

  @Test
  public void getPlayerTest() throws TileCantBeOccupedException,TileIsOccupedException,MaxCapacityException{
    assertEquals(this.a1.getPlayer(),this.p);
  }

  @Test
  public void setPlayerTest() throws TileCantBeOccupedException,TileIsOccupedException,MaxCapacityException{
    this.a1.setPlayer(this.p2);
    assertEquals(this.a1.getPlayer(),this.p2);
  }

  @Test 
  public void getNbWarriorTest(){
    assertEquals(this.a1.getNbWarrior(),1);
    assertEquals(this.a2.getNbWarrior(),3);/**ici 3 car une armée de taille 1 est considérée comme une armée de taille 3 */
  }

  @Test
  public void getMaxCapacityTest(){
    assertEquals(this.a1.getMaxCapacity(),3);/**3 car desert */
    assertEquals(this.a3.getMaxCapacity(),5);
  }

  @Test 
  public void addWarriorTest_et_addWarriorMountain(){
    this.a1.addWarrior(1);
    assertEquals(this.a1.getNbWarrior(),2);
    this.a2.addWarriorMountain(1);
    assertEquals(this.a2.getNbWarrior(),4);/**4 car une armée de taille 2 est considéré comme une armée de taille 4 */
  }

  @Test
  public void looseWarriorTest(){
    this.a1.addWarrior(1);
    this.a1.looseWarrior(1);
    assertEquals(this.a1.getNbWarrior(),1);
    this.a1.looseWarrior(1);
    System.out.println(p.getCharacters().size());
    assertEquals(p.getCharacters().size(),3);/**car l'armée est supprimés */
    assertFalse(a1.equals(p.getCharacters().get(0)));
    assertFalse(a1.equals(p.getCharacters().get(1)));
    assertFalse(a1.equals(p.getCharacters().get(2)));
  }

  @Test
  public void looseWarriorMountain(){
    this.a2.addWarriorMountain(1);
    this.a2.looseWarriorMountain();
    assertEquals(this.a2.getNbWarrior(),3);
    this.a2.looseWarriorMountain();
    assertEquals(p.getCharacters().size(),3);/**car l'armée est supprimés  */
    assertFalse(a2.equals(p.getCharacters().get(0)));
    assertFalse(a2.equals(p.getCharacters().get(1)));
  }

  @Test
  public void equalsTest(){
    assertTrue(this.a1.equals(p.getCharacters().get(0)));
    assertFalse(this.a1.equals(p.getCharacters().get(1)));
    assertFalse(this.a1.equals("faux"));
  }

  @Test
  public void getXTest(){
    assertEquals(a4.getX(),2);
  }

  @Test
  public void getYTest(){
    assertEquals(a4.getY(),5);
  }

  @Test 
  public void toStringTest(){
    assertEquals(a1.toString(),"A(TEST,1)");
  }

  


  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(game.character.ArmyTest.class);
  }
}
