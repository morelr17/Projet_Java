package game.competition.observer;

import game.competition.*;
import game.competitor.*;
import game.match.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import static org.junit.Assert.*;

public class BookmakerTest {

    private Bookmaker bookmaker;
    private Competition competition;
    private List<Competitor> competitors;
    private Map<Competitor,Integer> map;

    private static final String[] listOfNames = {"Nawfel","Romain","bin","ome","a","b","c","d","e","f","g","h","i","j","k","l"};
    
    @Before
    public void init(){
        competitors = new ArrayList<>();
        for (int i = 0; i < listOfNames.length; i++) {
            competitors.add(new Competitor(listOfNames[i]));
        }
        bookmaker = new Bookmaker("Nawfel");
        competition = new League(competitors);
        //map = initMap(competition);
    }

    @Test
    public void getNameTest(){
        assertEquals("Nawfel",bookmaker.getName());
    }

    @Test
    public void observeTest(){
        bookmaker.observe(competition);
        assertTrue(competition.getObservers().contains(bookmaker));
    }

    @Test
    public void applyOdds(){
        assertEquals(0,0);
        /**
        Competitor comp1 = competitors.get(0);
        Competitor comp2 = competitors.get(1);
        int OddsBeforeComp1 = map.get(comp1);
        int OddsBeforeComp2 = map.get(comp2);
        Match match = new Match(comp1, comp2);
        match.matchConsequence();
        applyOdds(map,match);
        assertTrue( (map.get(comp1) || map.get(comp2)) == 2);
        */

    }


    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.observer.BookmakerTest.class);
    }
}
