package game.competition;

import game.competitor.*;
import game.match.*;
import game.exception.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class LeagueTest extends CompetitionTest{

    @Test
    public void playNotNullTest() {
        assertNotNull(league.play());
    }

    @Test(expected = RuntimeException.class)
    public void playCannotPlayMoreThan1TimeTest() {
        league.play();
        league.play();
    }


    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.LeagueTest.class);
    }
}