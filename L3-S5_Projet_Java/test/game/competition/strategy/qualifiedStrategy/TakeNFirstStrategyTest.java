package game.competition.strategy.qualifiedStrategy;

import game.competition.*;
import game.competitor.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class TakeNFirstStrategyTest extends QualifiedByGroupStrategyTest{
    
    @Test
    public void getTest(){
        assertEquals(0,0);
    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.strategy.qualifiedStrategy.TakeNFirstStrategyTest.class);
    }
}
