package game.competition.observer;

import game.competition.*;
import game.competitor.*;
import game.match.Match;

import java.util.List;
import java.util.ArrayList;


import org.junit.*;
import static org.junit.Assert.*;

public class JournalistTest {

    private Journalist journalist;
    private Competition competition;
    private List<Competitor> competitors;
    

    private static final String[] listOfNames = {"Nawfel","Romain","bin","ome","a","b","c","d","e","f","g","h","i","j","k","l"};
    
    @Before
    public void init(){
        competitors = new ArrayList<>();
        for (int i = 0; i < listOfNames.length; i++) {
            competitors.add(new Competitor(listOfNames[i]));
        }
        journalist = new Journalist("Nawfel");
        competition = new League(competitors);
    }

    @Test
    public void getNameTest(){
        assertEquals("Nawfel",journalist.getName());
    }

    @Test
    public void observeTest(){
        journalist.observe(competition);
        assertTrue(competition.getObservers().contains(journalist));
    }


    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.observer.JournalistTest.class);
    }
}