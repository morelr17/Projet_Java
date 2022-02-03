package game.match;

import game.competitor.*;
import game.competition.*;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class MatchTest{
    
    private Competition competition;
    private Competitor c1;
    private Competitor c2;
    private Match match;

    @Before
    public void init(){
        c1 = new Competitor("Romain");
        c2 = new Competitor("Nawfel");

        List<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(c1);
        competitors.add(c2);
        
        competition = new MockCompetition(competitors);
        match = new Match(competition,c1,c2);
    }

    @Test
    public void matchConsequenceTest(){
        match.matchConsequence();
        assertTrue( ( (competition.getPoints(c1) > 0) && (competition.getPoints(c2) == 0) ) || ( (competition.getPoints(c2) > 0) && (competition.getPoints(c1) == 0) ) );
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.match.MatchTest.class);
    }
}