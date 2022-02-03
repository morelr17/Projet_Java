package game.competition.strategy.generatesGroupStrategy;

import game.competition.*;
import game.competitor.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class MinGroupStrategyTest extends MasterGroupStrategyTest{

    @Test
    public void generatesGroupTest(){
        List<Competition> competitionGenerate = master.getGroupStrategy().generatesGroups();
        assertEquals(2,competitionGenerate.size());
    }
    

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.strategy.generatesGroupStrategy.MinGroupStrategyTest.class);
    }
}
