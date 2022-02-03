package game.competition;

import game.competitor.*;
import game.match.*;
import game.exception.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;

import static org.junit.Assert.*;

public class MasterTest extends CompetitionTest {

    @Test
    public void playNotNullTest() {
        assertNotNull(master.play());
    }

    @Test
    public void playWinnerMatchWithRankingTest() {
        Competitor winner = master.play();
        assertEquals(winner, master.ranking().entrySet().stream().max((c1, c2) -> Integer.compare(c1.getValue(), c2.getValue())).get().getKey());
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.MasterTest.class);
    }

}