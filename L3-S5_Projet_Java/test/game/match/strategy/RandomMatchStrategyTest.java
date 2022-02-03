package game.match.strategy;

import game.competitor.*;
import game.competition.*;
import game.match.*;

import org.junit.*;
import static org.junit.Assert.*;

public class RandomMatchStrategyTest{
    
    private Competitor c1;
    private Competitor c2;
    private MatchStrategy matchRandom;

    @Before
    public void init(){
        c1 = new Competitor("Romain");
        c2 = new Competitor("Nawfel");
        matchRandom = new RandomMatchStrategy();
    }

    @Test
    public void operateTest(){
        assertTrue(matchRandom.operate(c1,c2) instanceof Competitor);
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.match.strategy.RandomMatchStrategyTest.class);
    }
}