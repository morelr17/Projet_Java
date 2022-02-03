package game.competition;

import game.competitor.*;
import game.match.*;
import game.match.strategy.*;
import game.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.*;
import static org.junit.Assert.*;

public class CompetitionTest{

    protected List<Competitor> competitors;
    protected List<Competitor> compsTest;
    protected Competition competition;
    protected League league;
    protected Tournament tournament;
    protected Master master;
    protected Competitor c1;
    protected Competitor c2;

    private static final String[] listOfNames = {"Nawfel","Romain","bin","ome","a","b","c","d","e","f","g","h","i","j","k","l"};

    @Before
    public void init(){
        competitors = new ArrayList<>();
        for (int i = 0; i < listOfNames.length; i++) {
            competitors.add(new Competitor(listOfNames[i]));
        }

        compsTest = new ArrayList<>();
        for(int i = 0;i<10;i++){
            compsTest.add(new Competitor(listOfNames[i]));
        }
        c1 = competitors.get(0);
        c2 = competitors.get(1);

        competition = new MockCompetition(competitors);
        league = new League(competitors);
        tournament = new Tournament(competitors);
        master = new Master(competitors);
    }

    @Test(expected=IllegalArgumentException.class)
    public void competitorsSizeNullTest(){
        List<Competitor> comps = null;
        Competition comp = new MockCompetition(comps);
    }

    @Test(expected=IllegalArgumentException.class)
    public void competitorsStrategyNullTest(){
        MatchStrategy strat = null;
        Competition comp = new MockCompetition(competitors,strat);
    }

    @Test
    public void getCompetitorsTest(){
        assertEquals(competitors,competition.getCompetitors());
    }

    @Test
    public void addPointsTest(){
        int pointBefore = competition.getPoints(c1);
        competition.addPoints(c1,1);
        int pointAfter = competition.getPoints(c1);
        
        assertEquals(1,pointAfter - pointBefore);
    }
    
    @Test
    public void getPointsTest(){
        assertEquals(0,competition.getPoints(c1));
    }

    @Test
    public void playMatchReturnAMatchTest(){
        assertTrue(competition.playMatch(c1,c2) instanceof Match);
    }

    @Test
    public void playMatchTheMatchIsPlayedTest(){
        int c1PointsBefore = competition.getPoints(c1);
        int c2PointsBefore = competition.getPoints(c2);
        competition.playMatch(c1,c2);
        assertTrue(c1PointsBefore < competition.getPoints(c1) || c2PointsBefore < competition.getPoints(c2));
    }
    
    @Test
    public void rankingTest(){
        competition.play();
        Entry<Competitor,Integer> mapWinner = competition.ranking().entrySet().iterator().next();
        for(Entry<Competitor,Integer> entry : competition.ranking().entrySet()){
            assertTrue(mapWinner.getValue() >= entry.getValue());
        }
    }

    @Test(expected = RuntimeException.class)
    public void playCannotPlayMoreThan1TimeTest() {
        competition.play();
        competition.play();
    }

    @Test
    public void competitionIsFinishedTest(){
        competition.play();
        assertTrue(competition.isFinished());
    }
    

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.CompetitionTest.class);
    }
    
}
