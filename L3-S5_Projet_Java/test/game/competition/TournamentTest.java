package game.competition;

import game.competitor.*;
import game.match.*;
import game.exception.*;
import game.util.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class TournamentTest extends CompetitionTest {

    @Test(expected=IllegalArgumentException.class)
    public void competitorsSizeIsNotEvenTest(){
        Tournament tournoi = new Tournament(compsTest);
    }

    @Test
    public void playOneRoundTest(){
        assertEquals(competitors.size()/2, tournament.playOneRound(tournament.competitors).size());
    }
    
    @Test
    public void playNotNullTest() {
        assertNotNull(tournament.play());
    }

    @Test(expected = RuntimeException.class)
    public void playCannotPlayMoreThan1TimeTest() {
        tournament.play();
        tournament.play();
    }

    @Test
    public void playWinnerMatchWithRankingTest() {
        Competitor winner = tournament.play();
        assertEquals(winner, tournament.ranking().entrySet().stream().max((comp1, comp2) -> Integer.compare(comp1.getValue(), comp2.getValue())).get().getKey());
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.competition.TournamentTest.class);
    }
}