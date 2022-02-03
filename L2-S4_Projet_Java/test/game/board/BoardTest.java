package game.board;

import org.junit.*;
import game.board.*;
import game.board.tile.*;
import game.play.*;
import game.play.player.*;


import static org.junit.Assert.*;

public class BoardTest{


      @Test
      public void boardTest(){
            Board b = new Board(3,3);
            assertEquals(b.getX(),3);
            assertEquals(b.getY(),3);
            /*on ne peux pas tester la valeur du stock de CommonTile car aléatoire*/
      }
            /**la plupart des méthodes reposant sur de l'aléatoires elles ne seront pas toutes testés cependant on peux vérifier si une board respecte bien les 2 conditions aprés l'utilisation de initBoard()**/
      
      @Test
      public void initBoardTest(){
            Board b = new Board(3,3);
            b.initBoard();
            Tile [][] theTiles;
            theTiles=b.getTiles();
            int cpt=0;
            int cpt2=0;
            for(int x=0;x<3;x++){
                  for(int y=0;y<3;y++){
                        if(theTiles[x][y] instanceof Ocean){
                              cpt+=1;
                        }
                        else{
                              if(b.checkAdjacent(theTiles[x][y])){ 
                                    cpt2+=1;
                              }
                        
                        }
                  }
            }
            assertTrue(cpt>=6); // 9*(2/3)=6
            assertTrue((cpt+cpt2)==9); // si l'ensemble des cases d'ocean plus l'ensemble des autres cases qui ont un adjacent est égale au nombre totale de cases alors le plateau est correct
      }    
      
  
  

  public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.board.BoardTest.class);
  }

}