package game.match;

import game.competitor.*;
import game.match.strategy.*;
import game.competition.*;


public class Match{

    private Competitor competitor1;
    private Competitor competitor2;
    private Competitor winner; 
    private MatchStrategy strategy;
    private Competition competition;

    public static final int  WINNING_POINTS = 1;

    /**
     *Create a Match
     *@param competition the competition where the match is played
     *@param c1 a competitor
     *@param c2 a competitor
     *@param strategy a match strategy
     */
    public Match(Competition competition, Competitor c1,Competitor c2, MatchStrategy strategy){
        this.competition = competition;
        this.competitor1 = c1;
        this.competitor2 = c2;
        this.strategy = strategy;
    }

    /**
     *Create a Match with default strategy
     *@param competition the competition where the match is played
     *@param c1 a competitor
     *@param c2 a competitor
     */
    public Match(Competition competition,Competitor c1,Competitor c2){
        this(competition,c1,c2,new RandomMatchStrategy());
    }
    
    /**
     *return the winner of the match
     *@return the winner of the match
     */
    public Competitor getWinner(){
        return this.winner;
    }
    public Competitor getLooser(){
        if (competitor1 == this.getWinner()){
            return competitor2;
        }
        else {
            return competitor1;
        }
    }

    /**
     *play the match with the adequate strategy and give points to the winner
     */
    public void matchConsequence(){
        this.winner = strategy.operate(this.competitor1, this.competitor2);
        this.competition.addPoints(this.winner, WINNING_POINTS);
    }

    /**
     *return the member of the match and who win the match 
     *@return the member of the match and who win the match 
     */
    public String toString(){
        return this.competitor1 + " vs " + this.competitor2  +"\nWinner -->" + this.getWinner() +"\n" ;
    }

}