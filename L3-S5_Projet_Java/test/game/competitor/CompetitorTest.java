package game.competitor;

import game.competition.*;

import org.junit.*;
import static org.junit.Assert.*;


public class CompetitorTest{

    private Competitor competitor1;
    private Competitor competitor2;
    private Competitor competitor3;
    
    @Before
    public void init(){
        competitor1 = new Competitor("binome");
        competitor2 = new Competitor("Nawfel");
        competitor3 = new Competitor("binome");
    }

    @Test
    public void getNameTest(){
        assertEquals("binome",competitor1.getName());
    }

    @Test
    public void equalsTrueTest(){
        assertTrue(competitor1.equals(competitor1));
    }

    @Test
    public void equalsFalseTest(){
        assertFalse(competitor1.equals(competitor2));
        assertFalse(competitor1.equals(competitor3));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competitor.CompetitorTest.class);
    }
}
